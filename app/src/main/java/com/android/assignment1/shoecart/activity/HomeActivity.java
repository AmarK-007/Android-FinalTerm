package com.android.assignment1.shoecart.activity;

<<<<<<< Updated upstream
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
=======
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

>>>>>>> Stashed changes
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.fragments.CartFragment;
<<<<<<< Updated upstream
import com.android.assignment1.shoecart.fragments.OrdersFragment;
import com.android.assignment1.shoecart.fragments.WishlistFragment;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
=======
import com.android.assignment1.shoecart.fragments.CategoryFragment;
import com.android.assignment1.shoecart.fragments.HomeFragment;
import com.android.assignment1.shoecart.fragments.OrdersFragment;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
>>>>>>> Stashed changes

public class HomeActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
<<<<<<< Updated upstream

    NavigationView navigationView;
    ViewFlipper viewFlipper;
    ImageView imageView;
    GridLayout newArrivalsGrid;
    GridLayout bestSellersGrid;
=======
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
        drawerItemClick();


        newArrivalsGrid = findViewById(R.id.newArrivalsGrid);
        bestSellersGrid = findViewById(R.id.bestSellersGrid);

        viewFlipper = findViewById(R.id.categoryCaraousal);
        for (int i : caraousalImages) {
            flipperCaraousalImages(i);
        }

        for (int i = 0; i < gridNewShoes.size(); i++) {

            HomeProduct product = gridNewShoes.get(i);
            CardView gridNewShoesCard = (CardView) getLayoutInflater().inflate(R.layout.custom_product_card, null);

            ImageView newShoesImage = gridNewShoesCard.findViewById(R.id.productImage);
            TextView newShoesName = gridNewShoesCard.findViewById(R.id.productName);
            TextView newShoesCost = gridNewShoesCard.findViewById(R.id.productPrice);

            gridNewShoesCard.setId(View.generateViewId());
            newShoesImage.setImageResource(product.getImageId());
            newShoesName.setText(product.getProductName());
            newShoesCost.setText(Double.toString(product.getCost()));

            newArrivalsGrid.addView(gridNewShoesCard);
        }

        for (int i = 0; i < gridBestShoes.size(); i++) {

            HomeProduct product = gridBestShoes.get(i);
            CardView gridBestShoes = (CardView) getLayoutInflater().inflate(R.layout.custom_product_card, null);

            ImageView bestShoesImage = gridBestShoes.findViewById(R.id.productImage);
            TextView bestShoesName = gridBestShoes.findViewById(R.id.productName);
            TextView bestShoesCost = gridBestShoes.findViewById(R.id.productPrice);

            gridBestShoes.setId(View.generateViewId());
            bestShoesImage.setImageResource(product.getImageId());
            bestShoesName.setText(product.getProductName());
            bestShoesCost.setText(Double.toString(product.getCost()));

            bestSellersGrid.addView(gridBestShoes);
        }
    }

    private void setDrawer() {
    }

    public void drawerItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.myProfile) {
//                   fragment = new AddFragment(employeeService);
                } else if (itemId == R.id.myOrders) {
                    fragment = new OrdersFragment();
                } else if (itemId == R.id.categories) {
//                   fragment = new UpdateFragment();
                } else if (itemId == R.id.wishList) {
                    fragment = new WishlistFragment();
                } else if (itemId == R.id.support) {
                    fragment = new CartFragment();
                } else if (itemId == R.id.about) {
//                   fragment = new ListFragment();
                } else if (itemId == R.id.logout) {
//                   fragment = new ListFragment();
                } else {

                }
                if (fragment != null) {
//                    opening or moving to a fragment
                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frames, fragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }

    public void flipperCaraousalImages(int image) {
=======
        changeFragment(new HomeFragment());

    }

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
                else if(i == R.id.whislist){

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
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======

    public void changeFragment(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.commit();
        drawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        showAppExitingAlert(this);
    }

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
=======
>>>>>>> Stashed changes
}