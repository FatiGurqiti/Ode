package com.example.ode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ode.ui.OdeApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class signup extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();

        EditText name = (EditText) findViewById(R.id.signup_name);
        Button button = (Button) findViewById(R.id.SignUpBtn);
        EditText mail = (EditText) findViewById(R.id.signup_email);
        EditText pin = (EditText) findViewById(R.id.signup_pin);
        EditText pinagain = (EditText) findViewById(R.id.signup_pinagian);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname = name.getText().toString().trim();
                String smail = mail.getText().toString().trim();
                String spin = pin.getText().toString().trim();
                String spin2 = pinagain.getText().toString().trim();
                int ipin = Integer.parseInt(spin);
                int ipin2 = Integer.parseInt(spin2);


                if (ipin == ipin2) {




                    createUserEmailAccount(smail, spin, sname);


                }
                else
                {
                    Toast.makeText(signup.this, "Pins are not he same",
                            Toast.LENGTH_LONG).show();
                }

            }



        });


    }
    private void createUserEmailAccount(String email, String password, final String username) {
        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(username) ){



            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            currentUser = firebaseAuth.getCurrentUser();
                            final String currentUserId = currentUser.getUid();


                            Map<String, String> userObj = new HashMap<>();
                            userObj.put("userId",currentUserId);
                            userObj.put("Username", username);


                            collectionReference.add(userObj)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            documentReference.get()
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                            if(Objects.requireNonNull(task.getResult()).exists())
                                                            {

                                                                String name = task.getResult()
                                                                        .getString("Username");

                                                               // OdeApi odeApi = OdeApi.getInstance();
                                                                //odeApi.setUserId(currentUserId);
                                                                //odeApi.setUsername(name);

                                                                Intent intent = new Intent(signup.this,MainActivity.class);
                                                                intent.putExtra("Username",name);
                                                                intent.putExtra("userId",currentUserId);
                                                                startActivity(intent);

                                                            }
                                                        }
                                                    });
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
        else{

        }
    }

}