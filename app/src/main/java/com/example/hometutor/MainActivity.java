package com.example.hometutor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hometutor.fragments.HomeFragment;
import com.example.hometutor.fragments.OtherFragment;
import com.example.hometutor.fragments.PdfFragment;
import com.example.hometutor.fragments.PostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, new HomeFragment()).commit();
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.main_bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNav);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNav =
            item -> {
                Fragment fragment = null;

                if(item.getItemId() == R.id.nav_home) fragment = new HomeFragment();
                if(item.getItemId() == R.id.nav_post) fragment = new PostFragment();
                if(item.getItemId() == R.id.nav_pdf) fragment = new PdfFragment();
                if(item.getItemId() == R.id.nav_other) fragment = new OtherFragment();

                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment).commit();
                return true;
            };

}