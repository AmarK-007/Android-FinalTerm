package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.assignment1.shoecart.adapters.OrderProductAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrderDetailsBinding;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;


public class OrderDetailsFragment extends Fragment {

FragmentOrderDetailsBinding binding;
    Order order;
    ArrayList<Product> products;

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
            order = (Order) getArguments().getParcelable("order");

            if (order != null){
                setData();
            }
        }




        return binding.getRoot();
    }

    public void setData(){
        binding.tvOrderId.setText("#" + order.getOrderId());
        binding.tvDate.setText(order.getOrderDate().toString());
        binding.tvStatus.setText(order.getDeliveryStatus());
//        binding.tvAddress.setText(order.get());
        binding.tvPaymentMethod.setText(order.getPaymentMethod());
        double total = 0.0;
        double deliveryCharges = 0.0;
        products = new ArrayList<>();
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            products.add(new ProductDataSource(requireContext()).getProduct(order.getOrderDetails().get(i).getProductId()));
        }
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            total += products.get(i).getPrice() * order.getOrderDetails().get(i).getQuantity();
            deliveryCharges += products.get(i).getShippingCost();
        }
        binding.tvItemTotal.setText("$" + total);
        binding.tvDeliveryCharges.setText("$" + deliveryCharges);
        binding.tvPaid.setText("$" + (total + deliveryCharges));

        adapter = new OrderProductAdapter(products, order.getOrderDetails());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);

        binding.rvOrdersList.setAdapter(adapter);
        binding.rvOrdersList.setLayoutManager(layoutManager);

    }
}