package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.grupo3.androiddsa.R;
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

        name.setText(object.getObjectName());
        details.setText("Descripción: "+object.getObjectDescription()+"\n"+
                "Monedas: "+object.getObjectCoins()+"\n"+
                "Tipo de objeto: "+object.getObjectTypeId()+"\n");


    }
}