package com.grupo3.androiddsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.adapters.AdapterCharacters;
import com.grupo3.androiddsa.adapters.AdapterDatos;
import com.grupo3.androiddsa.domain.Characters;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajesFragment extends Fragment {

    private List<Characters> listCharacters;
    private RecyclerView recyclerC;
    private AdapterCharacters adapterDatosC;
    private ProgressBar progressBarCharacter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_personajes, container, false);

        recyclerC = (RecyclerView) rootView.findViewById(R.id.RecyclerViewCharacters);
        recyclerC.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        progressBarCharacter = rootView.findViewById(R.id.progressBarCharacters);

        getListCharacters();

        return rootView;
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
                        Api api = Api.retrofit.create(Api.class);
                        SharedPreferences preferencias=getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
                        String email = preferencias.getString("mail","");
                        Call<Void> call = api.buyCharacter(email, character.getCharacterId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                switch (response.code()) {
                                    case 200:
                                        Toast.makeText(getContext(),"Felicidades has comprado: " + character.getCharacterId(), Toast.LENGTH_LONG).show();
                                        break;
                                    case 500:
                                        Toast.makeText(getContext(), "No tienes suficientes monedas", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getContext(), response.message(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                recyclerC.setAdapter(adapterDatosC);
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBarCharacter.setVisibility(View.GONE);
            }
        });
    }

    /*private void moveToDescriptionC(Characters character) {
        Toast.makeText(getContext(),"Has comprat",Toast.LENGTH_LONG).show();
    }*/
}