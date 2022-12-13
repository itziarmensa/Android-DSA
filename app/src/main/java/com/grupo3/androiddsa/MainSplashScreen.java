package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash_screen);

        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MainSplashScreen.this,MainLogIn.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo=new Timer();
        tiempo.schedule(tarea,2000);

    }
}