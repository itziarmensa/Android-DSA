package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.adapters.AdapterDatos;
import com.grupo3.androiddsa.adapters.AdapterEscogerObjeto;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscogerObjetoActivity extends AppCompatActivity {

    private List<MyObjects> listObjects;
    private RecyclerView recycler;
    private AdapterEscogerObjeto adapterDatos;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_objeto);

        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        getListObjects();

    }

    private void getListObjects(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String email = preferencias.getString("mail","");
        Call<List<MyObjects>> call = service.getMyObjects(email);

        call.enqueue(new Callback<List<MyObjects>>() {
            @Override
            public void onResponse(Call<List<MyObjects>> call, Response<List<MyObjects>> response) {
                progressBarStore.setVisibility(View.GONE);
                listObjects = response.body();
                adapterDatos = new AdapterEscogerObjeto(listObjects, new AdapterEscogerObjeto.OnItemClickListener() {
                    @Override
                    public void onItemClick(MyObjects object) {
                        Toast.makeText(getApplicationContext(),"Has escogido "+ object.getObjectName(),Toast.LENGTH_LONG).show();
                        Intent i = new Intent(EscogerObjetoActivity.this,EscogerPersonajeActivity.class);
                        startActivity(i);
                    }
                });
                recycler.setAdapter(adapterDatos);
            }

            @Override
            public void onFailure(Call<List<MyObjects>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}