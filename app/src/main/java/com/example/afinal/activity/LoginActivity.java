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

import java.nio.file.Path;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailTextView;
    EditText passwordTextView;
    Button loginButton;

    TextView resetPass;
    TextView signUp;

    Singleton singleton = Singleton.getSingleton();

    String email;
    String password;

    FirebaseAuth mAuth = singleton.getFirebaseAuth();
    FirebaseUser mUser = singleton.getUser();

//    FirebaseUser mUser;
//    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindWidgets();
    }

    private void bindWidgets()
    {
        emailTextView = (EditText) findViewById(R.id.editTextEmailLoginPage);
        passwordTextView = (EditText) findViewById(R.id.editTextPasswordLoginPage);
        loginButton = (Button) findViewById(R.id.buttonLoginPage);
        resetPass = (TextView) findViewById(R.id.textViewResetPassLoginPage);
        signUp = (TextView) findViewById(R.id.textViewSignupLoginPage);

        loginButton.setOnClickListener(this);
        resetPass.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLoginPage: {
                email = emailTextView.getText().toString();
                password = passwordTextView.getText().toString();

                if (email.isEmpty()) {
                    emailTextView.setError("Email cannot be empty");
                    emailTextView.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordTextView.setError("Invalid password");
                    passwordTextView.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailTextView.setError("Invalid email. Please type in valid email");
                    emailTextView.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (mUser.isEmailVerified()) {
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                                Log.i("User", "Logged in");
                                    } else
                                        Toast.makeText(LoginActivity.this, "Account not verified. Please check your email", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(LoginActivity.this, "Login failed! Please check your credentials", Toast.LENGTH_SHORT).show();
                            }
                        });
            }break;

            case R.id.textViewResetPassLoginPage:
            {
                try {
                    startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }break;

            case R.id.textViewSignupLoginPage:
            {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        }
    }
}
