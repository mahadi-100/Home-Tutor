package com.example.hometutor.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hometutor.AuthenticationActivity;
import com.example.hometutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class OtherFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_other, container, false);
        mAuth = FirebaseAuth.getInstance();

        TextView logout = view.findViewById(R.id.other_logout);

        ImageView call = view.findViewById(R.id.other_phone);
        ImageView email = view.findViewById(R.id.other_email);
        ImageView web = view.findViewById(R.id.other_web);

        call.setOnClickListener(v->actionCall());
        email.setOnClickListener(v->actionEmail());
        web.setOnClickListener(v->actionWeb());

        logout.setOnClickListener(v-> goBackToAuthenticationActivity());

        return view;
    }

    private void actionWeb() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sifu1906/photos_all")));
    }

    private void actionEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);

        String[] recipients ={"emrul15-11669@diu.edu.bd"};
        intent.putExtra(Intent.EXTRA_SUBJECT, "greeting");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent, "choose an email"));
    }

    private void actionCall() {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01537263616")));
    }


    private void goBackToAuthenticationActivity(){
        mAuth.signOut();
        startActivity(new Intent(getActivity(), AuthenticationActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }
}

