package com.example.myapplication;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<Product> cartList;
    private SharedPreferences sharedPreferences;

    // Giao diện callback để CartActivity cập nhật tổng tiền
    public interface OnCartChangedListener {
        void onCartUpdated();
    }

    private OnCartChangedListener cartChangedListener;

    public void setOnCartChangedListener(OnCartChangedListener listener) {
        this.cartChangedListener = listener;
    }

    public CartAdapter(ArrayList<Product> cartList, SharedPreferences sharedPreferences) {
        this.cartList = cartList;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Product product = cartList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(formatCurrency(product.getPrice()));
        holder.image.setImageResource(product.getImageResource());

        // Xử lý nút XÓA
        holder.btnDelete.setOnClickListener(v -> {
            // Xoá sản phẩm
            cartList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartList.size());

            // Cập nhật SharedPreferences
            StringBuilder newData = new StringBuilder();
            for (int i = 0; i < cartList.size(); i++) {
                Product p = cartList.get(i);
                newData.append(p.getName()).append("~")
                        .append(p.getPrice()).append("~")
                        .append(p.getImageResource());
                if (i < cartList.size() - 1) {
                    newData.append("|");
                }
            }
            sharedPreferences.edit().putString("cart_items", newData.toString()).apply();

            // Gọi callback để cập nhật tổng tiền
            if (cartChangedListener != null) {
                cartChangedListener.onCartUpdated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image;
        Button btnDelete;

        public CartViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            price = itemView.findViewById(R.id.txtPrice);
            image = itemView.findViewById(R.id.imgProduct);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
}
