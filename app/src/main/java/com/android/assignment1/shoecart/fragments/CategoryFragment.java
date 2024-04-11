package com.android.assignment1.shoecart.fragments;

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.android.assignment1.shoecart.R;
//
//public class CategoryFragment extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_category, container, false);
//
//        // Find TextViews for each category
//        TextView type1TextView = view.findViewById(R.id.type1);
//        TextView type2TextView = view.findViewById(R.id.type2);
//
//
//        // Set OnClickListener for each category TextView
//        type1TextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToCategoryFragment("Type 1");
//            }
//        });
//
//        type2TextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToCategoryFragment("Type 2");
//            }
//        });
//
//
//        return view;
//    }
//
//    // Method to navigate to the selected category fragment
//    private void navigateToCategoryFragment(String category) {
//        Fragment categoryFragment;
//        switch (category) {
//            case "Type 1":
//                categoryFragment = new CategoryItem1Fragment();
//                break;
//            case "Type 2":
//                categoryFragment = new CategoryItem2Fragment();
//                break;
//            // Add cases for other categories as needed
//            default:
//                return;
//        }
//        requireActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frames, categoryFragment)
//                .addToBackStack(null)
//                .commit();
//    }
//}

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.assignment1.shoecart.R;

public class CategoryFragment extends Fragment {

    // Dummy category data (you can replace it with your actual category data)
    private static final String[] categoryNames = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
    private static final int[] categoryImages = {R.drawable.product_blue_1, R.drawable.product_red_1, R.drawable.product_yellow_1, R.drawable.product_grey_1, R.drawable.product_blue_2};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Find the container layout where you want to add the custom card layouts
        ViewGroup cardContainer = view.findViewById(R.id.categoryList);

        // Loop to dynamically add 5 custom card layouts
        for (int i = 0; i < 5; i++) {
            // Inflate the custom card layout
            View customCardView = inflater.inflate(R.layout.custom_category_card, cardContainer, false);

            // Find views within the custom card layout
            ImageView categoryImage = customCardView.findViewById(R.id.categoryImage);
            TextView categoryName = customCardView.findViewById(R.id.categoryName);

            // Set data for each category
            categoryImage.setImageResource(categoryImages[i]);
            categoryName.setText(categoryNames[i]);

            // Set OnClickListener for each custom card layout
            customCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click event here, such as navigating to a specific category fragment
                    // You can use categoryName.getText().toString() to get the category name
                    // For simplicity, I'll just log the clicked category name
                    String clickedCategory = categoryName.getText().toString();
                    navigateToCategoryFragment(clickedCategory);
                }
            });

            // Add the custom card layout to the container
            cardContainer.addView(customCardView);
        }

        return view;
    }

    // Method to navigate to the selected category fragment
    private void navigateToCategoryFragment(String category) {
        // Handle navigation to the selected category fragment
        // You can implement this method according to your navigation requirements
        // For demonstration purposes, I'll just log the selected category
        System.out.println("Selected Category: " + category);
    }
}

