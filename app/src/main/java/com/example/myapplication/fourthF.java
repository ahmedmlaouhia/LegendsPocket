package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fourthF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fourthF extends Fragment {
    RecyclerView rv;
    CartAdapter adapter;
    TextView total;
    Button pay;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fourthF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fourthF.
     */
    // TODO: Rename and change types and number of parameters
    public static fourthF newInstance(String param1, String param2) {
        fourthF fragment = new fourthF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_fourth, container, false);
        total=rootView.findViewById(R.id.total);
        pay=rootView.findViewById(R.id.pay);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidprojectfsb-default-rtdb.europe-west1.firebasedatabase.app");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();
        DatabaseReference cart = database.getReference("carts");
        DatabaseReference myRef = database.getReference("carts").child(user).child("products");
        rv=rootView.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        FirebaseRecyclerOptions<Product> options
                = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(myRef, Product.class)
                .build();
        adapter= new CartAdapter(options);
        rv.setAdapter(adapter);
        pay.setOnClickListener(v->{
            cart.removeValue();
            total.setText("0");
            Toast.makeText(this.getContext(), "Paid",
                    Toast.LENGTH_SHORT).show();
        });
        return rootView;
    }

    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}