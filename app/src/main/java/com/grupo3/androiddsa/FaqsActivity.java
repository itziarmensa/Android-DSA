package com.grupo3.androiddsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.adapters.AdapterEscogerObjeto;
import com.grupo3.androiddsa.adapters.AdapterFaqs;
import com.grupo3.androiddsa.domain.Faqs;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqsActivity extends AppCompatActivity {

    private List<Faqs> listFaqs;
    private RecyclerView recycler;
    private AdapterFaqs adapterFaqs;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        getListFaqs();
    }

    private void getListFaqs(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<Faqs>> call = service.getFaqs();

        call.enqueue(new Callback<List<Faqs>>() {
            @Override
            public void onResponse(Call<List<Faqs>> call, Response<List<Faqs>> response) {
                progressBarStore.setVisibility(View.GONE);
                listFaqs = response.body();
                adapterFaqs = new AdapterFaqs(listFaqs);
                recycler.setAdapter(adapterFaqs);
            }

            @Override
            public void onFailure(Call<List<Faqs>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver(View view) {
        Intent i = new Intent(getApplicationContext(), MainPrincipal.class);
        startActivity(i);
    }
}