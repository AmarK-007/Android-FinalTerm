package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.HomeRecyclerViewAdapter;
import com.android.assignment1.shoecart.models.HomeProduct;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    ImageView imageView;
    RecyclerView recyclerViewNewArrivals;
    RecyclerView recyclerViewBestSellers;

    List<HomeProduct> gridNewShoes = new ArrayList<>();
    List<HomeProduct> gridBestShoes = new ArrayList<>();
    int[] carousalImages = {R.drawable.product_blue_1, R.drawable.product_white_blue_1, R.drawable.product_grey_3};
    boolean imagesAdded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

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


        recyclerViewNewArrivals = rootView.findViewById(R.id.recyclerView_newArrivals);
        recyclerViewBestSellers = rootView.findViewById(R.id.recyclerView_bestSellers);

        LinearLayoutManager layoutManagerNewArrivals = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNewArrivals.setLayoutManager(layoutManagerNewArrivals);
        HomeRecyclerViewAdapter adapterNewArrivals = new HomeRecyclerViewAdapter(gridNewShoes);
        recyclerViewNewArrivals.setAdapter(adapterNewArrivals);


        GridLayoutManager layoutManagerBestSellers = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerViewBestSellers.setLayoutManager(layoutManagerBestSellers);
        HomeRecyclerViewAdapter adapterBestSellers = new HomeRecyclerViewAdapter(gridBestShoes);
        recyclerViewBestSellers.setAdapter(adapterBestSellers);

        viewFlipper = rootView.findViewById(R.id.categoryCaraousal);
        addImagesToFlipper();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void addImagesToFlipper() {
        viewFlipper.removeAllViews();
        for (int i : carousalImages) {
            ImageView imageView = new ImageView(requireContext());
            imageView.setImageResource(i);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        startFlipping();
    }

    private void startFlipping() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!viewFlipper.isAttachedToWindow()) return;
                    int nextChildIndex = (viewFlipper.getDisplayedChild() + 1) % viewFlipper.getChildCount();
                    viewFlipper.setDisplayedChild(nextChildIndex);
                    startFlipping();
                }
            }, 2000);
        }
    }

}
