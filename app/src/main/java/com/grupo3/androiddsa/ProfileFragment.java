package com.grupo3.androiddsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.adapters.AdapterUser;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    public List<User> listUsers;
    private RecyclerView recycler;
    private AdapterUser adapterUser;
    private ProgressBar progressBarStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_profile,container,false);
        recycler=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        progressBarStore = rootView.findViewById(R.id.progressBarStore);
        getListUsers();
        return rootView;
    }
    private void getListUsers(){
        progressBarStore.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<User>> call=service.getListUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBarStore.setVisibility(View.GONE);
                listUsers=response.body();
                adapterUser=new AdapterUser(listUsers, new AdapterUser.OnItemClickListener() {
                    @Override
                    public void onItemClick(User user) {
                        moveToDescription(user);
                    }
                });
                recycler.setAdapter(adapterUser);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                progressBarStore.setVisibility(View.GONE);
            }
        });
    }

    private void moveToDescription(User user){
        Intent i=new Intent(getActivity(),MainUsersDetails.class);
        i.putExtra("Details",user);
        startActivity(i);
    }
}
