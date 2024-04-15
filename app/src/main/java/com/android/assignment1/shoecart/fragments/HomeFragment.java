package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.HomeRecyclerViewAdapter;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// HomeFragment class that extends Fragment and implements AdapterInterface
public class HomeFragment extends Fragment implements AdapterInterface<Product> {

    // Declare variables
    ViewFlipper viewFlipper;
    ImageView imageView;
    RecyclerView recyclerViewNewArrivals;
    RecyclerView recyclerViewBestSellers;
    List<Product> productList = new ArrayList<>();
    List<Product> gridNewShoes = new ArrayList<>();
    List<Product> gridBestShoes = new ArrayList<>();
    int[] carousalImages = {R.drawable.product_blue_1, R.drawable.product_white_blue_1, R.drawable.product_grey_3};
    boolean imagesAdded = false;

    // onCreateView method for creating the view of the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize lists
        gridNewShoes = new ArrayList<>();
        gridBestShoes = new ArrayList<>();

        // Get all products
        productList = new ProductDataSource(requireContext()).getAllProducts();

        // Sort products into categories
        productList.forEach(product -> {
            if (Objects.equals(product.getCategory(), "Sports Shoes")) {
                gridBestShoes.add(product);
            } else if (Objects.equals(product.getCategory(), "Trekking Shoes") || Objects.equals(product.getCategory(), "Formal shoes")) {
                gridNewShoes.add(product);
            }
            Log.e("TAG", product.getCategory());
        });

        // Initialize RecyclerViews
        recyclerViewNewArrivals = rootView.findViewById(R.id.recyclerView_newArrivals);
        recyclerViewBestSellers = rootView.findViewById(R.id.recyclerView_bestSellers);

        // Set up RecyclerView for new arrivals
        LinearLayoutManager layoutManagerNewArrivals = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNewArrivals.setLayoutManager(layoutManagerNewArrivals);
        HomeRecyclerViewAdapter adapterNewArrivals = new HomeRecyclerViewAdapter(gridNewShoes,requireContext(),this);
        recyclerViewNewArrivals.setAdapter(adapterNewArrivals);

        // Set up RecyclerView for best sellers
        GridLayoutManager layoutManagerBestSellers = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerViewBestSellers.setLayoutManager(layoutManagerBestSellers);
        HomeRecyclerViewAdapter adapterBestSellers = new HomeRecyclerViewAdapter(gridBestShoes,requireContext(),this);
        recyclerViewBestSellers.setAdapter(adapterBestSellers);

        // Initialize and populate ViewFlipper
        viewFlipper = rootView.findViewById(R.id.categoryCaraousal);
        addImagesToFlipper();

        // Return the root view
        return rootView;
    }

    // Method to add images to the ViewFlipper
    private void addImagesToFlipper() {
        viewFlipper.removeAllViews();

        // Add each product image to the ViewFlipper
        productList.forEach(product -> {
            ImageView imageView = new ImageView(requireContext());
            imageView.setImageResource(Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), requireContext()));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("product", product);

                // Create a new instance of ProductDetailsFragment and set the arguments
                Fragment fragment = new ProductDetailsFragment();
                fragment.setArguments(bundle);

                // Replace the current fragment with ProductDetailsFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frames, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                // Set action bar title to the selected product's title
                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(product.getTitle());
                }
            });
            viewFlipper.addView(imageView);
        });
        startFlipping();
    }

    // Method to start flipping the ViewFlipper
    private void startFlipping() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!viewFlipper.isAttachedToWindow()) return;
                    int nextChildIndex = (viewFlipper.getDisplayedChild() + 1) % viewFlipper.getChildCount();
                    viewFlipper.setDisplayedChild(nextChildIndex);
                    startFlipping();
                }
            }, 2000);
        }
    }

    // Method to handle item selection
    @Override
    public void onItemSelected(Product data, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", data);

        // Create a new instance of ProductDetailsFragment and set the arguments
        Fragment fragment = new ProductDetailsFragment();
        fragment.setArguments(bundle);

        // Replace the current fragment with ProductDetailsFragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        // Set action bar title to the selected product's title
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(data.getTitle());
        }
    }

    // Method to handle item removal
    @Override
    public void onItemRemoved() {
        // Implementation here...
    }
}