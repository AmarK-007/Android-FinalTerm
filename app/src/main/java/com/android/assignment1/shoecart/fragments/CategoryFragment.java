package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
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

public class CategoryFragment extends Fragment implements AdapterInterface<String> {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<Category> categories = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.categoryList);


        ProductDataSource productDataSource = new ProductDataSource(requireContext());
        List<Product> productList = productDataSource.getAllProducts();
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

        // Now distinctCategoryNames set contains unique category names
        // Print the distinct category names
        List<String> distinctCategory = new ArrayList<>();
        for (String categoryName : distinctCategoryNames) {
            System.out.println(categoryName);
            distinctCategory.add(categoryName);
        }

        adapter = new CategoryAdapter(distinctCategory, requireContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemSelected(String data, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("category", data);
//        moving to add fragment
        Fragment fragment = new ShowProductFragment();
        Log.e("TAG", data);
        //passing arguments
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemRemoved() {

    }
}



