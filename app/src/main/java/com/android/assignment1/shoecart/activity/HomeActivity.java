package com.android.assignment1.shoecart.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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
import com.android.assignment1.shoecart.fragments.CartFragment;
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
    ViewFlipper viewFlipper;
    ImageView imageView;
    NavigationView navDrawerMenu;

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

    }



//    public void drawerItemClick() {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                int itemId = item.getItemId();
//                if (itemId == R.id.myProfile) {
////                   fragment = new AddFragment(employeeService);
//                } else if (itemId == R.id.myOrders) {
//                    fragment = new OrdersFragment();
//                } else if (itemId == R.id.categories) {
////                   fragment = new UpdateFragment();
//                } else if (itemId == R.id.wishList) {
//                    fragment = new WishlistFragment();
//                } else if (itemId == R.id.support) {
//                    fragment = new CartFragment();
//                } else if (itemId == R.id.about) {
////                   fragment = new ListFragment();
//                } else if (itemId == R.id.logout) {
////                   fragment = new ListFragment();
//                } else {
//
//                }
//                if (fragment != null) {
////                    opening or moving to a fragment
//                    FragmentManager supportFragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.frames, fragment);
//                    fragmentTransaction.commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                return false;
//            }
//        });
//    }

    private void setDrawer() {
        navDrawerMenu = findViewById(R.id.navMenu);
        navDrawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int i = item.getItemId();

                if(i == R.id.myProfile){

                }
                else if(i == R.id.myOrders){
                    changeFragment(new OrdersFragment());
                }
                else if(i == R.id.categories){
                    changeFragment(new CategoryFragment());
                }
                else if(i == R.id.wishList){
                    changeFragment(new WishlistFragment());
                }
                else if(i == R.id.support){

                }
                else if(i == R.id.about){

                }
                else if(i == R.id.logout){
                    onBackPressed();
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

        if(id == R.id.home){
            changeFragment(new HomeFragment());
        }
        else if(id == R.id.search){

        } else if(id == R.id.cart){
            changeFragment(new CartFragment());
        }

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showAppExitingAlert(this);
    }


    public void changeFragment(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.commit();
        drawerLayout.closeDrawers();
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