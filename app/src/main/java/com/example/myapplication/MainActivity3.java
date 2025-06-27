package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView categoryRecyclerView;
    private RecyclerView productRecyclerView;

    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    private List<Category> categories;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        // Cài đặt padding cho status bar nếu cần
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo view
        initViews();

        // Tạo dữ liệu mẫu
        setupData();

        // Cấu hình RecyclerView
        setupRecyclerViews();
    }

    private void initViews() {
        searchEditText = findViewById(R.id.searchEditText);
        categoryRecyclerView = findViewById(R.id.categoriesRecyclerView);
        productRecyclerView = findViewById(R.id.productsRecyclerView);

        ImageView imgCart = findViewById(R.id.imgCart);
        imgCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, CartActivity.class);
            startActivity(intent);
        });

    }



    private void setupData() {
        categories = new ArrayList<>();
        categories.add(new Category("1", "Điện thoại", R.drawable.catephone));
        categories.add(new Category("2", "Máy tính", R.drawable.catelap));
        categories.add(new Category("3", "Đồng hồ", R.drawable.catewatch));
        categories.add(new Category("4", "Phụ kiện", R.drawable.catephukien));
//        categories.add(new Category("5", "Sách", R.drawable.ic_menu_agenda));

        products = new ArrayList<>();
        products.add(new Product("1", "iPhone 15 Pro Max", 29990000, 34990000,
                R.drawable.img, 4.8f, 120, "Điện thoại cao cấp"));
        products.add(new Product("2", "Samsung Galaxy S24", 24990000, 27990000,
                R.drawable.img_1, 4.6f, 85, "Smartphone Android"));
        products.add(new Product("3", "MacBook Pro M3", 52990000, 59990000,
                R.drawable.img_2, 4.9f, 45, "Laptop Apple"));
        products.add(new Product("4", "Dell XPS 13", 25990000, 29990000,  R.drawable.img_3, 4.5f, 32, "Laptop Dell"));
        products.add(new Product("5", "Áo thun Basic", 299000, 399000,
                android.R.drawable.ic_menu_gallery, 4.2f, 200, "Áo thun cotton"));
        products.add(new Product("6", "Quần jeans Slim", 599000, 799000,
                android.R.drawable.ic_menu_gallery, 4.4f, 150, "Quần jeans nam"));
    }

    private void setupRecyclerViews() {
        categoryAdapter = new CategoryAdapter(categories);
        categoryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        productAdapter = new ProductAdapter(products);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productRecyclerView.setAdapter(productAdapter);

        // Bắt sự kiện click
        categoryAdapter.setOnCategoryClickListener(category ->
                Toast.makeText(this, "Chọn danh mục: " + category.getName(), Toast.LENGTH_SHORT).show());

        productAdapter.setOnProductClickListener(product ->
                Toast.makeText(this, "Chọn sản phẩm: " + product.getName(), Toast.LENGTH_SHORT).show());

        productAdapter.setOnProductClickListener(product -> {
            Intent intent = new Intent(MainActivity3.this, ProductDetailActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("image", product.getImageResource());
            intent.putExtra("description", product.getDescription());
            startActivity(intent);
        });
    }

}
