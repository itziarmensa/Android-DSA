package com.grupo3.androiddsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo3.androiddsa.domain.Information;
import com.grupo3.androiddsa.domain.Issue;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueActivity extends AppCompatActivity {

    Button btnAdd, btnBack;
    EditText message;

    private ProgressBar progressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        message = findViewById(R.id.message);

        progressBarRegister = findViewById(R.id.progressBarRegister);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIssue(view);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IssueActivity.this, MainPrincipal.class);
                startActivity(i);
            }
        });
    }

    public void addIssue(View view) {
        progressBarRegister.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String email = preferencias.getString("mail","");

        Call<Issue> call = service.addIssue(new Issue(date.toString(), email, message.getText().toString()));

        call.enqueue(new Callback<Issue>() {
            @Override
            public void onResponse(Call<Issue> call, Response<Issue> response) {
                progressBarRegister.setVisibility(View.GONE);
                switch (response.code()) {
                    case 200:
                        Toast.makeText(getApplicationContext(),"Issue added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(IssueActivity.this, MainPrincipal.class);
                        startActivity(i);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Issue> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                progressBarRegister.setVisibility(View.GONE);
            }
        });
    }
}