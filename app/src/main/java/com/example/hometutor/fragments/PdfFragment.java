package com.example.hometutor.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hometutor.R;

public class PdfFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pdf, container, false);

        String[] courses = {"Java", "Spring Boot", "JavaFx", "Android", "Game Development",
                             "PHP", "Html", "JavaScript", "React Native"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, courses);

        ListView listView = view.findViewById(R.id.pdf_listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> actionUponPosition(position));

        return view;
    }

    private void actionUponPosition(int position) {
        String gotToWebsite = "https://www.udemy.com/";

        switch (position){
            case 0:
                gotToDesiredWebsite("Going to java tutorial", "https://cutt.ly/Dc2Uc7E");
                break;

            case 1:
                gotToDesiredWebsite("Going to Spring boot tutorial", "https://cutt.ly/gc2UIPu");
                break;

            case 2:
                gotToDesiredWebsite("Going to JavaFx Tutorial", "https://cutt.ly/fc2PI9i");
                break;

            case 3:
                gotToDesiredWebsite("Downloading android pdf", "https://cutt.ly/mc2AyeA");
                break;

            case 4:
                gotToDesiredWebsite("Downloading game development pdf", "https://cutt.ly/Dc2ARCC");
                break;

            case 5:
                gotToDesiredWebsite("Downloading PHP pdf", "https://cutt.ly/cc2Mijr");
                break;

            case 6:
                gotToDesiredWebsite("Going to Html tutorial", "https://cutt.ly/sc2MQvW");
                break;

            case 7:
                gotToDesiredWebsite("Going to JavaScript tutorial", "https://cutt.ly/6c20fF8");
                break;

            case 8:
                gotToDesiredWebsite("Going to React native tutorial", "https://cutt.ly/dc20WM8");
                break;

            default:
                Toast.makeText(getContext(), "Noting selected", Toast.LENGTH_SHORT).show();

        }
    }

    private void gotToDesiredWebsite(String toast, String website){
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(website)));
    }
}