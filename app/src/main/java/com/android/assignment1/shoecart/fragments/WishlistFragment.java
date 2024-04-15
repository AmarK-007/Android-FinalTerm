package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.WishlistAdapter;
import com.android.assignment1.shoecart.databinding.FragmentWishlistBinding;
import com.android.assignment1.shoecart.db.WishlistDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.ProductSize;
import com.android.assignment1.shoecart.models.Wishlist;

import java.util.ArrayList;
import java.util.List;


public class WishlistFragment extends Fragment implements AdapterInterface<Wishlist> {


    FragmentWishlistBinding binding;
    WishlistAdapter adapter;
    List<Wishlist> arrayList;

    public WishlistFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        arrayList = new ArrayList<>();
        ArrayList<ProductSize> sizes = new ArrayList<>();
        sizes.add(new ProductSize(2, 3, 14, 2));
        arrayList = new WishlistDataSource(requireContext()).getAllWishlists();


        adapter = new WishlistAdapter(arrayList, this, requireContext());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        binding.rvWishlistItem.setLayoutManager(manager);
        binding.rvWishlistItem.setAdapter(adapter);

        LottieAnimationView animationView = binding.getRoot().findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.shoecart_orderplaced);


        if (arrayList.isEmpty()) {
            animationView.setVisibility(View.VISIBLE);
            animationView.playAnimation();
        } else {
            animationView.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }

    @Override
    public void onItemSelected(Wishlist data, int position) {

    }
}