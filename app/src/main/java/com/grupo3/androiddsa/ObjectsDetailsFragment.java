package com.grupo3.androiddsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo3.androiddsa.domain.MyObjects;

public class ObjectsDetailsFragment extends Fragment {

    TextView name;
    TextView details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_objects_details, container, false);

        name = rootView.findViewById(R.id.name_object);
        details = rootView.findViewById(R.id.details);

        MyObjects object = (MyObjects) getArguments().getSerializable("Details");

        name.setText(object.getObjectName());
        details.setText("Descripción: "+object.getObjectDescription()+"\n"+
                "Monedas: "+object.getObjectCoins()+"\n"+
                "Tipo de objeto: "+object.getObjectTypeId()+"\n");

        return rootView;
    }
}