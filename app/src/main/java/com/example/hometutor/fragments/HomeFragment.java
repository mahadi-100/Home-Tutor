package com.example.hometutor.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hometutor.Classes.FirebaseInfo;
import com.example.hometutor.R;
import com.example.hometutor.adaptor.HomeAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<FirebaseInfo> list = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.home_recycleview);

        HomeAdaptor adaptor = new HomeAdaptor(list, getContext());
        recyclerView.setAdapter(adaptor);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));
        list.add(new FirebaseInfo("Mahadi hasan", "23", "Dhaka", R.drawable.happy_face));

        //Fetch data from firebase and add to the arrayList.

        return view;
    }
}