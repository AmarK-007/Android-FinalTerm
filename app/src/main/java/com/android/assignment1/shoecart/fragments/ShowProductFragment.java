package com.android.assignment1.shoecart.fragments;

// Import necessary libraries and packages
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

// ShowProductFragment class that extends Fragment and implements AdapterInterface
public class ShowProductFragment extends Fragment implements AdapterInterface<Product> {

    // Declare variables
    FragmentShowProductBinding binding;
    List<Product> productList = new ArrayList<>();
    String category;
    String searchQuery;
    ShowProductAdapter showProductAdapter;

    // onCreate method for initializing fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateView method for creating the view of the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowProductBinding.inflate(inflater, container, false);

        // Check if arguments are passed to the fragment
        if (getArguments() != null) {
            // Retrieve the arguments
            category = getArguments().getString("category");
            searchQuery = getArguments().getString("searchQuery");
        }

        // Get all products
        productList = new ProductDataSource(requireContext()).getAllProducts();
        List<Product> filteredProduct = new ArrayList<>();

        // Filter products based on the search query or category
        if (searchQuery != null) {
            productList.forEach(product -> {
                if (product.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredProduct.add(product);
                }
            });
        } else {
            productList.forEach(product -> {
                if (Objects.equals(product.getCategory(), category)) {
                    filteredProduct.add(product);
                }
            });
        }

        // Initialize the adapter and set it to the RecyclerView
        showProductAdapter = new ShowProductAdapter(filteredProduct, requireContext(), this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvCategory.setAdapter(showProductAdapter);
        binding.rvCategory.setLayoutManager(layoutManager);

        // Return the root view
        return binding.getRoot();
    }

    // onResume method to set the action bar title to the category
    @Override
    public void onResume() {
        super.onResume();
        String category = getArguments().getString("category");
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null && category != null) {
            actionBar.setTitle(category);
        }
    }

    // Method to handle item selection
    @Override
    public void onItemSelected(Product data, int position) {
        // Create a bundle and put the selected product in it
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", data);

        // Create a new instance of ProductDetailsFragment and set the arguments
        Fragment fragment = new ProductDetailsFragment();
        fragment.setArguments(bundle);

        // Replace the current fragment with ProductDetailsFragment
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

    // Method to handle item removal
    @Override
    public void onItemRemoved() {
        // Implementation here...
    }
}