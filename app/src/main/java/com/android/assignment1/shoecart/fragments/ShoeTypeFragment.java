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

public class ShoeTypeFragment extends Fragment {

    private static final String ARG_SHOE_TYPE = "shoeType";

    private String shoeType;

    public static ShoeTypeFragment newInstance(String shoeType) {
        ShoeTypeFragment fragment = new ShoeTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOE_TYPE, shoeType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shoeType = getArguments().getString(ARG_SHOE_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoe_type, container, false);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(shoeType);

        return view;
    }
}
