package com.android.assignment1.shoecart.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Order;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.List;

/**
 * Adapter class for orders
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    List<Order> arrayList;
    AdapterInterface<Order> adapterInterface;
    Context context;

    /**
     * Constructor for OrdersAdapter
     *
     * @param arrayList
     * @param adapterInterface
     * @param context
     * @return
     */
    public OrdersAdapter(List<Order> arrayList, AdapterInterface<Order> adapterInterface, Context context) {
        this.arrayList = arrayList;
        this.adapterInterface = adapterInterface;
        this.context = context;
    }

    /**
     * onCreateViewHolder method
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_item, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * onBindViewHolder method
     *
     * @param holder
     * @param position
     * @return
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText("#" + arrayList.get(position).getOrderId());
        holder.shortDescription.setText(arrayList.get(position).getOrderDetails().size() + "Items");
        holder.status.setText(arrayList.get(position).getDeliveryStatus());
        holder.orderDate.setText(arrayList.get(position).getOrderDate().toString());
        holder.parent.setOnClickListener(v -> {
            adapterInterface.onItemSelected(arrayList.get(position), position);
        });

        String imageName = new ProductDataSource(context).getProduct(arrayList.get(position).getOrderDetails().get(0).getProductId()).getImages().get(0).getImageUrl();
//        String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
        int resourceId = Utility.getImageResourceFromName(imageName, context);
        Log.e("IMAGE", resourceId + "");
        holder.ivProduct.setImageResource(resourceId);

    }

    /**
     * getItemCount method
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /**
     * MyViewHolder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView title, shortDescription, status, orderDate;
        LinearLayout parent;

        /**
         * Constructor for MyViewHolder
         *
         * @param itemView
         * @return
         */
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
