package com.grupo3.androiddsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainSettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tv;
    Button btnConfChanges;
    String idioma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        btnConfChanges = (Button) findViewById(R.id.btnConfChanges);
        tv=(TextView) findViewById(R.id.tvId);
        Spinner spn=(Spinner) findViewById(R.id.spn);
        spn.setOnItemSelectedListener(this);
        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tv.setText(preferences.getString("idioma",""));

    }
    public void applyChanges(View view){
        cambiarIdioma();
        Intent i = new Intent(MainSettings.this, MainSplashScreen.class);
        startActivity(i);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        idioma=item;
        tv.setText("El idioma seleccionado es el: "+item);
    }
    public void cambiarIdioma() {
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tv.setText("");
    }
}