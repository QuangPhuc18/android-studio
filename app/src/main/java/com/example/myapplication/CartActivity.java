package com.example.myapplication;

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

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerCart);
        txtTotalPrice = findViewById(R.id.totalPrice);
        Button btnBack = findViewById(R.id.btnBack);

        // Sự kiện nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Khởi tạo danh sách
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
                        int image = Integer.parseInt(parts[2]);
                        cartList.add(new Product(name, price, image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Thiết lập RecyclerView
        adapter = new CartAdapter(cartList, prefs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Gán callback cập nhật tổng tiền khi có thay đổi giỏ hàng
        adapter.setOnCartChangedListener(this::updateTotalPrice);

        // Tính tổng ban đầu
        updateTotalPrice();
    }

    // Hàm cập nhật tổng giá
    private void updateTotalPrice() {
        double total = 0;
        for (Product product : cartList) {
            total += product.getPrice();
        }
        txtTotalPrice.setText("Tổng: " + formatCurrency(total));
    }

    // Hàm định dạng tiền
    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
}
