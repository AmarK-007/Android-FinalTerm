package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements AdapterInterface<Product> {

    ViewFlipper viewFlipper;
    ImageView imageView;
    RecyclerView recyclerViewNewArrivals;
    RecyclerView recyclerViewBestSellers;

    List<Product> productList = new ArrayList<>();

    List<Product> gridNewShoes = new ArrayList<>();
    List<Product> gridBestShoes = new ArrayList<>();
    int[] carousalImages = {R.drawable.product_blue_1, R.drawable.product_white_blue_1, R.drawable.product_grey_3};
    boolean imagesAdded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        gridNewShoes = new ArrayList<>();
        gridBestShoes = new ArrayList<>();


        productList = new ProductDataSource(requireContext()).getAllProducts();



        productList.forEach(product -> {
            if (Objects.equals(product.getCategory(), "Sports Shoes")) {
                gridBestShoes.add(product);
            } else if (Objects.equals(product.getCategory(), "Trekking Shoes") || Objects.equals(product.getCategory(), "Formal shoes")) {
                    gridNewShoes.add(product);
            }
            Log.e("TAG", product.getCategory());
        });


        recyclerViewNewArrivals = rootView.findViewById(R.id.recyclerView_newArrivals);
        recyclerViewBestSellers = rootView.findViewById(R.id.recyclerView_bestSellers);

        LinearLayoutManager layoutManagerNewArrivals = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNewArrivals.setLayoutManager(layoutManagerNewArrivals);
        HomeRecyclerViewAdapter adapterNewArrivals = new HomeRecyclerViewAdapter(gridNewShoes,requireContext(),this);
        recyclerViewNewArrivals.setAdapter(adapterNewArrivals);


        GridLayoutManager layoutManagerBestSellers = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerViewBestSellers.setLayoutManager(layoutManagerBestSellers);
        HomeRecyclerViewAdapter adapterBestSellers = new HomeRecyclerViewAdapter(gridBestShoes,requireContext(),this);
        recyclerViewBestSellers.setAdapter(adapterBestSellers);

        viewFlipper = rootView.findViewById(R.id.categoryCaraousal);
        addImagesToFlipper();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void addImagesToFlipper() {
        viewFlipper.removeAllViews();

        productList.forEach(product -> {
            ImageView imageView = new ImageView(requireContext());
            imageView.setImageResource(Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), requireContext()));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("product", product);
//        moving to add fragment
                Fragment fragment = new ProductDetailsFragment();

                //passing arguments
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frames, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                // Set action bar title to the selected product's title
                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(product.getTitle());
                }
            });
            viewFlipper.addView(imageView);
        });
//        for (int i = 0 ; i < productList.size(); i++){
//            ImageView imageView = new ImageView(requireContext());
//            imageView.setImageResource(i);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("product", productList.get(i));
////        moving to add fragment
//                Fragment fragment = new ProductDetailsFragment();
//
//                //passing arguments
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frames, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//                // Set action bar title to the selected product's title
//                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//                if (actionBar != null) {
//                    actionBar.setTitle(productList.get(i).getTitle());
//                }
//            });
//            viewFlipper.addView(imageView);
//        }
//        for (int i : carousalImages) {
//            ImageView imageView = new ImageView(requireContext());
//            imageView.setImageResource(i);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("product", productList.get(i));
////        moving to add fragment
//                Fragment fragment = new ProductDetailsFragment();
//
//                //passing arguments
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frames, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//                // Set action bar title to the selected product's title
//                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//                if (actionBar != null) {
//                    actionBar.setTitle(productList.get(i).getTitle());
//                }
//            });
//            viewFlipper.addView(imageView);
//        }
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

    @Override
    public void onItemSelected(Product data, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", data);
//        moving to add fragment
        Fragment fragment = new ProductDetailsFragment();

        //passing arguments
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        // Set action bar title to the selected product's title
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(data.getTitle());
        }
    }

    @Override
    public void onItemRemoved() {

    }
}
