package com.itziar.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itziar.androiddsa.domain.vo.Credentials;
import com.itziar.androiddsa.domain.vo.EmailAddress;
import com.itziar.androiddsa.retrofit.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogIn extends AppCompatActivity {

    Button btnLogIn, btnRegister;
    EditText mail, password;

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

    public void logIn(View view){
        Api service = Api.retrofit.create(Api.class);
        Call<Void> call = service.logInUser(new Credentials(new EmailAddress(mail.getText().toString()),password.getText().toString()));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
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
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}