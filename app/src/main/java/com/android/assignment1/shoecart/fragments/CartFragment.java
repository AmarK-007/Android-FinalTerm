package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.CartAdapter;
import com.android.assignment1.shoecart.databinding.FragmentCartBinding;
import com.android.assignment1.shoecart.db.CartDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.ProductSize;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment implements AdapterInterface<Cart> {

    FragmentCartBinding binding;
    CartAdapter adapter;
    List<Cart> arrayList;

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
//        sizes.add(new ProductSize(2, 3, 14, 2));
        arrayList = new CartDataSource(requireContext()).getAllCartsForUser(String.valueOf(Utility.getUser(requireContext()).getUserId()));
//        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));
//        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));
//        arrayList.add(new Product(3, "Nike 1", "Mens Shoe", 30.2, 2.0, 0, sizes, new ArrayList<>()));

        checkIfListIsEmpty();
        adapter = new CartAdapter(arrayList, this, requireContext());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        binding.rvCartItem.setLayoutManager(manager);
        binding.rvCartItem.setAdapter(adapter);


        binding.btnProceed.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show();

            Fragment fragment = new ShippingDetailsFragment();

            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frames, fragment);
            fragmentTransaction.commit();
        });


        return binding.getRoot();
    }

    public void checkIfListIsEmpty() {
            LottieAnimationView animationView = binding.getRoot().findViewById(R.id.animation_view_empty_cart);

        if (arrayList.isEmpty()) {
            animationView.setVisibility(View.VISIBLE);
            binding.btnProceed.setVisibility(View.GONE);
            animationView.playAnimation();
        } else {
            animationView.setVisibility(View.GONE);
            binding.btnProceed.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemSelected(Cart data, int position) {

    }

    @Override
    public void onItemRemoved() {
        checkIfListIsEmpty();
    }
}