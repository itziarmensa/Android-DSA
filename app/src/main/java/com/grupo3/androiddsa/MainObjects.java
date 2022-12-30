package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.recycler.AdapterDatos;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainObjects extends AppCompatActivity {

    private List<MyObjects> listObjects;
    private RecyclerView recycler;
    private AdapterDatos adapterDatos;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objects_main);

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        getListObjects();


    }

    private void getListObjects(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<MyObjects>> call = service.getListObjects();

        call.enqueue(new Callback<List<MyObjects>>() {
            @Override
            public void onResponse(Call<List<MyObjects>> call, Response<List<MyObjects>> response) {
                progressBarStore.setVisibility(View.GONE);
                listObjects = response.body();
                adapterDatos = new AdapterDatos(listObjects, new AdapterDatos.OnItemClickListener() {
                    @Override
                    public void onItemClick(MyObjects object) {
                        moveToDescription(object);
                    }
                });
                recycler.setAdapter(adapterDatos);
            }

            @Override
            public void onFailure(Call<List<MyObjects>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBarStore.setVisibility(View.GONE);
            }
        });
    }

    private void moveToDescription(MyObjects object) {
        Intent i = new Intent(MainObjects.this, MainObjectDetails.class);
        i.putExtra("Details",object);
        startActivity(i);
    }


}