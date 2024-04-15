package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.ShowProductAdapter;
import com.android.assignment1.shoecart.databinding.FragmentShowProductBinding;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ShowProductFragment extends Fragment implements AdapterInterface<Product> {

    FragmentShowProductBinding binding;
    List<Product> productList = new ArrayList<>();
    String category;

    ShowProductAdapter showProductAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowProductBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            //geting arguments
            category = getArguments().getString("category");
            Log.e("TAG", category + " 1");

        }

        productList = new ProductDataSource(requireContext()).getAllProducts();

        List<Product> categoryProduct = new ArrayList<>();

        productList.forEach(product -> {
            if (Objects.equals(product.getCategory(), category)) {
                categoryProduct.add(product);
                Log.e("TAG", categoryProduct.get(0).getCategory() + " 23");
            }
            Log.e("TAG", product.getCategory());
        });

        categoryProduct.forEach(product -> {
            Log.e("CAt", product.getTitle());
        });

        showProductAdapter = new ShowProductAdapter(categoryProduct, requireContext(), this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        binding.rvCategory.setAdapter(showProductAdapter);
        binding.rvCategory.setLayoutManager(layoutManager);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Retrieve the category from the arguments
        String category = getArguments().getString("category");

        // Set action bar title to the category
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null && category != null) {
            actionBar.setTitle(category);
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