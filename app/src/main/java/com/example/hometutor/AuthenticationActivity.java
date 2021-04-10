package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.hometutor.fragments.SignInFragment;
import com.example.hometutor.fragments.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.auth_layout, new SignInFragment()).commit();
        }

        Button signIn = findViewById(R.id.auth_btnSignIn);
        signIn.setOnClickListener(v -> gotoSpecificFragment(new SignInFragment()));

        Button signUp = findViewById(R.id.auth_btnSignUp);
        signUp.setOnClickListener(v->gotoSpecificFragment(new SignUpFragment()));
    }

    private void gotoSpecificFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.auth_layout, fragment);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
        finish();
    }
}