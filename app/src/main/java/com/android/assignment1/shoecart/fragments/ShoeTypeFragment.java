package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.assignment1.shoecart.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoeTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoeTypeFragment extends Fragment {

    // Constant for the shoe type argument key
    private static final String ARG_SHOE_TYPE = "shoeType";

    // Variable to hold the shoe type
    private String shoeType;

    // Factory method to create a new instance of this fragment
    public static ShoeTypeFragment newInstance(String shoeType) {
        ShoeTypeFragment fragment = new ShoeTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOE_TYPE, shoeType); // Put the shoe type into the arguments
        fragment.setArguments(args); // Set the arguments for the fragment
        return fragment; // Return the new fragment
    }

    // onCreate method called when the fragment is created
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve the shoe type from the arguments
            shoeType = getArguments().getString(ARG_SHOE_TYPE);
        }
    }

    // onCreateView method called to create the view for the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoe_type, container, false);

        // Find the TextView and set its text to the shoe type
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(shoeType);

        // Return the view for the fragment
        return view;
    }
}