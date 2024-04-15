package com.android.assignment1.shoecart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.db.CartDataSource;
import com.android.assignment1.shoecart.db.ProductDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<Cart> cartArrayList;
    AdapterInterface<Cart> adapterInterface;
    Context context;
    CartDataSource dataSource;

    public CartAdapter(List<Cart> cartArrayList, AdapterInterface<Cart> adapterInterface, Context context) {
        this.cartArrayList = cartArrayList;
        this.adapterInterface = adapterInterface;
        this.context = context;
        dataSource = new CartDataSource(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = new ProductDataSource(context).getProduct(cartArrayList.get(position).getProductId());
        final int[] quantity = {0};
        quantity[0] = cartArrayList.get(position).getQuantity();
        holder.tvPrice.setText("$" + product.getPrice());
        holder.tvTitle.setText(product.getTitle());
        holder.tvQuantity.setText(String.valueOf(quantity[0]));

        holder.ivProduct.setImageResource(Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), context));

        holder.ivAdd.setOnClickListener(v -> {
            quantity[0]++;
            holder.tvQuantity.setText(String.valueOf(quantity[0]));
            updateCart(new Cart(cartArrayList.get(position).getCartId(), cartArrayList.get(position).getProductId(), cartArrayList.get(position).getProductSize(), quantity[0], cartArrayList.get(position).getUserId()));
        });

        holder.ivMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                holder.tvQuantity.setText(String.valueOf(quantity[0]));
                updateCart(new Cart(cartArrayList.get(position).getCartId(), cartArrayList.get(position).getProductId(), cartArrayList.get(position).getProductSize(), quantity[0], cartArrayList.get(position).getUserId()));

            } else {
                Toast.makeText(context, "Item can't be 0. Please click remove item button to remove item from cart", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnRemove.setOnClickListener(v -> {

            dataSource.deleteCart(cartArrayList.get(position));
            cartArrayList.remove(position);
            notifyDataSetChanged();
            // Notify the fragment that an item has been removed
            adapterInterface.onItemRemoved();
        });
    }

    public void updateCart(Cart cart) {

        dataSource.updateCart(cart);
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
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
