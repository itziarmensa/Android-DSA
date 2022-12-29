package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainJugar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jugar);
    }

    public void btnSettings(View view) {
        Intent i = new Intent(MainJugar.this,MainSettings.class);
        startActivity(i);
    }
}