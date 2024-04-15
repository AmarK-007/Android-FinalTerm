package com.android.assignment1.shoecart.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.fragments.AboutFragment;
import com.android.assignment1.shoecart.fragments.CartFragment;
import com.android.assignment1.shoecart.fragments.OrdersFragment;
import com.android.assignment1.shoecart.fragments.ProfileFragment;
import com.android.assignment1.shoecart.fragments.SupportFragment;
import com.android.assignment1.shoecart.fragments.WishlistFragment;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import com.android.assignment1.shoecart.fragments.CategoryFragment;
import com.android.assignment1.shoecart.fragments.HomeFragment;
import com.android.assignment1.shoecart.fragments.OrdersFragment;
import com.android.assignment1.shoecart.fragments.WishlistFragment;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavigationView navDrawerMenu;
    TextView userName;

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        materialToolbar = findViewById(R.id.homeToolBar);
        drawerLayout = findViewById(R.id.drawerMenu);
        navigationView = findViewById(R.id.navMenu);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, materialToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer();
        changeFragment(new HomeFragment());
        setUsernameInDrawer();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frames);
                updateTitle(currentFragment);
            }
        });
    }

    private void setDrawer() {
        navDrawerMenu = findViewById(R.id.navMenu);

        navDrawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int i = item.getItemId();

                if (i == R.id.myProfile) {
                    changeFragment(new ProfileFragment());
                } else if (i == R.id.myOrders) {
                    changeFragment(new OrdersFragment());
                } else if (i == R.id.categories) {
                    changeFragment(new CategoryFragment());
                } else if (i == R.id.wishList) {
                    changeFragment(new WishlistFragment());
                } else if (i == R.id.support) {
                    changeFragment(new SupportFragment());
                } else if (i == R.id.about) {
                    changeFragment(new AboutFragment());
                } else if (i == R.id.logout) {
                    showAppExitingAlertLogout(HomeActivity.this);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            changeFragment(new HomeFragment());
        } else if (id == R.id.search) {

        } else if (id == R.id.cart) {
            changeFragment(new CartFragment());
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frames);
        if (currentFragment instanceof HomeFragment) {
            showAppExitingAlert(this);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void updateTitle(Fragment fragment) {
        String title = "";

        if (fragment instanceof ProfileFragment) {
            title = "Profile";
        } else if (fragment instanceof OrdersFragment) {
            title = "Orders";
        } else if (fragment instanceof CategoryFragment) {
            title = "Categories";
        } else if (fragment instanceof WishlistFragment) {
            title = "Wishlist";
        } else if (fragment instanceof SupportFragment) {
            title = "Support";
        } else if (fragment instanceof AboutFragment) {
            title = "About";
        } else if (fragment instanceof HomeFragment) {
            title = getResources().getString(R.string.app_name);
        } else if (fragment instanceof CartFragment) {
            title = "Cart";
        }

        if (!title.isEmpty()) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void changeFragment(Fragment fragment) {
        updateTitle(fragment);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null); // Add this line
        fragmentTransaction.commit();
        drawerLayout.closeDrawers();
    }

    AlertDialog.Builder alertDialog;

    public void showAppExitingAlert(final Context context) {
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setCustomTitle(Utility.showStyledAlertDialog(context));
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

    public void setUsernameInDrawer(){
        navDrawerMenu = findViewById(R.id.navMenu);
        View navHeaderView = navDrawerMenu.getHeaderView(0);
        userName= navHeaderView.findViewById(R.id.welcomeUser);
        SharedPreferences sharedPreferences = getSharedPreferences("Login_Username", Context.MODE_PRIVATE);
        String getUsername = sharedPreferences.getString("userName", "");
        userName.setText("Welcome, " + getUsername);
    }

    public void showAppExitingAlertLogout(final Context context) {
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setCustomTitle(Utility.showStyledAlertDialog(context));
        alertDialog.setMessage(getString(R.string.msg_app_exit));
        alertDialog.setPositiveButton(getString(R.string.button_ok),
                (dialog, which) -> {
                    dialog.dismiss();
                    alertDialog = null;
                    Log.v(TAG, "App Exited via Alert Dialog");
                    Log.v(TAG, "from exitDialog HomeActivity called");
                    Intent intent = new Intent(this, LoginScreenActivity.class);
                    startActivity(intent);
                    finish();
                });
        alertDialog.setNegativeButton(getString(R.string.button_cancel), (dialog, which) -> {
            dialog.dismiss();
            alertDialog = null;
        });
        alertDialog.show();
    }
}