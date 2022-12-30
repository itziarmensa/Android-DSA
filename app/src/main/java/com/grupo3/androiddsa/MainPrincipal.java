package com.grupo3.androiddsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grupo3.androiddsa.AmigosFragment;
import com.grupo3.androiddsa.JugarFragment;
import com.grupo3.androiddsa.ProfileFragment;
import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.SettingsFragment;
import com.grupo3.androiddsa.TiendaFragment;

public class MainPrincipal extends AppCompatActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

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

}