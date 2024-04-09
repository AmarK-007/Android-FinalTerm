package com.android.assignment1.shoecart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.databinding.ActivitySplashscreenBinding;

/**
 * SplashScreenActivity is the first activity that is shown when the app is launched.
 */
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.shoecart_splash);
        animationView.playAnimation();

        int SPLASH_TIME_OUT = 3000; // This is 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreenActivity.this, LoginScreenActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}