package com.grupo3.androiddsa;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private TextView tv;
    Button btnConfChanges;
    Button cerrarSesion;
    String idioma;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

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

        tv=(TextView) rootView.findViewById(R.id.tvId);
        Spinner spn=(Spinner) rootView.findViewById(R.id.spn);
        spn.setOnItemSelectedListener(this);
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        tv.setText(preferences.getString("idioma",""));

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        idioma=item;
        tv.setText("El idioma seleccionado es el: "+item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        tv.setText("");
    }

    public void applyChanges(View view){
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
        SharedPreferences preferencias = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
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
    }
}