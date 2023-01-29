package com.grupo3.androiddsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.adapters.AdapterInformation;
import com.grupo3.androiddsa.adapters.AdapterRanking;
import com.grupo3.androiddsa.adapters.AdapterUser;
import com.grupo3.androiddsa.domain.Information;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialFragment extends Fragment {
    public List<Information> listInformation;
    private RecyclerView recycler;
    private AdapterInformation adapterInformation;
    private ProgressBar progressBarStore;
    private Button btnAddInformation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_social,container,false);
        recycler=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        progressBarStore = rootView.findViewById(R.id.progressBarStore);
        btnAddInformation = rootView.findViewById(R.id.btnAddInformation);

        btnAddInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), InformationActivity.class);
                startActivity(i);
            }
        });

        getInformation();
        return rootView;
    }
    private void getInformation(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<Information>> call=service.getInformation();
        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                progressBarStore.setVisibility(View.GONE);
                listInformation=response.body();
                adapterInformation=new AdapterInformation(listInformation);
                recycler.setAdapter(adapterInformation);
            }

            @Override
            public void onFailure(Call<List<Information>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                progressBarStore.setVisibility(View.GONE);
            }
        });
    }
}