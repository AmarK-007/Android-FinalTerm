package com.android.assignment1.shoecart.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.utils.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.shoecart_orderplaced);
        animationView.playAnimation();

        new CopyDatabaseTask().execute();

        int SPLASH_TIME_OUT = 8000; // This is 8 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utility.getUser(SplashScreenActivity.this) == null) {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, LoginScreenActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private class CopyDatabaseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String dbPath = getApplicationContext().getDatabasePath("shoecart.db").getPath();

            File file = new File(dbPath);
            if (file.exists()) {
                Log.d(TAG, "Database already exists. No need to copy.");
                return null;
            }

            try {
                Log.d(TAG, "Copying database from assets...");

                InputStream in = getAssets().open("shoecart.db");
                OutputStream out = new FileOutputStream(dbPath);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                out.flush();
                out.close();
                in.close();

                Log.d(TAG, "Database copied successfully.");
            } catch (IOException e) {
                Log.e(TAG, "Error copying database", e);
            }

            return null;
        }

    }
}