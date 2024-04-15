package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.CategoryAdapter;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Category;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// CategoryFragment class that extends Fragment and implements AdapterInterface
public class CategoryFragment extends Fragment implements AdapterInterface<String> {

    // Declare variables
    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<Category> categories = new ArrayList<>();

    // onCreateView method for creating the view of the fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.categoryList);

        // Get all products
        ProductDataSource productDataSource = new ProductDataSource(requireContext());
        List<Product> productList = productDataSource.getAllProducts();

        // Create a list of categories from the products
        List<Category> category = new ArrayList<>();
        productList.forEach(product -> {
            category.add(new Category(0, product.getImages().get(0).getImageUrl(), product.getCategory()));
        });

        // Create a Set to store unique category names
        Set<String> distinctCategoryNames = new HashSet<>();

        // Iterate over the list of categories and add each category name to the set
        for (Category item : category) {
            distinctCategoryNames.add(item.getCategoryName());
        }

        // Create a list of distinct categories
        List<String> distinctCategory = new ArrayList<>();
        for (String categoryName : distinctCategoryNames) {
            System.out.println(categoryName);
            distinctCategory.add(categoryName);
        }

        // Initialize adapter and set it to the RecyclerView
        adapter = new CategoryAdapter(distinctCategory, requireContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Return the root view
        return view;
    }

    // Method to handle item selection
    @Override
    public void onItemSelected(String data, int position) {
        // Create a bundle and put the selected category in it
        Bundle bundle = new Bundle();
        bundle.putString("category", data);

        // Create a new instance of ShowProductFragment and set the arguments
        Fragment fragment = new ShowProductFragment();
        Log.e("TAG", data);
        fragment.setArguments(bundle);

        // Replace the current fragment with ShowProductFragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        // Set action bar title to the selected category
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(data);
        }
    }

    // Method to handle item removal
    @Override
    public void onItemRemoved() {
        // Implementation here...
    }
}