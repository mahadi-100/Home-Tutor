package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade.setDuration(2000);

        textView = findViewById(R.id.splash_textView);
        textView.setAnimation(fade);

        goToMainActivity();
    }

    private void goToMainActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Pair<View, String> pair = new Pair<>(textView, "app_name");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pair);

            Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
            startActivity(intent, options.toBundle());
            finish();

        },3000);
    }
}