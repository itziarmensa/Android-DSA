package com.grupo3.androiddsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.adapters.AdapterRanking;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {
    private List<User> listUser;
    private RecyclerView recycler;
    private AdapterRanking adapterRanking;
    private ProgressBar progressBarStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = findViewById(R.id.progressBarStore);

        getRanking();
    }

    private void getRanking(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<User>> call = service.getRanking();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBarStore.setVisibility(View.GONE);
                listUser = response.body();
                adapterRanking = new AdapterRanking(listUser);
                recycler.setAdapter(adapterRanking);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver(View view) {
        Intent i = new Intent(getApplicationContext(), MainPrincipal.class);
        startActivity(i);
    }
}
