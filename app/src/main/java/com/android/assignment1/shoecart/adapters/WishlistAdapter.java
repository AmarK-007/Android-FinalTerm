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
import com.android.assignment1.shoecart.db.WishlistDataSource;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Cart;
import com.android.assignment1.shoecart.models.Product;
import com.android.assignment1.shoecart.models.Wishlist;
import com.android.assignment1.shoecart.utils.Utility;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {

    List<Wishlist> wishlistList;
    AdapterInterface<Wishlist> adapterInterface;
    Context context;
    WishlistDataSource dataSource;

    public WishlistAdapter(List<Wishlist> wishListArrayList, AdapterInterface<Wishlist> adapterInterface, Context context) {
        this.wishlistList = wishListArrayList;
        this.adapterInterface = adapterInterface;
        this.context = context;
        dataSource = new WishlistDataSource(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductDataSource productDataSource = new ProductDataSource(context);
        Product product = productDataSource.getProduct(wishlistList.get(position).getProductId());

        holder.btnRemove.setText("Add to Cart");
        final int[] quantity = {1};

        holder.tvPrice.setText("$" + product.getPrice());
        holder.tvTitle.setText(product.getTitle());
        holder.tvQuantity.setText(String.valueOf(quantity[0]));

        holder.ivProduct.setImageResource(Utility.getImageResourceFromName(product.getImages().get(0).getImageUrl(), context));
        holder.btnDelete.setVisibility(View.VISIBLE);

        holder.ivAdd.setOnClickListener(v -> {
            quantity[0]++;
            holder.tvQuantity.setText(String.valueOf(quantity[0]));
//            updateCart(new Cart(wishlistList.get(position).getCartId(), wishlistList.get(position).getProductId(), wishlistList.get(position).getProductSize(),quantity[0], wishlistList.get(position).getUserId()));
        });

        holder.ivMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                holder.tvQuantity.setText(String.valueOf(quantity[0]));
//                updateCart(new Cart(wishlistList.get(position).getCartId(), wishlistList.get(position).getProductId(), wishlistList.get(position).getProductSize(),quantity[0], wishlistList.get(position).getUserId()));

            } else {
                Toast.makeText(context, "Item can't be 0. Please click remove item button to remove item from cart", Toast.LENGTH_SHORT).show();
            }
        });
        final String[] productSize = new String[1];
        holder.btnRemove.setOnClickListener(v -> {
            CartDataSource cartDataSource = new CartDataSource(context);


            productDataSource.getProductSizes(product.getProductId()).forEach(size -> {
                if (size.getSizeId() == wishlistList.get(position).getSizeId()) {
                    productSize[0] = String.valueOf(size.getSizeUs());
                }
            });

            cartDataSource.insertCart(new Cart(wishlistList.get(position).getProductId(), productSize[0], quantity[0], wishlistList.get(position).getUserId()));
            dataSource.deleteWishlist(wishlistList.get(position));
            wishlistList.remove(position);
            notifyDataSetChanged();
        });

        holder.btnDelete.setOnClickListener(v -> {
            dataSource.deleteWishlist(wishlistList.get(position));
            wishlistList.remove(position);
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return wishlistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPrice, tvQuantity;
        Button btnRemove, btnDelete;
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
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
