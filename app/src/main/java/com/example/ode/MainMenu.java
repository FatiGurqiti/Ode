package com.example.ode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ode.ui.OdeApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Toast.makeText(MainMenu.this, "Welcome " + OdeApi.getInstance().getUsername(),
                Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_main_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        ImageButton debt = (ImageButton) findViewById(R.id.debt1);
        ImageButton loan = (ImageButton) findViewById(R.id.loan1);
        ImageButton profile = (ImageButton) findViewById(R.id.profile1);
        ImageButton addpayment = (ImageButton) findViewById(R.id.addpayment);

        addpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoAddPayment();
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
    public void GoAddPayment()
    {
        Intent profileintent = new Intent(this,adddebt.class);
        startActivity(profileintent);
    }public void GoProfile()
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