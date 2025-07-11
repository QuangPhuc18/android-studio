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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String selectedCategoryUrl = "https://dummyjson.com/products/category/smartphones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupCategories();
        setupRecyclerViews();
        fetchProductsFromApi(selectedCategoryUrl);

        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
            @Override public void afterTextChanged(android.text.Editable s) {}
        });
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

    private void setupCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("smartphones", "Điện thoại", R.drawable.catephone));
        categories.add(new Category("laptops", "Máy tính", R.drawable.catelap));
        categories.add(new Category("mens-watches", "Đồng hồ", R.drawable.catewatch));
        categories.add(new Category("fragrances", "Phụ kiện", R.drawable.catephukien));
    }

    private void fetchProductsFromApi(String url) {
        products = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray productArray = response.getJSONArray("products");
                        for (int i = 0; i < productArray.length(); i++) {
                            JSONObject obj = productArray.getJSONObject(i);
                            String name = obj.getString("title");
                            double price = obj.getDouble("price");
                            String imageUrl = obj.getString("thumbnail");
                            String description = obj.getString("description");
                            float rating = (float) obj.getDouble("rating");

                            products.add(new Product(name, price, imageUrl, description, rating));
                        }

                        if (productAdapter == null) {
                            productAdapter = new ProductAdapter(products, this);
                            productRecyclerView.setAdapter(productAdapter);
                        } else {
                            productAdapter.updateList(products);
                        }

                        productAdapter.setOnProductClickListener(product -> {
                            Intent intent = new Intent(MainActivity3.this, ProductDetailActivity.class);
                            intent.putExtra("name", product.getName());
                            intent.putExtra("price", product.getPrice());
                            intent.putExtra("imageUrl", product.getImageUrl());
                            intent.putExtra("description", product.getDescription());
                            startActivity(intent);
                        });

                    } catch (JSONException e) {
                        Toast.makeText(this, "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Lỗi khi gọi API", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private void setupRecyclerViews() {
        categoryAdapter = new CategoryAdapter(categories);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        categoryAdapter.setOnCategoryClickListener(category -> {
            selectedCategoryUrl = "https://dummyjson.com/products/category/" + category.getId();
            fetchProductsFromApi(selectedCategoryUrl);
            Toast.makeText(this, "Chọn: " + category.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.updateList(filteredList);
    }
}
