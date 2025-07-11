package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private ArrayList<Product> cartList;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView txtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerCart);
        txtTotalPrice = findViewById(R.id.totalPrice);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnBuy = findViewById(R.id.btnBuy); // ✅ đặt trong onCreate

        btnBack.setOnClickListener(v -> finish());

        // Load cart từ SharedPreferences
        cartList = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("cart", MODE_PRIVATE);
        String data = prefs.getString("cart_items", "");

        if (!data.isEmpty()) {
            String[] items = data.split("\\|");
            for (String item : items) {
                String[] parts = item.split("~");
                if (parts.length == 3) {
                    try {
                        String name = parts[0];
                        double price = Double.parseDouble(parts[1]);
                        String imageUrl = parts[2];
                        cartList.add(new Product(name, price, imageUrl));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        adapter = new CartAdapter(cartList, prefs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnCartChangedListener(this::updateTotalPrice);
        updateTotalPrice();


        btnBuy.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotalPrice() {
        double total = 0;
        for (Product product : cartList) {
            total += product.getPrice();
        }
        txtTotalPrice.setText("Tổng: " + formatCurrency(total));
    }

    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
}
