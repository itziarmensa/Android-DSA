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

import com.grupo3.androiddsa.adapters.AdapterPartida;
import com.grupo3.androiddsa.domain.Partida;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartidaActivity extends AppCompatActivity {
    private List<Partida> listPartida;
    private RecyclerView recycler;
    private AdapterPartida adapterRanking;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        getPartidas();
    }

    private void getPartidas(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String email = preferencias.getString("mail","");
        Call<List<Partida>> call = service.getPartidas(email);

        call.enqueue(new Callback<List<Partida>>() {
            @Override
            public void onResponse(Call<List<Partida>> call, Response<List<Partida>> response) {
                progressBarStore.setVisibility(View.GONE);
                listPartida = response.body();
                adapterRanking = new AdapterPartida(listPartida, new AdapterPartida.OnItemClickListener() {
                    @Override
                    public void onItemClick(Partida partida) {
                        if (!partida.getFinished()) {
                            Toast.makeText(getApplicationContext(),"Has escogido "+ partida.getPartidaId(),Toast.LENGTH_LONG).show();
                            //Intent i = new Intent(PartidaActivity.this,Unity.class);
                            //i.putExtra("partida",partida);
                            //startActivity(i);
                        }
                    }
                });
                recycler.setAdapter(adapterRanking);
            }

            @Override
            public void onFailure(Call<List<Partida>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver(View view) {
        Intent i = new Intent(getApplicationContext(), MainPrincipal.class);
        startActivity(i);
    }
}
