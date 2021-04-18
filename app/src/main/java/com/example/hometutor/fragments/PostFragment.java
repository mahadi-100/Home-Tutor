package com.example.hometutor.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hometutor.Classes.InfoClass;
import com.example.hometutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class PostFragment extends Fragment {
    private EditText edtFName, edtLName, edtGraduated, edtQualification;
    private EditText edtPhone, edtAddress, edtAbout;

    private StorageReference reference;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post, container, false);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        reference = storage.getReference();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        edtFName = view.findViewById(R.id.post_firstName);
        edtLName = view.findViewById(R.id.post_lastName);
        edtGraduated = view.findViewById(R.id.post_graduated);
        edtQualification = view.findViewById(R.id.post_equalification);
        edtPhone = view.findViewById(R.id.post_phone);
        edtAddress = view.findViewById(R.id.post_address);
        edtAbout = view.findViewById(R.id.post_someWords);

        Button enter = view.findViewById(R.id.post_btnEnter);
        enter.setOnClickListener(v->saveInfoToDatabase());

        return view;
    }

    private void addingPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imageUri = data.getData();
            uploadPicture(imageUri);
        }
    }

    private void saveInfoToDatabase() {
        String firstName = edtFName.getText().toString();
        String lastName = edtLName.getText().toString();
        String graduated = edtGraduated.getText().toString();
        String qualification = edtQualification.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String about = edtAbout.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || graduated.isEmpty() || qualification.isEmpty() ||
            phone.isEmpty() || address.isEmpty() || about.isEmpty()){
            Toast.makeText(getContext(), "empty info field found!", Toast.LENGTH_SHORT).show();
            return;
        }

        addingPicture();
        if (imageUri == null){
            return;
        }

        DatabaseReference infoReference = database.getReference("Information").
                child(Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail()).substring(0,10));

        infoReference.setValue(new InfoClass(firstName, lastName, graduated, qualification, phone, address, about,imageUri.toString()));
    }

    private void uploadPicture(Uri imageUri) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Uploading Image...");
        dialog.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference myRef = reference.child("images/" + randomKey);

        myRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Upload Done", Toast.LENGTH_SHORT).show();
                })

                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                })

                .addOnProgressListener(taskSnapshot->{
                    double progress = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    dialog.setMessage("Percentage: " + (int)progress + "%");
                });
    }
}