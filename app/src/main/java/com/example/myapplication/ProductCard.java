package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCard extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView name;

    public ProductCard(@NonNull View view) {
        super(view);
        this.image = view.findViewById(R.id.image);
        this.name = view.findViewById(R.id.name);
    }

    @NonNull
    public static ProductCard create(@NonNull ViewGroup parent) {
        return new ProductCard(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false));
    }
}