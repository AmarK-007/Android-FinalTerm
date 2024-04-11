package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.assignment1.shoecart.adapters.CartAdapter;
import com.android.assignment1.shoecart.databinding.FragmentCartBinding;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.ProductSize;

import java.util.ArrayList;


public class CartFragment extends Fragment implements AdapterInterface<Product> {

    FragmentCartBinding binding;
    CartAdapter adapter;
    ArrayList<Product> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);

        arrayList = new ArrayList<>();
        ArrayList<ProductSize> sizes = new ArrayList<>();
        sizes.add(new ProductSize(2, 3, 14, 2));

        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));
        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));
        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));


        adapter = new CartAdapter(arrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        binding.rvCartItem.setLayoutManager(manager);
        binding.rvCartItem.setAdapter(adapter);


        binding.btnProceed.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show();
        });


        return binding.getRoot();
    }

    @Override
    public void onItemSelected(Product data, int position) {

    }
}