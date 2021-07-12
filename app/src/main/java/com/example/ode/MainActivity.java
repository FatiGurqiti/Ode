package com.example.ode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ode.ui.OdeApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");






    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        

        ImageView imgView=(ImageView) findViewById(R.id.rectangle);
        EditText mail = (EditText) findViewById(R.id.mainemail);
        EditText pin = (EditText) findViewById(R.id.mainpin);
        TextView account = (TextView) findViewById(R.id.noAccount);
        Button login = (Button) findViewById(R.id.SignUpBtn);

        String smail = mail.getText().toString();
        String spin = pin.getText().toString();




        mail.bringToFront();
        pin.bringToFront();
        account.bringToFront();
        login.bringToFront();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(pin.getText().toString(),mail.getText().toString());


            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                signUp();

            }
        });


    }

    public void signUp()
    {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }


    private void  login(final String email, String password){






        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(email)){
            FirebaseAuth mAuth;
            mAuth = FirebaseAuth.getInstance();


            FirebaseAuth.AuthStateListener authStateListener;

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            final String currentUserId = user.getUid();

                            collectionReference
                                    .whereEqualTo("userId", currentUserId)
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                            assert value != null;
                                            if (!value.isEmpty()) {

                                                for (QueryDocumentSnapshot snapshot : value) {
                                                    OdeApi odeApi = OdeApi.getInstance();
                                                    odeApi.setUsername(snapshot.getString("Username"));
                                                    odeApi.setUserId(snapshot.getString("userId"));


                                                    Intent intent = new Intent(MainActivity.this,MainMenu.class);
                                                    intent.putExtra("userId",currentUserId);
                                                    startActivity(intent);




                                                   // startActivity(new Intent(MainActivity.this, MainMenu.class));




                                                }  } }
                                    });
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });





        }




    }

    private void updateUI(FirebaseUser user) {

        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }


}