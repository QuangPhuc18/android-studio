package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ArrayList<Product> cartList;
    private RecyclerView recyclerView;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Khởi tạo danh sách và ánh xạ RecyclerView
        cartList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Nút quay lại
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Lấy dữ liệu từ SharedPreferences
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
                        int image = Integer.parseInt(parts[2]);

                        // Dùng constructor rút gọn
                        Product product = new Product(name, price, image);
                        cartList.add(product);
                    } catch (Exception e) {
                        e.printStackTrace(); // Bắt lỗi parse sai định dạng
                    }
                }
            }
        }

        // Khởi tạo adapter và gắn vào RecyclerView
        adapter = new CartAdapter(cartList);
        recyclerView.setAdapter(adapter);
    }
}
