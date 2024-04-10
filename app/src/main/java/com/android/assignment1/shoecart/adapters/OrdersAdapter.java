package com.android.assignment1.shoecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.fragments.OrdersFragment;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Orders;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    ArrayList<Orders> arrayList;
    AdapterInterface<Orders> adapterInterface;

    public OrdersAdapter(ArrayList<Orders> arrayList,AdapterInterface<Orders> adapterInterface) {
        this.arrayList = arrayList;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText("#" + arrayList.get(position).getOrderId());
        holder.shortDescription.setText(arrayList.get(position).getProductList().size() + "Items");
        holder.status.setText(arrayList.get(position).getStatus());
        holder.orderDate.setText(arrayList.get(position).getOrderDate());
        holder.parent.setOnClickListener(v -> {
            adapterInterface.onItemSelected(arrayList.get(position),position);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView title,shortDescription,status,orderDate;
        RelativeLayout parent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            title = itemView.findViewById(R.id.tvTitle);
            shortDescription = itemView.findViewById(R.id.tvShortDescription);
            status = itemView.findViewById(R.id.tvStatus);
            orderDate = itemView.findViewById(R.id.tvDate);
            parent = itemView.findViewById(R.id.parentLayout);
        }
    }
}
