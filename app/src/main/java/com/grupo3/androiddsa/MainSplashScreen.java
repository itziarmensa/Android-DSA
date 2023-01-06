package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Locale;
import com.grupo3.androiddsa.ElServicio;

public class MainSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash_screen);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Boolean isLogged = preferencias.getBoolean("isLogged", false);

        // Recuperar el valor de la variable idioma almacenado en Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE);
        String idioma = sharedPreferences.getString("idioma", "");
        Resources res = getResources(); // obtenemos una instancia de Resources
        Configuration config = new Configuration(res.getConfiguration()); // obtenemos la configuración actual
        switch (idioma){
            case "Spanish":
                config.setLocale(new Locale("es")); // establecemos el idioma español
                break;
            case "English":
                config.setLocale(new Locale("en")); // establecemos el idioma español
                break;
            case "French":
                config.setLocale(new Locale("fr")); // establecemos el idioma español
                break;
            case "Italian":
                config.setLocale(new Locale("it")); // establecemos el idioma español
                break;
            case "Portuguese":
                config.setLocale(new Locale("pt")); // establecemos el idioma español
                break;

        }
        res.updateConfiguration(config, res.getDisplayMetrics()); // aplicamos los cambios
        startService(new Intent(this, ElServicio.class));

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
