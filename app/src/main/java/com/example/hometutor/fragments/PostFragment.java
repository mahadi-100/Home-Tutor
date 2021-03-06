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
import android.widget.ImageView;
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
    private EditText edtName, edtAge, edtGraduate, edtEducation;
    private EditText edtPhone, edtAddress, edtAbout;
    private ImageView image;

    private StorageReference reference;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    private Uri imageUri = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        reference = storage.getReference();


        edtName= view.findViewById(R.id.post_name);
        edtAge = view.findViewById(R.id.post_age);
        edtGraduate = view.findViewById(R.id.post_graduate);
        edtEducation = view.findViewById(R.id.post_qualification);
        edtPhone = view.findViewById(R.id.post_phone);
        edtAddress= view.findViewById(R.id.post_address);
        edtAbout = view.findViewById(R.id.post_about);

        image = view.findViewById(R.id.post_image);
        image.setOnClickListener(v-> addingPicture());

        Button enter = view.findViewById(R.id.post_enter);
        enter.setOnClickListener(v-> saveInfoToDatabase());

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
            image.setImageURI(imageUri);
        }
    }

    private void saveInfoToDatabase() {
        String name = edtName.getText().toString().trim();
        String age = edtAge.getText().toString().trim();
        String graduate = edtGraduate.getText().toString();
        String education = edtEducation.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String about = edtAbout.getText().toString();

        if (name.isEmpty() || age.isEmpty() || graduate.isEmpty() || education.isEmpty()
            || phone.isEmpty() || address.isEmpty() || about.isEmpty()){
            Toast.makeText(getContext(), "empty input field found", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri == null){
            Toast.makeText(getContext(), "Image not selected", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference infoReference = database.getReference("Information").
                child(Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail()).substring(0,10));

        infoReference.setValue(new InfoClass(name, age, graduate, education, phone, address, about, imageUri.toString()));

        uploadPicture(imageUri);
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
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                })

                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                })

                .addOnProgressListener(taskSnapshot->{
                    double progress = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    dialog.setMessage("Percentage: " + (int)progress + "%");
                });
    }
}