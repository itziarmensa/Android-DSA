package com.itziar.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainRegister extends AppCompatActivity {

    Button btnOK, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        btnOK = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
    }

    public void btnOK (View view){
        Intent i = new Intent(MainRegister.this, MainLogIn.class);
        startActivity(i);
    }

    public void btnCancel(View view){
        Intent i = new Intent(MainRegister.this, MainLogIn.class);
        startActivity(i);
    }
}