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

import com.grupo3.androiddsa.adapters.AdapterDatos;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjetosFragment extends Fragment {

    private List<MyObjects> listObjects;
    private RecyclerView recycler;
    private AdapterDatos adapterDatos;
    private ProgressBar progressBarStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_objetos, container, false);

        recycler = (RecyclerView) rootView.findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        progressBarStore = rootView.findViewById(R.id.progressBarStore);

        getListObjects();

        return rootView;
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
                        Api api = Api.retrofit.create(Api.class);
                        SharedPreferences preferencias=getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
                        String email = preferencias.getString("mail","");
                        Call<Void> call = api.buyObject(email, object.getObjectId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                switch (response.code()) {
                                    case 200:
                                        Toast.makeText(getContext(),"Felicidades has comprado: " + object.getObjectName(), Toast.LENGTH_LONG).show();
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
                recycler.setAdapter(adapterDatos);
            }

            @Override
            public void onFailure(Call<List<MyObjects>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBarStore.setVisibility(View.GONE);
            }
        });
    }

    private void MoveToDescription(MyObjects object) {
        Intent i = new Intent(getActivity(), MainObjectDetails.class);
        i.putExtra("Details",object);
        startActivity(i);
    }
}