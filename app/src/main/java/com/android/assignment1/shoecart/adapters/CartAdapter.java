package com.android.assignment1.shoecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    ArrayList<Product> productArrayList;
    AdapterInterface<Product> adapterInterface;

    public CartAdapter(ArrayList<Product> productArrayList, AdapterInterface<Product> adapterInterface) {
        this.productArrayList = productArrayList;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvPrice.setText(String.valueOf(productArrayList.get(position).getPrice()));
        holder.tvTitle.setText(productArrayList.get(position).getTitle());
        holder.tvQuantity.setText("3");

        holder.ivAdd.setOnClickListener(v -> {

        });

        holder.ivMinus.setOnClickListener(v -> {

        });

        holder.btnRemove.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPrice, tvQuantity;
        Button btnRemove;
        ImageView ivAdd, ivProduct, ivMinus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnRemove = itemView.findViewById(R.id.btnRemoveItem);
            ivAdd = itemView.findViewById(R.id.ivAdd);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            ivMinus = itemView.findViewById(R.id.ivMinus);
        }
    }
}
