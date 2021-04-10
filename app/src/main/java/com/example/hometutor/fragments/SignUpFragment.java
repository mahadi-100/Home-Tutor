package com.example.hometutor.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hometutor.MainActivity;
import com.example.hometutor.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpFragment extends Fragment {
    private EditText edtEmail, edtPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = view.findViewById(R.id.signup_email);
        edtPassword = view.findViewById(R.id.signup_password);
        progressBar = view.findViewById(R.id.signup_progressbar);

        Button signUp = view.findViewById(R.id.signup_button);
        signUp.setOnClickListener(v->checkInputValidity());
        return view;
    }

    private void checkInputValidity() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Invalid email");
            edtEmail.requestFocus();
            return;
        }

        if(password.length() < 6){
            edtPassword.setError("at least 6 characters");
            edtPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        createNewAccount(email, password);
    }

    private void createNewAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Account already exist", Toast.LENGTH_SHORT).show();
                    }
                    goToMainActivity();
                    progressBar.setVisibility(View.GONE);
                });
    }

    private void goToMainActivity(){
        startActivity(new Intent(getActivity(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }
}