package com.grupo3.androiddsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPrincipal extends AppCompatActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        View container = findViewById(R.id.fondo);
        container.getBackground().setAlpha(50);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        mCurrentFragment = new JugarFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, mCurrentFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.play);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.lista:
                        mCurrentFragment = new SocialFragment();
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

}