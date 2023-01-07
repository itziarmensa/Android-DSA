package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.grupo3.androiddsa.domain.User;

public class MainUsersDetails extends AppCompatActivity {
    TextView name;
    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_main);
        name=findViewById(R.id.name_user);
        details=findViewById(R.id.details);
        User user=(User) getIntent().getSerializableExtra("Details");
        name.setText(user.getUserName());
        details.setText("Name: "+user.getUserName()+"\n"+
                "Surname: "+user.getUserSurname()+"\n"+
                "Birth Date: "+user.getUserBirth()+"\n"+
                "Email: "+user.getEmail()+"\n");
    }
}
