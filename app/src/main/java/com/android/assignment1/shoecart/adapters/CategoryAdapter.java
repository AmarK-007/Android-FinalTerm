package com.android.assignment1.shoecart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<String> categories = new ArrayList<>();
    Context context;

    AdapterInterface<String> adapterInterface;


    public CategoryAdapter(List<String> categories, Context context, AdapterInterface<String> adapterInterface) {
        this.categories = categories;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        String imageName = categories.get(position).getCategoryImage();
////        String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
//        int resourceId = Utility.getImageResourceFromName(imageName, context);

//        holder.Image.setImageResource(resourceId);
        holder.Name.setText(categories.get(position));
        holder.layout.setOnClickListener(v -> {
            adapterInterface.onItemSelected(categories.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //        ImageView Image;
        LinearLayout layout;
        TextView Name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.rootViewLL);
//            Image = itemView.findViewById(R.id.categoryImage);
            Name = itemView.findViewById(R.id.categoryName);
        }
    }
}
