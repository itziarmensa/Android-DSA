package com.grupo3.androiddsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class JugarFragment extends Fragment {

    Button btnCreatePartida, btnPreviousPartida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_jugar, container, false);

        btnCreatePartida = rootView.findViewById(R.id.btnCreatePartida);
        btnPreviousPartida = rootView.findViewById(R.id.btnPreviousPartida);

        ScaleAnimation animation = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        btnCreatePartida.startAnimation(animation);
        btnPreviousPartida.startAnimation(animation);

        btnCreatePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),EscogerObjetoActivity.class);
                startActivity(i);
            }
        });

        btnPreviousPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),PartidaActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

}