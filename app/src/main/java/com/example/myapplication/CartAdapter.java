package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<Product> cartList;

    public CartAdapter(ArrayList<Product> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Product product = cartList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getFormattedPrice());
        holder.image.setImageResource(product.getImageResource());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image;

        public CartViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);       // ✅ sửa lại đúng ID
            price = itemView.findViewById(R.id.txtPrice);     // ✅ sửa lại đúng ID
            image = itemView.findViewById(R.id.imgProduct);   // ✅ sửa lại đúng ID
        }
    }
}
