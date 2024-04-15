package com.android.assignment1.shoecart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.List;


public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.MyViewHolder> {

    List<Product> arrayList;
    Context context;
    AdapterInterface<Product> adapterInterface;

    public ShowProductAdapter(List<Product> products, Context context, AdapterInterface<Product> adapterInterface) {
        this.arrayList = products;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageName = new ProductDataSource(context).getProduct(arrayList.get(position).getProductId()).getImages().get(0).getImageUrl();
//        String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
        int resourceId = Utility.getImageResourceFromName(imageName, context);
        holder.imageView.setImageResource(resourceId);
        holder.productName.setText(arrayList.get(position).getTitle());
        holder.price.setText("$" + arrayList.get(position).getPrice());

        holder.cardView.setOnClickListener(v -> adapterInterface.onItemSelected(arrayList.get(position), position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productName, price;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            cardView = itemView.findViewById(R.id.rootViewCV);
        }
    }
}
