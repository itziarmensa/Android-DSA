package com.itziar.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itziar.androiddsa.domain.UserLogIn;
import com.itziar.androiddsa.domain.exceptions.EmailAddressNotValidException;
import com.itziar.androiddsa.domain.vo.Credentials;
import com.itziar.androiddsa.domain.vo.EmailAddress;
import com.itziar.androiddsa.retrofit.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainLogIn extends AppCompatActivity {

    Button btnLogIn, btnRegister;
    EditText mail, password;

    String URL = "http://147.83.7.205:80/dsaApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btnLogIn = findViewById(R.id.btnLogIn);
        btnRegister = findViewById(R.id.btnRegister);

        mail = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void addUser(View view){
        Intent i = new Intent(MainLogIn.this, MainRegister.class);
        startActivity(i);
    }


    public void logIn(View view) throws EmailAddressNotValidException {
        Credentials credentials = new Credentials(new EmailAddress(mail.toString()),password.toString());
        logInUser(credentials);
    }

    private void logInUser(Credentials credentials){

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
        Call<UserLogIn> call = service.logInUser(credentials);

        call.enqueue(new Callback<UserLogIn>() {
            @Override
            public void onResponse(Call<UserLogIn> call, Response<UserLogIn> response) {
                switch (response.code()) {
                    case 200:
                        Intent i = new Intent(MainLogIn.this, MainObjects.class);
                        startActivity(i);
                        break;

                    case 500:
                        Toast.makeText(getApplicationContext(),"Missing information", Toast.LENGTH_LONG).show();
                        break;

                    case 501:
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserLogIn> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_LONG).show();
            }
        });
    }


}