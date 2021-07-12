package com.example.ode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ode.ui.OdeApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton debt = (ImageButton) findViewById(R.id.debt1);
        ImageButton loan = (ImageButton) findViewById(R.id.loan1);
        ImageButton profile = (ImageButton) findViewById(R.id.profile1);
        ImageButton logout = (ImageButton) findViewById(R.id.logout);

        TextView name = (TextView) findViewById(R.id.name);
        TextView ono = (TextView) findViewById(R.id.onoprofile);

        OdeApi odeApi = OdeApi.getInstance();

        name.setText(odeApi.getUsername());
        ono.setText(odeApi.getUserId());



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Logout();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoProfile();
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoLoan();
            }
        });
        debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoDebt();
            }
        });



    }
    public void GoViewContact()
    {
        Intent profileintent = new Intent(this,viewcontact.class);
        startActivity(profileintent);
    }
    public void Logout()
    {

         FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        OdeApi odeApi = OdeApi.getInstance();
        odeApi.setUsername(" ");
        odeApi.setUserId(" ");

        Intent profileintent = new Intent(this,MainActivity.class);
        startActivity(profileintent);
    }
    public void GoAddContact()
    {
        Intent profileintent = new Intent(this,addcontact.class);
        startActivity(profileintent);
    }

    public void GoProfile()
    {
        Intent profileintent = new Intent(this,profile.class);
        startActivity(profileintent);
    }
    public void GoDebt()
    {
        Intent profileintent = new Intent(this,MainMenu.class);
        startActivity(profileintent);
    }
    public void GoLoan()
    {
        Intent profileintent = new Intent(this,loan.class);
        startActivity(profileintent);
    }
}