package com.example.edufree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class LayoutActivity extends AppCompatActivity {

    private int waitTime  = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.auth_frame_container, new SplashScreenFragment())
                .commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.auth_frame_container, new WelcomeFragment())
                        .commit();
            }
        }, waitTime);

    }
}