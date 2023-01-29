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
import android.widget.Toast;

import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private TextView tv;
    Button btnConfChanges;
    Button cerrarSesion;
    Button musicStopBtn;
    Button musicPlayBtn;
    Button faqsBtn;
    Button issueBtn;
    String idioma;
    User user;

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

        faqsBtn = (Button) rootView.findViewById(R.id.faqsBtn);
        faqsBtn.setOnClickListener(this);

        issueBtn = (Button) rootView.findViewById(R.id.issueBtn);
        issueBtn.setOnClickListener(this);

        Spinner spn=(Spinner) rootView.findViewById(R.id.spn);
        spn.setOnItemSelectedListener(this);

        Api service = Api.retrofit.create(Api.class);
        SharedPreferences preferencias=getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        String email = preferencias.getString("mail","");
        Call<User> call = service.getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

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
        cambiarIdiomaBBDD();
        Intent intent = new Intent(getActivity(), MainSplashScreen.class);
        getActivity().startActivity(intent);
    }

    public void cambiarIdioma() {
        Resources res = getResources(); // obtenemos una instancia de Resources
        Configuration config = new Configuration(res.getConfiguration()); // obtenemos la configuración actual
        switch (idioma){
            case "Spanish":
                user.setLanguage("es");
                config.setLocale(new Locale("es")); // establecemos el idioma español
                break;
            case "English":
                user.setLanguage("en");
                config.setLocale(new Locale("en")); // establecemos el idioma español
                break;
            case "French":
                user.setLanguage("fr");
                config.setLocale(new Locale("fr")); // establecemos el idioma español
                break;
            case "Italian":
                user.setLanguage("it");
                config.setLocale(new Locale("it")); // establecemos el idioma español
                break;
            case "Portuguese":
                user.setLanguage("pt");
                config.setLocale(new Locale("pt")); // establecemos el idioma español
                break;

        }
        res.updateConfiguration(config, res.getDisplayMetrics()); // aplicamos los cambios
    }

    public void cambiarIdiomaBBDD() {
        Api service = Api.retrofit.create(Api.class);
        Call<Void> call = service.updateUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        Toast.makeText(getContext(), "Language updated correctly", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });
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
        if(view.getId()==R.id.faqsBtn){
            Intent intent = new Intent(getActivity(), FaqsActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.issueBtn){
            Intent intent = new Intent(getActivity(), IssueActivity.class);
            startActivity(intent);
        }
    }
}