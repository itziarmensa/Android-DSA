package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.domain.to.UserRegister;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRegister extends AppCompatActivity {

    Button btnOK, btnCancel;
    EditText name ,surname , birthdate, mailRegister, passwordRegister,passwordRegister2;

    DatePickerDialog.OnDateSetListener setListener;

    private ProgressBar progressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        btnOK = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        birthdate = findViewById(R.id.birthdate);
        mailRegister = findViewById(R.id.mailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        passwordRegister2 = findViewById(R.id.passwordRegister2);

        progressBarRegister = findViewById(R.id.progressBarRegister);

        Calendar calendar =  Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        birthdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainRegister.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = dayOfMonth+"/" + month + "/"+year;
                birthdate.setText(date);
            }
        };
    }

    public void btnCancel(View view){
        Intent i = new Intent(MainRegister.this, MainLogIn.class);
        startActivity(i);
    }

    public void registerUser(View view) {
        if (!passwordRegister.getText().toString().equals(passwordRegister2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not coincide", Toast.LENGTH_LONG).show();
            return;
        }
        progressBarRegister.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);


        Call<User> call = service.registerUser(new UserRegister(name.getText().toString(), surname.getText().toString(), birthdate.getText().toString(), mailRegister.getText().toString(), passwordRegister.getText().toString()));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBarRegister.setVisibility(View.GONE);
                switch (response.code()) {
                    case 200:
                        Intent i = new Intent(MainRegister.this, MainLogIn.class);
                        startActivity(i);
                        break;
                    case 406:
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                        Toast.makeText(getApplicationContext(), "Missing Information", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                progressBarRegister.setVisibility(View.GONE);
            }
        });
    }
}