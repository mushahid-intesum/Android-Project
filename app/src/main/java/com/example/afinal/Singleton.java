package com.example.afinal;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Singleton {

    Context applicationContext;
    private static final Singleton singleton = new Singleton();
//    FirebaseApp firebaseApp = FirebaseApp.initializeApp(getApplicationContext());
//    FirebaseApp.initializeApp();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();

    private Singleton() { }

    public static Singleton getSingleton()
    {
        return singleton;
    }

    public FirebaseAuth getFirebaseAuth()
    {
        return mAuth;
    }

    public FirebaseFirestore getFirestore()
    {
        return firestore;
    }

    public FirebaseUser getUser()
    {
        return mUser;
    }
}
