package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.CategoryAdapter;
import com.android.assignment1.shoecart.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<Category> categories = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.categoryList);
        adapter = new CategoryAdapter(categories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Category category1 =new Category(1, R.drawable.product_blue_1, "Blue Shoes");
        Category category2 =new Category(2, R.drawable.product_grey_1, "Grey Shoes");
        Category category3 =new Category(1, R.drawable.product_yellow_1, "Yellow Shoes");
        Category category4 =new Category(1, R.drawable.product_red_1, "Red Shoes");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        return view;
    }
}



