package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage, btnBack;
    private TextView nameText, priceText, oldPriceText, ratingText, soldText, descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ View
        productImage = findViewById(R.id.productImage);
        btnBack = findViewById(R.id.btnBack);
        nameText = findViewById(R.id.productName);
        priceText = findViewById(R.id.productPrice);
        oldPriceText = findViewById(R.id.productOldPrice);
        ratingText = findViewById(R.id.productRating);
        soldText = findViewById(R.id.productSold);
        descriptionText = findViewById(R.id.productDescription);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        double price = getIntent().getDoubleExtra("price", 0.0);
        double originalPrice = getIntent().getDoubleExtra("originalPrice", 0.0);
        int image = getIntent().getIntExtra("image", 0);
        float rating = getIntent().getFloatExtra("rating", 0.0f);
        int sold = getIntent().getIntExtra("sold", 0);
        String description = getIntent().getStringExtra("description");

        // Hiển thị dữ liệu
        nameText.setText(name);
        priceText.setText(formatCurrency(price));

        oldPriceText.setText(formatCurrency(originalPrice));


        ratingText.setText("★ " + rating);
        soldText.setText("Đã bán: " + sold);
        descriptionText.setText(description);
        productImage.setImageResource(image);

        // Xử lý nút quay lại
        btnBack.setOnClickListener(v -> finish());
    }

    // Hàm định dạng giá
    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
}
