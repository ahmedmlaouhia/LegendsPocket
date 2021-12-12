package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class loading_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);

        Handler handler= new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(loading_page.this,Login.class));
            finish();
        },2500
        );
    }
}
