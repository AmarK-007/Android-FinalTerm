package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.OrdersAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrdersBinding;
import com.android.assignment1.shoecart.db.OrderDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.ProductSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// OrdersFragment class that extends Fragment and implements AdapterInterface
public class OrdersFragment extends Fragment implements AdapterInterface<Order> {

    // Declare variables
    FragmentOrdersBinding binding;
    List<Order> ordersList = new ArrayList<>();
    ArrayList<Product> productArrayList = new ArrayList<>();
    OrdersAdapter adapter;
    LinearLayoutManager layoutManager;

    // Empty public constructor
    public OrdersFragment() {
        // Required empty public constructor
    }

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
        binding = FragmentOrdersBinding.inflate(inflater, container, false);

        // Initialize OrderDataSource and get all orders
        OrderDataSource orderDataSource = new OrderDataSource(requireContext());
        ordersList = new ArrayList<>();
        ordersList = orderDataSource.getAllOrders();

        // Initialize productArrayList
        productArrayList = new ArrayList<>();

        // Reverse the order of the ordersList
        Collections.reverse(ordersList);

        // Set the data and check if the list is empty
        setData();
        checkIfListIsEmpty();

        // Return the root view
        return binding.getRoot();
    }

    // Method to set the data
    public void setData() {
        // Initialize the adapter and set it to the RecyclerView
        adapter = new OrdersAdapter(ordersList, this, requireContext());
        layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvOrders.setLayoutManager(layoutManager);
        binding.rvOrders.setAdapter(adapter);
    }

    // Method to handle item selection
    @Override
    public void onItemSelected(Order data, int position) {
        // Create a bundle and put the selected order in it
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", data);

        // Create a new instance of OrderDetailsFragment and set the arguments
        Fragment fragment = new OrderDetailsFragment();
        fragment.setArguments(bundle);

        // Replace the current fragment with OrderDetailsFragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Method to check if the list is empty
    public void checkIfListIsEmpty() {
        LottieAnimationView animationView = binding.getRoot().findViewById(R.id.animation_view_empty_orders);

        // If the list is empty, show the animation view, otherwise hide it
        if (ordersList.isEmpty()) {
            animationView.setVisibility(View.VISIBLE);
            animationView.playAnimation();
        } else {
            animationView.setVisibility(View.GONE);
        }
    }

    // Method to handle item removal
    @Override
    public void onItemRemoved() {
        checkIfListIsEmpty();
    }
}