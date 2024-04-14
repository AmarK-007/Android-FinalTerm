package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.models.HomeProduct;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    ImageView imageView;
    GridLayout newArrivalsGrid;
    GridLayout bestSellersGrid;

    List<HomeProduct> gridNewShoes = new ArrayList<>();
    List<HomeProduct> gridBestShoes = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        int caraousalImages[] = {R.drawable.product_blue_1, R.drawable.product_white_blue_1, R.drawable.product_grey_3};

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


        newArrivalsGrid = rootView.findViewById(R.id.newArrivalsGrid);
        bestSellersGrid= rootView.findViewById(R.id.bestSellersGrid);

        viewFlipper = rootView.findViewById(R.id.categoryCaraousal);
        for (int i: caraousalImages) {
            ImageView imageView = new ImageView(requireContext());
            imageView.setImageResource(i);
            viewFlipper.addView(imageView);
        }

        viewFlipper.startFlipping();

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
        // Inflate the layout for this fragment
        return rootView;
    }

}