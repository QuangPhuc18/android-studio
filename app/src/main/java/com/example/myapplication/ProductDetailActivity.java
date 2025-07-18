package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage, btnBack;
    private TextView nameText, priceText, oldPriceText, ratingText, soldText, descriptionText;
    private Button btnAddToCart;

    private String name;
    private double price;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ view
        productImage = findViewById(R.id.productImage);
        btnBack = findViewById(R.id.btnBack);
        nameText = findViewById(R.id.productName);
        priceText = findViewById(R.id.productPrice);
        oldPriceText = findViewById(R.id.productOldPrice);
        ratingText = findViewById(R.id.productRating);
        soldText = findViewById(R.id.productSold);
        descriptionText = findViewById(R.id.productDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Nhận dữ liệu từ Intent
        name = getIntent().getStringExtra("name");
        price = getIntent().getDoubleExtra("price", 0.0);
        double originalPrice = getIntent().getDoubleExtra("originalPrice", 0.0);
        imageUrl = getIntent().getStringExtra("imageUrl"); // <-- dùng URL
        float rating = getIntent().getFloatExtra("rating", 0.0f);
        int sold = getIntent().getIntExtra("sold", 0);
        String description = getIntent().getStringExtra("description");

        // Set dữ liệu vào view
        nameText.setText(name);
        priceText.setText(formatCurrency(price));
        oldPriceText.setText(formatCurrency(originalPrice));
        ratingText.setText("★ " + rating);
        soldText.setText("Đã bán: " + sold);
        descriptionText.setText(description);

        // Hiển thị ảnh bằng Glide
        Glide.with(this)
                .load(imageUrl)
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.error_image)
                .into(productImage);

        // Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("cart", MODE_PRIVATE);
            String oldData = prefs.getString("cart_items", "");
            String newItem = name + "~" + price + "~" + imageUrl;
            String newData = oldData.isEmpty() ? newItem : oldData + "|" + newItem;
            prefs.edit().putString("cart_items", newData).apply();

            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
}
