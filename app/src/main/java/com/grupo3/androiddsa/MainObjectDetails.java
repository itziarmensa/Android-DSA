package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.grupo3.androiddsa.domain.MyObjects;

public class MainObjectDetails extends AppCompatActivity {

    TextView name;
    TextView details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_details_main);

        name = findViewById(R.id.name_object);
        details = findViewById(R.id.details);

        MyObjects object = (MyObjects) getIntent().getSerializableExtra("Details");

        name.setText(object.getName());
        details.setText("Descripción: "+object.getDescriptionObject()+"\n"+
                "Monedas: "+object.getCoins()+"\n"+
                "Tipo de objeto: "+object.getTypeObject().getDescription()+"\n");

    }

}