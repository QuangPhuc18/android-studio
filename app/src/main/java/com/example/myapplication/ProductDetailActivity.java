package com.example.myapplication; // sửa theo tên package của bạn

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView nameText, priceText, descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.productImage);
        nameText = findViewById(R.id.productName);
        priceText = findViewById(R.id.productPrice);
        descriptionText = findViewById(R.id.productDescription);

        String name = getIntent().getStringExtra("name");
        int price = getIntent().getIntExtra("price", 0);
        int image = getIntent().getIntExtra("image", 0);
        String description = getIntent().getStringExtra("description");

        if (name != null) nameText.setText(name);
        priceText.setText(formatPrice(price));
        if (description != null) descriptionText.setText(description);
        if (image != 0) productImage.setImageResource(image);
    }

    private String formatPrice(int price) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(price) + " đ";
    }

}
