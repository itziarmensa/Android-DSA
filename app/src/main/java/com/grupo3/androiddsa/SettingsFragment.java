package com.grupo3.androiddsa;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private TextView tv;
    Button btnConfChanges;
    Button cerrarSesion;
    Button musicStopBtn;
    Button musicPlayBtn;
    String idioma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        //setContentView(rootView);

        btnConfChanges = (Button) rootView.findViewById(R.id.btnConfChanges);
        btnConfChanges.setOnClickListener(this);

        cerrarSesion = rootView.findViewById(R.id.cerrarSesion);
        cerrarSesion.setOnClickListener(this);

        musicStopBtn=(Button) rootView.findViewById(R.id.musicStopBtn);
        musicStopBtn.setOnClickListener(this);

        musicPlayBtn=(Button) rootView.findViewById(R.id.playMusicBtn);
        musicPlayBtn.setOnClickListener(this);

        tv=(TextView) rootView.findViewById(R.id.tvId);
        Spinner spn=(Spinner) rootView.findViewById(R.id.spn);
        spn.setOnItemSelectedListener(this);
        SharedPreferences preferences = getActivity().getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE);
        tv.setText(preferences.getString("idioma",""));

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        idioma=item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        tv.setText("");
    }

    public void applyChanges(View view){
        // Declarar la variable sharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mi_archivo_preferencias", MODE_PRIVATE);

        // Almacenar el valor de la variable idioma
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idioma", idioma);

        // Aplicar los cambios
        editor.apply();
        cambiarIdioma();
        Intent intent = new Intent(getActivity(), MainSplashScreen.class);
        getActivity().startActivity(intent);


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

    public void cerrarSesion(View view) {
        SharedPreferences preferencias = getActivity().getSharedPreferences("datos", MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putBoolean("isLogged",false);
        Obj_editor.apply();
        Intent i = new Intent(getActivity(), MainLogIn.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnConfChanges) {
            applyChanges(view);
        }

        if(view.getId() == R.id.cerrarSesion){
            cerrarSesion(view);
        }
        if(view.getId()==R.id.musicStopBtn){
            Intent intent = new Intent(getActivity(), ElServicio.class);
            getActivity().stopService(intent);
        }
        if(view.getId()==R.id.playMusicBtn){
            Intent intent = new Intent(getActivity(), ElServicio.class);
            getActivity().startService(intent);
        }
    }
}