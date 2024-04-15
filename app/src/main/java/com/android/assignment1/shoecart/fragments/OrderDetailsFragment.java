package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages

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

// OrderDetailsFragment class that extends Fragment and implements OnDialogClickListener
public class OrderDetailsFragment extends Fragment implements OnDialogClickListener {

    // Declare variables
    FragmentOrderDetailsBinding binding;
    Order order;
    ArrayList<Product> products;
    Boolean fromShipping = false;
    OrderProductAdapter adapter;

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
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false);

        // Check if arguments are passed to the fragment
        if (getArguments() != null) {
            // Retrieve the arguments
            order = (Order) getArguments().getParcelable("order");
            fromShipping = getArguments().getBoolean("fromShipping", false);

            // If order is not null, set the data
            if (order != null) {
                setData();
            }
        }

        // If fromShipping is true, set the text of the button
        if (fromShipping) {
            binding.btnReOrder.setText("Place Order");
        }

        // Set onClickListener for the button
        binding.btnReOrder.setOnClickListener(v -> {
            if (fromShipping) {
                // Show the popup, delete all carts for the user, and show a toast message
                showPopUp();
                CartDataSource cartDataSource = new CartDataSource(requireContext());
                List<Cart> cartList = cartDataSource.getAllCartsForUser(Utility.getUser(requireContext()).getUserId() + "");
                cartList.forEach(cartDataSource::deleteCart);
                Toast.makeText(requireContext(), "Order Placed", Toast.LENGTH_SHORT).show();
            } else {
                // Add all order details to the cart and show a toast message
                Toast.makeText(requireContext(), "Products Added to cart", Toast.LENGTH_SHORT).show();
                CartDataSource cartDataSource = new CartDataSource(requireContext());
                order.getOrderDetails().forEach(orderDetail -> {
                    cartDataSource.insertCart(new Cart(orderDetail.getProductId(), orderDetail.getProductSize(), orderDetail.getQuantity(), Utility.getUser(requireContext()).getUserId()));
                });
            }
        });

        // Return the root view
        return binding.getRoot();
    }

    // Method to show the popup
    public void showPopUp() {
        CustomAlertDialogFragment dialogFragment = CustomAlertDialogFragment.newInstance(this, "shoecart_orderplaced");
        dialogFragment.setCancelable(false);
        dialogFragment.show(getChildFragmentManager(), getResources().getString(R.string.order_placed));
    }

    // Method to set the data for the order
    public void setData() {
        binding.tvDate.setText(order.getOrderDate().toString());
        binding.tvStatus.setText(order.getDeliveryStatus());
        binding.tvPaymentMethod.setText(order.getPaymentMethod());

        // Calculate the total price and delivery charges
        double total = 0.0;
        double deliveryCharges = 0.0;
        products = new ArrayList<>();
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            products.add(new ProductDataSource(requireContext()).getProduct(order.getOrderDetails().get(i).getProductId()));
            total += products.get(i).getPrice() * order.getOrderDetails().get(i).getQuantity();
            deliveryCharges += products.get(i).getShippingCost();
        }

        // Set the text for the TextViews
        binding.tvItemTotal.setText("$" + String.format("%.2f", total));
        binding.tvDeliveryCharges.setText("$" + String.format("%.2f", deliveryCharges));
        binding.tvPaid.setText("$" + String.format("%.2f", (total + deliveryCharges)));

        // Initialize the adapter and set it to the RecyclerView
        adapter = new OrderProductAdapter(products, order.getOrderDetails());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvOrdersList.setAdapter(adapter);
        binding.rvOrdersList.setLayoutManager(layoutManager);
    }

    // Method to handle dialog button click
    @Override
    public void onDialogButtonClick() {
        // Replace the current fragment with HomeFragment
        Utility.callHomeFragment(((AppCompatActivity) getActivity()).getSupportFragmentManager());
    }
}