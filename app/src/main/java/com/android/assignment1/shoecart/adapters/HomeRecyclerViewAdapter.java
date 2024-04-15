package com.android.assignment1.shoecart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.HomeProduct;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {
    private List<Product> products;
    Context context;
    AdapterInterface<Product> adapterInterface;

    public HomeRecyclerViewAdapter(List<Product> products,Context context,AdapterInterface<Product> adapterInterface) {
        this.products = products;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_product_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.newShoesImage.setImageResource(Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), context));
        holder.newShoesName.setText(product.getTitle());
        holder.newShoesCost.setText("$"+product.getPrice());
        holder.cardView.setOnClickListener(v -> {
            adapterInterface.onItemSelected(product,position);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView newShoesImage;
        public TextView newShoesName, newShoesCost;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            newShoesImage = view.findViewById(R.id.productImage);
            newShoesName = view.findViewById(R.id.productName);
            newShoesCost = view.findViewById(R.id.productPrice);
            cardView = view.findViewById(R.id.rootViewCV);
        }
    }
}
