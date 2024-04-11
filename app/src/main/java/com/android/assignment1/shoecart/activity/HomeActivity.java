package com.android.assignment1.shoecart.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        MaterialToolbar materialToolbar = findViewById(R.id.homeToolBar);
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

//
//        Fragment fragment = new CartFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frames, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onSupportNavigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        showAppExitingAlert(this);
        return true;
    }

    AlertDialog.Builder alertDialog;

    public void showAppExitingAlert(final Context context) {
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setTitle(Utility.getAppNameString(context));
        alertDialog.setMessage(getString(R.string.msg_app_exit));
        alertDialog.setPositiveButton(getString(R.string.button_ok),
                (dialog, which) -> {
                    dialog.dismiss();
                    alertDialog = null;
                    Log.v(TAG, "App Exited via Alert Dialog");
                    Log.v(TAG, "from exitDialog HomeActivity called");
                    //HomeActivity.this.finishAffinity();
                    HomeActivity.this.finishAndRemoveTask();
                });
        alertDialog.setNegativeButton(getString(R.string.button_cancel), (dialog, which) -> {
            dialog.dismiss();
            alertDialog = null;
        });
        alertDialog.show();
    }
}