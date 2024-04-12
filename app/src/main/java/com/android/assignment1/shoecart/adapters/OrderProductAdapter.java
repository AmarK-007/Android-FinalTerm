package com.android.assignment1.shoecart.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.models.OrderDetail;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.MyViewHolder> {

    ArrayList<Product> productArrayList;
    List<OrderDetail> orderDetailList;

    public OrderProductAdapter(ArrayList<Product> productArrayList, List<OrderDetail> orderDetailList) {
        this.productArrayList = productArrayList;
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_product_item,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(productArrayList.get(position).getTitle() + " x " + orderDetailList.get(position).getQuantity());
        holder.price.setText(String.valueOf(productArrayList.get(position).getPrice() * orderDetailList.get(position).getQuantity()));
        holder.shortDescription.setText(productArrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,price,shortDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            price = itemView.findViewById(R.id.tvPrice);
            shortDescription = itemView.findViewById(R.id.tvShortDescription);
        }
    }
}
