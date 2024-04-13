package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.databinding.FragmentShippingDetailsBinding;
import com.android.assignment1.shoecart.db.CartDataSource;
import com.android.assignment1.shoecart.db.OrderDataSource;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.db.UserDataSource;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.models.OrderDetail;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.User;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ShippingDetailsFragment extends Fragment {

    FragmentShippingDetailsBinding binding;
    String spinnerSelectedItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShippingDetailsBinding.inflate(inflater, container, false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.canadian_provinces, // Array resource containing province names
                android.R.layout.simple_spinner_item // Default spinner layout
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.provinceSpinner.setAdapter(adapter);

        binding.provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected province
                String selectedProvince = parent.getItemAtPosition(position).toString();

                spinnerSelectedItem = selectedProvince;
                // Display a toast with the selected province
                Toast.makeText(requireContext(), "Selected Province: " + selectedProvince, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        User user = Utility.getUser(requireContext());
        setData(user);


        binding.btnCheckOut.setOnClickListener(v -> {
            formValidation(user);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private int getIndex(Spinner spinner, String item) {
        // Get the index of the specified item in the Spinner's data
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return 0; // Default to the first item if not found
    }

    public void setData(User user) {
        binding.firstname.setText(user.getName());
        binding.addressOne.setText(user.getShippingAddress1());
        binding.addressTwo.setText(user.getShippingAddress2());
        binding.postalCode.setText(user.getPincode());
        binding.city.setText(user.getCity());
        binding.provinceSpinner.setSelection(getIndex(binding.provinceSpinner, "Manitoba"));
    }

    public void formValidation(User user) {
        if (binding.firstname.getText().toString().isEmpty()) {
            binding.firstname.setError("Required*");
        } else if (binding.addressOne.getText().toString().isEmpty()) {
            binding.addressOne.setError("Required*");
        } else if (binding.addressTwo.getText().toString().isEmpty()) {
            binding.addressTwo.setError("Required*");
        } else if (binding.postalCode.getText().toString().isEmpty()) {
            binding.postalCode.setError("Required*");
        } else if (binding.city.getText().toString().isEmpty()) {
            binding.city.setError("Required*");
        } else {
            UserDataSource userDataSource = new UserDataSource(requireContext());
            userDataSource.updateUser(new User(user.getUserId(), binding.firstname.getText().toString(), user.getEmail(), user.getPassword(), user.getUsername(), user.getPurchaseHistory(), binding.addressOne.getText().toString(), binding.addressTwo.getText().toString(), binding.city.getText().toString(), spinnerSelectedItem, binding.postalCode.getText().toString()));

            OrderDataSource orderDataSource = new OrderDataSource(requireContext());
            CartDataSource cartDataSource = new CartDataSource(requireContext());
            int userId = Utility.getUser(requireContext()).getUserId();
            List<Cart> cartList = cartDataSource.getAllCartsForUser(userId + "");

            List<OrderDetail> orderDetails = new ArrayList<>();
            double total = 0.0;
            double deliveryCharges = 0.0;
            List<Product> products = new ArrayList<>();


            for (int i = 0; i < cartList.size(); i++) {
                products.add(new ProductDataSource(requireContext()).getProduct(cartList.get(i).getProductId()));
                total += products.get(i).getPrice() * cartList.get(i).getQuantity();
                deliveryCharges += products.get(i).getShippingCost();
                orderDetails.add(new OrderDetail(cartList.get(i).getProductId(), cartList.get(i).getQuantity(), cartList.get(i).getProductSize()));
            }


            double totalAmount = (total * 0.13) + total + deliveryCharges;


            Order order = new Order(userId, totalAmount, new Date(), new Date(), "Cash On Delivery", "Ordered", "", orderDetails);

            orderDataSource.insertOrder(order);

//            cartList.forEach(cartDataSource::deleteCart);


            Bundle bundle = new Bundle();
            bundle.putParcelable("order", order);
            bundle.putBoolean("fromShipping", true);
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
}