package com.example.ode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ode.ui.OdeApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class adddebt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddebt);


        EditText ammount = (EditText) findViewById(R.id.ammount);
        EditText label = (EditText) findViewById(R.id.label);
        EditText onotxt = (EditText) findViewById(R.id.onotxt);
        Button addbtn = (Button) findViewById(R.id.addcontactbtn);


        ammount.bringToFront();
        label.bringToFront();
        onotxt.bringToFront();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                OdeApi odeApi = OdeApi.getInstance();


                // Create a new user with a first and last name
                Map<String, Object> debt = new HashMap<>();
                debt.put("ONO", odeApi.getUserId());
                debt.put("to", onotxt.getText().toString());
                debt.put("label", label.getText().toString());
                debt.put("ammount", Double.parseDouble(ammount.getText().toString()));
                debt.put("date",new Timestamp(new Date()));

// Add a new document with a generated ID
                db.collection("debt")
                        .add(debt)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Succesfull", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(adddebt.this, "Debt added successfully",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Failure", "Error adding document", e);
                            }
                        });

            }
        });
    }
}