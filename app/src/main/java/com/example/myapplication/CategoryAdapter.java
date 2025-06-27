package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> categories;
    private OnCategoryClickListener listener;

    // Interface callback khi click vào một category
    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    // Constructor
    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    // Gán listener từ Activity hoặc Fragment
    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView categoryImage;
        private final TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);

            // Xử lý khi click vào item
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition(); // an toàn hơn
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onCategoryClick(categories.get(position));
                }
            });
        }

        public void bind(Category category) {
            categoryName.setText(category.getName());
            categoryImage.setImageResource(category.getImageResource());
        }
    }
}
