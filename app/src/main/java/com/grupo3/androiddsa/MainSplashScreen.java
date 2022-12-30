package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash_screen);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Boolean isLogged = preferencias.getBoolean("isLogged", false);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLogged) {
                        Intent intent = new Intent(MainSplashScreen.this, MainPrincipal.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainSplashScreen.this, MainLogIn.class);
                        startActivity(intent);
                    }
                }
            }

            };
            timerThread.start();
        }
    }
