package com.android.assignment1.shoecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.models.HomeProduct;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {
    private List<HomeProduct> products;

    public HomeRecyclerViewAdapter(List<HomeProduct> products) {
        this.products = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_product_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeProduct product = products.get(position);
        holder.newShoesImage.setImageResource(product.getImageId());
        holder.newShoesName.setText(product.getProductName());
        holder.newShoesCost.setText(Double.toString(product.getCost()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView newShoesImage;
        public TextView newShoesName, newShoesCost;

        public MyViewHolder(View view) {
            super(view);
            newShoesImage = view.findViewById(R.id.productImage);
            newShoesName = view.findViewById(R.id.productName);
            newShoesCost = view.findViewById(R.id.productPrice);
        }
    }
}
