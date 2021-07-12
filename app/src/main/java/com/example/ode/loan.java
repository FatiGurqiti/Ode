package com.example.ode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ode.ui.OdeApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class loan extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference debtref = db.collection("debt");
    private DocumentReference debtonoref = db.collection("debt").document("ONO");

    private  TextView context;
    private  TextView date;
    private  TextView settle;
    private  String label,to,ammount,sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        ImageButton debt = (ImageButton) findViewById(R.id.debt1);
        ImageButton loan = (ImageButton) findViewById(R.id.loan1);
        ImageButton profile = (ImageButton) findViewById(R.id.profile1);




         context = (TextView) findViewById(R.id.loanContext);
         date = (TextView) findViewById(R.id.date);

        Button btnShow = new Button(this);
        btnShow.setText("DINAMIC BUTTON");
        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(loan.this, "woohoo", Toast.LENGTH_LONG).show();
            }
        });


         OdeApi odeApi = OdeApi.getInstance();
        Task<QuerySnapshot> query = debtref.whereEqualTo("ONO", odeApi.getUserId().toString())
                .get().addOnCompleteListener
                        (new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()){
                                        String to,slabel,sentence,sdate;
                                        double sammount;


                                        to = (document.getString("to"));
                                        sdate = (document.getTimestamp("date").toDate().toString());
                                        slabel = document.getString("label");
                                        sammount = document.getDouble("ammount");





                                        sentence = to + " owes you " + String.valueOf(sammount) +  " HRK with label of: " +slabel;

                                        date.setText(sdate);
                                        context.setText(sentence);


                                    }
                                }
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
    public void ListLoan()
    {

        OdeApi odeApi = OdeApi.getInstance();

        Task<QuerySnapshot> query = debtref.whereEqualTo("ONO", odeApi.getUserId())
                .get().addOnCompleteListener
                        (new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()){





                                      //  label.setText(document.getString("label"));
                                      //  date.setText(document.getString("date"));
                                       // ammount.setText(document.getString("ammount"));
                                      //  to.setText(document.getString("to"));

                                        sentence = to + "owe's you" + ammount + "with label : " + label;






                                    }
                                }
                            }
                        });
    }
}