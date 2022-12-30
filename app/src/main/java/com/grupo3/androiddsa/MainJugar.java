package com.grupo3.androiddsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grupo3.androiddsa.databinding.ActivityMainJugarBinding;

public class MainJugar extends AppCompatActivity {

    //ActivityMainJugarBinding binding;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainJugarBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main_jugar);
        //replaceFragment(new JugarFragment());

        /*binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.lista:
                    replaceFragment(new AmigosFragment());
                    break;
                case R.id.tienda:
                    replaceFragment(new TiendaFragment());
                    break;
                case R.id.play:
                    replaceFragment(new JugarFragment());
                    break;
                case R.id.perfil:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.config:
                    replaceFragment(new SettingsFragment());
                    break;

            }


            return true;
        });*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        mCurrentFragment = new JugarFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, mCurrentFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.play);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.lista:
                        mCurrentFragment = new AmigosFragment();
                        break;
                    case R.id.tienda:
                        mCurrentFragment = new TiendaFragment();
                        break;
                    case R.id.play:
                        mCurrentFragment = new JugarFragment();
                        break;
                    case R.id.perfil:
                        mCurrentFragment = new ProfileFragment();
                        break;
                    case R.id.config:
                        mCurrentFragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, mCurrentFragment).commit();
                return true;
            }

        });
    }

    /*private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }*/


}