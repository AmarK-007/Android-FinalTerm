package com.android.assignment1.shoecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.models.Category;
import com.android.assignment1.shoecart.models.HomeProduct;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<Category> categories = new ArrayList<>();



    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Image.setImageResource(categories.get(position).getCategoryImage());
        holder.Name.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView Image;
        TextView Name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image = itemView.findViewById(R.id.categoryImage);
            Name = itemView.findViewById(R.id.categoryName);
        }
    }
}
