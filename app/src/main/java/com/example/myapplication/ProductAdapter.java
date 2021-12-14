package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyAdapter> {

    public ArrayList<Product> products =new ArrayList<>();
    private Context context;

    public ProductAdapter(Context ctx,ArrayList<Product> products){
        context=ctx;
        this.products=products;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.product_card,parent,false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        holder.name.setText(products.get(0).getName());
        try {
            ImageView i =holder.image;
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(products.get(0).getImageUrl()).getContent());
            i.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,price;
        public Button remove;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.name = itemView.findViewById(R.id.name);
            this.price = itemView.findViewById(R.id.price);
            this.remove=itemView.findViewById(R.id.remove);
        }
    }
}
