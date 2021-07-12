# odeMobile

**Öde** is an android project made for the Mobile Developement class in VSITE university.

## Technologies

- Java
- Android Studio
- FireBase
- XML

### Usage of the app

![Image of SignIn](https://github.com/FatiGurqiti/odeMobile/blob/master/images/1.bmp)

On the opening page of the app you should see the log in page.If you don't have an account you can simply click on 'Don't have an account ?' and register on the opened page.
_E-mail_ will not accept any given input if it isn't on correct e-mail format.
Also _Pin_ will not accept any character or special symbol.

Once you complete all the input,program will check if they are all in correct format.If given e-mail adress does not exist on the _FireBase_,your info's will be saved to the database.In this process, firebase also creates an unique user ID for you.This will also be used in many other parts of the program.

Once you successfully create your account,the app should automatically direct you to the Log in page.Here,you can enter your informations and click on the _Sign in_ button.
If you successfully log in,you will be directed to the main page of the app with a greeting text on the bottom.

In this area app uses a local **API**.The task of the API is to take your user name and user ID through the program so that the program can access your needed data such as _username_ or _userID_.

If you click on the _Add Payement_ button you will be directed to the page which you add your payments.In here you can enter needed informations and once you click on the _ADD_ button the program will use the API to get your userID and save it to Firebase collection.

``
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                OdeApi odeApi = OdeApi.getInstance();
         
                // Create a new user with a first and last name
                Map<String, Object> debt = new HashMap<>();
                debt.put("ONO", odeApi.getUserId());
                debt.put("to", onotxt.getText().toString());
                debt.put("label", label.getText().toString());
                debt.put("ammount", Double.parseDouble(ammount.getText().toString()));
                debt.put("date",new Timestamp(new Date()));
``

In order to add these to the databse it is needed to specify the collection.

```     db.collection("debt")
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


```

Once it's successfully done,you can view the database on the console of Firebase:

<br>

![Image of FireBase](https://github.com/FatiGurqiti/odeMobile/blob/master/images/2.bmp)

If you get back to the main page and click on the middle button on the bottom menu,you can view the debt that you recently added.
It also shows the added date on the bottom of the text so that it give the user a better idea when this event happened.

![Image of Debt](https://github.com/FatiGurqiti/odeMobile/blob/master/images/3.bmp)

If you click on the right button on the right menu,you will be directed to the _Profile_ page.Here you can view your userID or Log out.

Log out button uses the _FirebaseAuth_ in order to sign out the user.

`
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth.signOut();
`

It is also require to set the values of the API to _null_ in order to avoid getting informations of the same user next time.

`
  OdeApi odeApi = OdeApi.getInstance();
        odeApi.setUsername(null);
        odeApi.setUserId(null);
`

After all these process,the program simply opens the log in activity

`
 Intent profileintent = new Intent(this,MainActivity.class);
        startActivity(profileintent);
`
