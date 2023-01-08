package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Characters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.grupo3.androiddsa.domain.MyObjects;

public class MainCharactersDetails extends AppCompatActivity {

    TextView nameCharacter;
    TextView detalisCharacter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characters_details_main);

        nameCharacter = findViewById(R.id.name_Character_Details);
        detalisCharacter = findViewById(R.id.detailsCharacter);

        Characters character = (Characters) getIntent().getSerializableExtra("Details Characters");

        nameCharacter.setText(character.getCharacterName());
        detalisCharacter.setText("Descripción: "+character.getCharacterDescription()+"\n"+
                "Monedas: "+character.getCharacterCoins()+"\n");

    }
}