package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.databinding.FragmentProductDetailsBinding;
import com.android.assignment1.shoecart.models.Product;

public class ProductDetailsFragment extends Fragment {

    private SeekBar seekBar;
    private TextView valueTextView;
    FragmentProductDetailsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        seekBar = view.findViewById(R.id.shoeSizeSeekBar);
        valueTextView = view.findViewById(R.id.shoeSize);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = progress;
                valueTextView.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Retrieve the product from the arguments
        Product product = getArguments().getParcelable("product");

        // Set action bar title to the product's title
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null && product != null) {
            actionBar.setTitle(product.getTitle());
        }
        return binding.getRoot();
    }
}