package com.example.afinal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.Singleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    Button signupButton;
    Button loginButton;
    Button exploreButton;

    Singleton singleton = Singleton.getSingleton();

    FirebaseUser mUser = singleton.getUser();
    FirebaseAuth mAuth = singleton.getFirebaseAuth();
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(LandingActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();
                    Log.i("Log in", "Already logged in!");
                }
            }
        };

        bindWidgets();
    }
    private void bindWidgets()
    {
        signupButton = (Button) findViewById(R.id.buttonSignupLandingPage);
        loginButton = (Button) findViewById(R.id.buttonLoginLandingPage);
        exploreButton = (Button) findViewById(R.id.buttonExploreLandingPage);

        signupButton.setOnClickListener(LandingActivity.this);
        loginButton.setOnClickListener(LandingActivity.this);
        exploreButton.setOnClickListener(LandingActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.buttonExploreLandingPage:
            {
                startActivity(new Intent(LandingActivity.this, NavigationActivity.class));
            }break;

            case R.id.buttonLoginLandingPage:
            {
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
            }break;

            case R.id.buttonSignupLandingPage:
            {
                startActivity(new Intent(LandingActivity.this, SignupActivity.class));
            }break;
        }
    }
}