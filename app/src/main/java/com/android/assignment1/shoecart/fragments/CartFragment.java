package com.android.assignment1.shoecart.fragments;// Import necessary libraries and packages
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
import com.android.assignment1.shoecart.fragments.ShippingDetailsFragment;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.ProductSize;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.ArrayList;
import java.util.List;

// CartFragment class that extends Fragment and implements AdapterInterface
public class CartFragment extends Fragment implements AdapterInterface<Cart> {

    // Declare variables
    FragmentCartBinding binding;
    CartAdapter adapter;
    List<Cart> arrayList;

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
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Initialize arrayList
        arrayList = new ArrayList<>();

        // Get all carts for the user
        arrayList = new CartDataSource(requireContext()).getAllCartsForUser(Utility.getUser(requireContext()).getUserId() + "");

        // Check if the list is empty
        checkIfListIsEmpty();

        // Initialize adapter and set it to the RecyclerView
        adapter = new CartAdapter(arrayList, this, requireContext());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvCartItem.setLayoutManager(manager);
        binding.rvCartItem.setAdapter(adapter);

        // Set onClickListener for the proceed button
        binding.btnProceed.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show();

            // Replace the current fragment with ShippingDetailsFragment
            Fragment fragment = new ShippingDetailsFragment();
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frames, fragment);
            fragmentTransaction.commit();
        });

        // Return the root view
        return binding.getRoot();
    }

    // Method to check if the list is empty and show/hide views accordingly
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

    // Method to handle item selection
    @Override
    public void onItemSelected(Cart data, int position) {
        // Implementation here...
    }

    // Method to handle item removal
    @Override
    public void onItemRemoved() {
        checkIfListIsEmpty();
    }
}