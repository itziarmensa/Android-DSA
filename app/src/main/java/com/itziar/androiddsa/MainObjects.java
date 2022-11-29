package com.itziar.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import com.itziar.androiddsa.domain.MyObjects;
import com.itziar.androiddsa.domain.UserLogIn;
import com.itziar.androiddsa.retrofit.Api;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainObjects extends AppCompatActivity {

    String URL = "http://147.83.7.205:80/dsaApp/";

    TextView lisObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objects_main);

        lisObjects = findViewById(R.id.listObjects);
        getListObjects();
    }

    private void getListObjects(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);
        Call<List<MyObjects>> call = service.getListObjects();

        call.enqueue(new Callback<List<MyObjects>>() {
            @Override
            public void onResponse(Call<List<MyObjects>> call, Response<List<MyObjects>> response) {
                List<MyObjects> lista = response.body();
                for(MyObjects object: lista){
                    lisObjects.setText(object.getIdObject()+"\n");
                    lisObjects.setText(object.getDescriptionObject()+"\n");
                    lisObjects.setText(object.getName()+"\n");
                    lisObjects.setText(object.getCoins()+"\n");
                }
            }

            @Override
            public void onFailure(Call<List<MyObjects>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}