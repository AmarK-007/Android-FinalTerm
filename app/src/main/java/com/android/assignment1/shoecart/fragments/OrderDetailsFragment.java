package com.android.assignment1.shoecart.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.OrderProductAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrderDetailsBinding;
import com.android.assignment1.shoecart.db.CartDataSource;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.OnDialogClickListener;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.ArrayList;
import java.util.List;


public class OrderDetailsFragment extends Fragment implements OnDialogClickListener {

    FragmentOrderDetailsBinding binding;
    Order order;
    ArrayList<Product> products;

    Boolean fromShipping = false;

    OrderProductAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            //geting arguments
            order = (Order) getArguments().getParcelable("order");
            fromShipping = getArguments().getBoolean("fromShipping", false);

            if (order != null) {
                setData();
            }
        }

        if (fromShipping) {
            binding.btnReOrder.setText("Place Order");

        }


        binding.btnReOrder.setOnClickListener(v -> {
            if (fromShipping) {
                showPopUp();
                CartDataSource cartDataSource = new CartDataSource(requireContext());
                List<Cart> cartList = cartDataSource.getAllCartsForUser(Utility.getUser(requireContext()).getUserId() + "");
                cartList.forEach(cartDataSource::deleteCart);
                Toast.makeText(requireContext(), "Order Placed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Products Added to cart", Toast.LENGTH_SHORT).show();

                CartDataSource cartDataSource = new CartDataSource(requireContext());
                order.getOrderDetails().forEach(orderDetail -> {
                    cartDataSource.insertCart(new Cart(orderDetail.getProductId(), orderDetail.getProductSize(), orderDetail.getQuantity(), Utility.getUser(requireContext()).getUserId()));
                });
            }
        });


        return binding.getRoot();
    }


    public void showPopUp() {
        CustomAlertDialogFragment dialogFragment = CustomAlertDialogFragment.newInstance(this, "shoecart_orderplaced");
        dialogFragment.setCancelable(false);
        dialogFragment.show(getChildFragmentManager(), getResources().getString(R.string.order_placed));
    }

    public void setData() {
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
            total += products.get(i).getPrice() * order.getOrderDetails().get(i).getQuantity();
            deliveryCharges += products.get(i).getShippingCost();
        }
//        for (int i = 0; i < order.getOrderDetails().size(); i++) {
//
//        }
        binding.tvItemTotal.setText("$" + total);
        binding.tvDeliveryCharges.setText("$" + deliveryCharges);
        binding.tvPaid.setText("$" + (total + deliveryCharges));

        adapter = new OrderProductAdapter(products, order.getOrderDetails());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        binding.rvOrdersList.setAdapter(adapter);
        binding.rvOrdersList.setLayoutManager(layoutManager);

    }

    @Override
    public void onDialogButtonClick() {
        // Replace the current fragment with HomeFragment
        Utility.callHomeFragment(((AppCompatActivity) getActivity()).getSupportFragmentManager());
    }
}