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
import com.grupo3.androiddsa.domain.Partida;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.domain.to.PartidaCreate;
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
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_personaje);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        objectId =(String) getIntent().getSerializableExtra("objectId");

        getListCharacters();
    }

    private void getListCharacters(){
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
                        PartidaCreate partidaCreate = new PartidaCreate(email, objectId, character.getCharacterId());
                        Call<Partida> call = service.createPartida(partidaCreate);
                        call.enqueue(new Callback<Partida>() {
                            @Override
                            public void onResponse(Call<Partida> call, Response<Partida> response) {
                                switch (response.code()) {
                                    case 200:
                                        //Intent i = new Intent(EscogerPersonajeActivity.this, Unity.class);
                                        //startActivity(i);
                                        break;
                                    case 500:
                                        Toast.makeText(getApplicationContext(), "Partida no creada", Toast.LENGTH_LONG).show();
                                        break;
                                }
                            }
                            @Override
                            public void onFailure(Call<Partida> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
                            }
                        });
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

    public void volver(View view) {
        Intent i = new Intent(getApplicationContext(), EscogerObjetoActivity.class);
        startActivity(i);
    }
}