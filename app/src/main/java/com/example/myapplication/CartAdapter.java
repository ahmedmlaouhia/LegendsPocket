package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends FirebaseRecyclerAdapter<Product,CartAdapter.MyAdapter> {

    public CartAdapter(@NonNull FirebaseRecyclerOptions<Product> options){
        super(options);
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.product_cart,parent,false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position,@NonNull Product model) {
        holder.name.setText(model.getName());
        holder.price.setText(String.valueOf(model.getPrice()));
        Picasso.get().load(model.getImageUrl()).into(holder.image);
        holder.remove.setOnClickListener(v->{
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String user = mAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidprojectfsb-default-rtdb.europe-west1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference("carts").child(user);
            myRef.child("products").child(model.getId()).setValue(model);
        });
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
            this.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}
