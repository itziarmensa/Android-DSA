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

import com.grupo3.androiddsa.adapters.AdapterEscogerObjeto;
import com.grupo3.androiddsa.adapters.AdapterEscogerPersonaje;
import com.grupo3.androiddsa.domain.Characters;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscogerPersonajeActivity extends AppCompatActivity {

    private List<Characters> listCharacters;
    private RecyclerView recycler;
    private AdapterEscogerPersonaje adapterDatos;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_personaje);

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
        Call<List<Characters>> call = service.getMyCharacters(email);

        call.enqueue(new Callback<List<Characters>>() {
            @Override
            public void onResponse(Call<List<Characters>> call, Response<List<Characters>> response) {
                progressBarStore.setVisibility(View.GONE);
                listCharacters = response.body();
                adapterDatos = new AdapterEscogerPersonaje(listCharacters, new AdapterEscogerPersonaje.OnItemClickListener() {
                    @Override
                    public void onItemClick(Characters character) {
                        Toast.makeText(getApplicationContext(),"Has escogido "+ character.getCharacterName(),Toast.LENGTH_LONG).show();
                        //Intent i = new Intent(EscogerPersonajeActivity.this,Unity.class);
                        //startActivity(i);
                    }
                });
                recycler.setAdapter(adapterDatos);
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}