package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo3.androiddsa.domain.User;

public class UsersDetailsFragment extends Fragment {

    TextView name;
    TextView details;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_users_details,container,false);
        name=rootView.findViewById(R.id.name_user);
        details=rootView.findViewById(R.id.details);

        User user=(User) getArguments().getSerializable("Details");
        name.setText(user.getUserName());
        details.setText("Name: "+user.getUserName()+"\n"+
                "Surname: "+user.getUserSurname()+"\n"+
                "Birth Date: "+user.getUserBirth()+"\n"+
                "Email: "+user.getEmail()+"\n");
        return rootView;

    }
}
