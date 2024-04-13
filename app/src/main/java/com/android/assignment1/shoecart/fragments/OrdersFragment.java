package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.OrdersAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrdersBinding;
import com.android.assignment1.shoecart.db.OrderDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OrdersFragment extends Fragment implements AdapterInterface<Order> {


    FragmentOrdersBinding binding;
    List<Order> ordersList = new ArrayList<>();
    ArrayList<Product> productArrayList = new ArrayList<>();

    OrdersAdapter adapter;
    LinearLayoutManager layoutManager;

    public OrdersFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater,container,false);

        OrderDataSource orderDataSource = new OrderDataSource(requireContext());
        ordersList = new ArrayList<>();
        ordersList = orderDataSource.getAllOrders();
        Collections.reverse(ordersList);

        setData();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void setData(){
        adapter = new OrdersAdapter(ordersList, this, requireContext());
        layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        binding.rvOrders.setLayoutManager(layoutManager);
        binding.rvOrders.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(Order data, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", data);
//        moving to add fragment
        Fragment fragment = new OrderDetailsFragment();
        //passing arguments
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}