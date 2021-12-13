package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fifthF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fifthF extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fifthF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fifthF.
     */
    // TODO: Rename and change types and number of parameters
    public static fifthF newInstance(String param1, String param2) {
        fifthF fragment = new fifthF();
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
    private FirebaseAuth  mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_fifth, container, false);
        mAuth= FirebaseAuth.getInstance();
        TextView user=rootView.findViewById(R.id.username);
        SharedPreferences ss = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = ss.getString("username","username");
        user.setText(username);
        Button signout=rootView.findViewById(R.id.signout);
        signout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        });
        return rootView;
    }
}