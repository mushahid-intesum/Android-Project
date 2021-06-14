package com.example.afinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.example.afinal.Singleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameTextView;
    EditText emailTextView;
    EditText passTextView;
    Button signUpButton;

    TextView loginTextView;

    String name;
    String email;
    String password;

    Map<String, String> loginData;

    Singleton singleton = Singleton.getSingleton();

    FirebaseAuth mAuth = singleton.getFirebaseAuth();
    FirebaseUser mUser = singleton.getUser();
    FirebaseFirestore firestore = singleton.getFirestore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindWidgets();
    }

    private void bindWidgets()
    {
        nameTextView = (EditText) findViewById(R.id.editTextNameSignupPage);
        emailTextView = (EditText) findViewById(R.id.editTextEmailSignupPage);
        passTextView = (EditText) findViewById(R.id.editTextPasswordSignupPage);
        signUpButton = (Button) findViewById(R.id.buttonSignupPage);
        loginTextView = (TextView) findViewById(R.id.textViewLoginSignupPage);
        loginData = new HashMap<>();
        signUpButton.setOnClickListener(SignupActivity.this);
        loginTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignupPage: {
                name = nameTextView.getText().toString();
                email = emailTextView.getText().toString();
                password = passTextView.getText().toString();

                if (name.isEmpty()) {
                    nameTextView.setError("Name cannot be empty");
                    nameTextView.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    emailTextView.setError("Email cannot be empty");
                    emailTextView.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    passTextView.setError("Password must be greater than 6 characters");
                    passTextView.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailTextView.setError("Invalid email");
                    emailTextView.requestFocus();
                    return;
                }

                loginData.put("email", email);
                loginData.put("username", name);
                loginData.put("password", password);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mUser.sendEmailVerification();
                                    firestore.collection("Users")
                                            .document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                                            .set(loginData);
                                    loginData.clear();
                                    Toast.makeText(SignupActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(SignupActivity.this, "An account exists with this email. Please out another unused email", Toast.LENGTH_SHORT).show();
                            }
                        });
            }break;

            case R.id.textViewLoginSignupPage:
            {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }break;
        }


    }
}
