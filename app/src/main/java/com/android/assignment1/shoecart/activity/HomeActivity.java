package com.android.assignment1.shoecart.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.utils.Utility;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;
    ImageView imageView;
    GridLayout newArrivalsGrid;
    GridLayout bestSellersGrid;

    List<HomeProduct> gridNewShoes = new ArrayList<>();
    List<HomeProduct> gridBestShoes = new ArrayList<>();
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int caraousalImages[] = {R.drawable.shoe1, R.drawable.shoe2, R.drawable.shoe3};

        HomeProduct product1 = new HomeProduct(R.drawable.product_blue_1, "Blue Shoes", 122.99);
        HomeProduct product2 = new HomeProduct(R.drawable.product_grey_1, "Grey Shoes", 122.99);
        HomeProduct product3 = new HomeProduct(R.drawable.product_red_1, "Red Shoes", 122.99);
        HomeProduct product4 = new HomeProduct(R.drawable.product_white_blue_1, "White Shoes", 122.99);

        gridNewShoes.add(product1);
        gridNewShoes.add(product2);
        gridNewShoes.add(product3);
        gridNewShoes.add(product4);

        gridBestShoes.add(product1);
        gridBestShoes.add(product2);
        gridBestShoes.add(product3);
        gridBestShoes.add(product4);

        materialToolbar = findViewById(R.id.homeToolBar);
        drawerLayout = findViewById(R.id.drawerMenu);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, materialToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer();

        newArrivalsGrid = findViewById(R.id.newArrivalsGrid);
        bestSellersGrid = findViewById(R.id.bestSellersGrid);

        viewFlipper = findViewById(R.id.categoryCaraousal);
        for (int i: caraousalImages) {
            flipperCaraousalImages(i);
        }

        for(int i = 0; i < gridNewShoes.size(); i++){

            HomeProduct product = gridNewShoes.get(i);
            CardView gridNewShoesCard =(CardView) getLayoutInflater().inflate(R.layout.custom_product_card, null);

            ImageView newShoesImage = gridNewShoesCard.findViewById(R.id.productImage);
            TextView newShoesName = gridNewShoesCard.findViewById(R.id.productName);
            TextView newShoesCost = gridNewShoesCard.findViewById(R.id.productPrice);

            gridNewShoesCard.setId(View.generateViewId());
            newShoesImage.setImageResource(product.getImageId());
            newShoesName.setText(product.getProductName());
            newShoesCost.setText(Double.toString(product.getCost()));

            newArrivalsGrid.addView(gridNewShoesCard);
        }

        for(int i = 0; i < gridBestShoes.size(); i++){

            HomeProduct product = gridBestShoes.get(i);
            CardView gridBestShoes =(CardView) getLayoutInflater().inflate(R.layout.custom_product_card, null);

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

    public void flipperCaraousalImages(int image){

        imageView = findViewById(R.id.caraousalImage);
        imageView.setBackgroundResource(image);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    @Override
    public void onBackPressed() {
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