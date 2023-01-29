package com.grupo3.androiddsa;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo3.androiddsa.domain.Information;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.domain.to.UserRegister;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {

    Button btnAdd, btnBack;
    EditText title, message;

    private ProgressBar progressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        title = findViewById(R.id.title);
        message = findViewById(R.id.message);

        progressBarRegister = findViewById(R.id.progressBarRegister);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInformation(view);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformationActivity.this, MainPrincipal.class);
                startActivity(i);
            }
        });
    }

    public void addInformation(View view) {
        progressBarRegister.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String email = preferencias.getString("mail","");

        Call<Information> call = service.addInformation(new Information(date.toString(),title.getText().toString(), message.getText().toString(), email));

        call.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, Response<Information> response) {
                progressBarRegister.setVisibility(View.GONE);
                switch (response.code()) {
                    case 200:
                        Intent i = new Intent(InformationActivity.this, MainPrincipal.class);
                        startActivity(i);
                        break;
                    case 500:
                        Toast.makeText(getApplicationContext(), "Missing Information", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                progressBarRegister.setVisibility(View.GONE);
            }
        });
    }
}