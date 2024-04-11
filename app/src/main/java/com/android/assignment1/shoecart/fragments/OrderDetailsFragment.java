package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.assignment1.shoecart.adapters.OrderProductAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrderDetailsBinding;
import com.android.assignment1.shoecart.models.Orders;


public class OrderDetailsFragment extends Fragment {

FragmentOrderDetailsBinding binding;
Orders order;

OrderProductAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetailsBinding.inflate(inflater,container,false);
        if (getArguments() != null) {
            //geting arguments
            order = (Orders) getArguments().getParcelable("order");

            if (order != null){
                setData();
            }
        }




        return binding.getRoot();
    }

    public void setData(){
        binding.tvOrderId.setText("#" + order.getOrderId());
        binding.tvDate.setText(order.getOrderDate());
        binding.tvStatus.setText(order.getStatus());
        binding.tvAddress.setText(order.getShippingAddress());
        binding.tvPaymentMethod.setText(order.getPaymentMethod());
        double total = 0.0;
        for (int i = 0 ; i < order.getProductList().size() ; i++){
            total += order.getProductList().get(i).getPrice() * order.getProductList().get(i).getSizes().get(0).getQuantity();
        }
        binding.tvItemTotal.setText("$ "+total);
        binding.tvDeliveryCharges.setText("$2");
        binding.tvPaid.setText("$"+ (total + 2));

        adapter = new OrderProductAdapter(order.getProductList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);

        binding.rvOrdersList.setAdapter(adapter);
        binding.rvOrdersList.setLayoutManager(layoutManager);

    }
}