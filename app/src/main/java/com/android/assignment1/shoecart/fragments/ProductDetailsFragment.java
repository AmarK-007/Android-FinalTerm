package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.databinding.FragmentProductDetailsBinding;
import com.android.assignment1.shoecart.db.CartDataSource;
import com.android.assignment1.shoecart.db.WishlistDataSource;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.Wishlist;
import com.android.assignment1.shoecart.utils.Utility;

// ProductDetailsFragment class that extends Fragment
public class ProductDetailsFragment extends Fragment {
    // Declare variables
    private SeekBar seekBar;
    private TextView valueTextView;
    FragmentProductDetailsBinding binding;
    Product product;
    int[] quantity = {1};

    CartDataSource cartDataSource;
    WishlistDataSource wishlistDataSource;
    int value = 6;

    // onCreateView method for creating the view of the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);

        // Check if arguments are passed to the fragment
        if (getArguments() != null) {
            // Retrieve the arguments
            product = (Product) getArguments().getParcelable("product");

            // If product is not null, set the data
            if (product != null){
                setData();
            }
        }

        // Initialize SeekBar and TextView
        seekBar = binding.getRoot().findViewById(R.id.shoeSizeSeekBar);
        valueTextView = binding.getRoot().findViewById(R.id.shoeSize);

        // Initialize CartDataSource and WishlistDataSource
        cartDataSource = new CartDataSource(requireContext());
        wishlistDataSource = new WishlistDataSource(requireContext());

        // Set OnSeekBarChangeListener for the SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the value and the TextView text
                value = progress;
                valueTextView.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Implementation here...
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Implementation here...
            }
        });

        // Return the root view
        return binding.getRoot();
    }

    // Method to set the data for the product
    public void setData(){
        // Set the text for the TextViews
        binding.productDetailName.setText(product.getTitle());
        binding.productDetailDescription.setText(product.getDescription());
        binding.pdQuantity.setText(String.valueOf(quantity[0]));

        // Get the image resource id and set the ImageView source
        int resourceId = Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), requireContext());
        binding.productDetailImg.setImageResource(resourceId);

        // Set onClickListener for the add button
        binding.pdAdd.setOnClickListener(v -> {
            quantity[0]++;
            binding.pdQuantity.setText(String.valueOf(quantity[0]));
        });

        // Set onClickListener for the minus button
        binding.pdMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                binding.pdQuantity.setText(String.valueOf(quantity[0]));
            } else {
                Toast.makeText(requireContext(), "Item can't be 0.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set onClickListener for the addToCartBtn button
        binding.addToCartBtn.setOnClickListener(v -> {
            cartDataSource.insertCart(new Cart(product.getProductId(),String.valueOf(value),quantity[0], Utility.getUser(requireContext()).getUserId()));
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
        });

        // Set onClickListener for the addToWishListBtn button
        binding.addToWishListBtn.setOnClickListener(v -> {
            wishlistDataSource.insertWishlist(new Wishlist(product.getProductId(), value, Utility.getUser(requireContext()).getUserId()));
            Toast.makeText(requireContext(), "Added to Wishlist", Toast.LENGTH_SHORT).show();
        });
    }
}