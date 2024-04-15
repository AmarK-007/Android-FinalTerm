package com.android.assignment1.shoecart.fragments;

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

public class ProductDetailsFragment extends Fragment {
    private SeekBar seekBar;
    private TextView valueTextView;
    FragmentProductDetailsBinding binding;
    Product product;
    int[] quantity = {1};

    CartDataSource cartDataSource;
    WishlistDataSource wishlistDataSource;
    int value = 6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        if (getArguments() != null) {
            //geting arguments
            product = (Product) getArguments().getParcelable("product");


            if (product != null){
                setData();
            }
        }


        seekBar = view.findViewById(R.id.shoeSizeSeekBar);
        valueTextView = view.findViewById(R.id.shoeSize);

        seekBar = binding.getRoot().findViewById(R.id.shoeSizeSeekBar);
        valueTextView = binding.getRoot().findViewById(R.id.shoeSize);

        cartDataSource = new CartDataSource(requireContext());
        wishlistDataSource = new WishlistDataSource(requireContext());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = progress;
                value = progress;
                valueTextView.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
        return binding.getRoot();
    }

    public void setData(){
        binding.productDetailName.setText(product.getTitle());
        binding.productDetailDescription.setText(product.getDescription());
        String imageName = product.getImages().get(0).getImageUrl();
        binding.pdQuantity.setText(String.valueOf(quantity[0]));
//        String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
        int resourceId = Utility.getImageResourceFromName(imageName, requireContext());


        binding.productDetailImg.setImageResource(resourceId);



        binding.pdAdd.setOnClickListener(v -> {
            quantity[0]++;
            binding.pdQuantity.setText(String.valueOf(quantity[0]));
//            updateCart(new Cart(wishlistList.get(position).getCartId(), wishlistList.get(position).getProductId(), wishlistList.get(position).getProductSize(),quantity[0], wishlistList.get(position).getUserId()));
        });

        binding.pdMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                binding.pdQuantity.setText(String.valueOf(quantity[0]));
//                updateCart(new Cart(wishlistList.get(position).getCartId(), wishlistList.get(position).getProductId(), wishlistList.get(position).getProductSize(),quantity[0], wishlistList.get(position).getUserId()));

            } else {
                Toast.makeText(requireContext(), "Item can't be 0.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.addToCartBtn.setOnClickListener(v -> {
            cartDataSource.insertCart(new Cart(product.getProductId(),String.valueOf(value),quantity[0], Utility.getUser(requireContext()).getUserId()));
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
        });

        binding.addToWishListBtn.setOnClickListener(v -> {
            wishlistDataSource.insertWishlist(new Wishlist(product.getProductId(), value, Utility.getUser(requireContext()).getUserId()));
            Toast.makeText(requireContext(), "Added to Wishlist", Toast.LENGTH_SHORT).show();
        });
    }
}