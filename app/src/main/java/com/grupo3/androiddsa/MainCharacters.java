package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.domain.Characters;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.recycler.AdapterCharacters;
import com.grupo3.androiddsa.recycler.AdapterDatos;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCharacters extends AppCompatActivity {

    private List<Characters> listCharacters;
    private RecyclerView recyclerC;
    private AdapterCharacters adapterDatosC;
    private ProgressBar progressBarCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characters_main);

        recyclerC = (RecyclerView) findViewById(R.id.RecyclerViewCharacters);
        recyclerC.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        progressBarCharacter = findViewById(R.id.progressBarCharacters);

        getListCharacters();
    }

    private void getListCharacters() {
        progressBarCharacter.setVisibility(View.VISIBLE);
        Api serviceCharacter = Api.retrofit.create(Api.class);
        Call<List<Characters>> call = serviceCharacter.getListCharacters();

        call.enqueue(new Callback<List<Characters>>() {
            @Override
            public void onResponse(Call<List<Characters>> call, Response<List<Characters>> response) {
                progressBarCharacter.setVisibility(View.GONE);
                listCharacters = response.body();
                adapterDatosC = new AdapterCharacters(listCharacters,new AdapterCharacters.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(Characters character) {
                        moveToDescriptionC(character);
                    }
                });
                recyclerC.setAdapter(adapterDatosC);
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBarCharacter.setVisibility(View.GONE);
            }
        });
    }

    private void moveToDescriptionC(Characters character) {
        Intent i = new Intent(MainCharacters.this, MainCharactersDetails.class);
        i.putExtra("Details Characters", (CharSequence) character);
        startActivity(i);
    }


}