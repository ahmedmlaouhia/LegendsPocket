package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText email;
    private TextInputEditText password;
    private Button login, signup, forgot;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.outlinedButton);
        signup = findViewById(R.id.textButton);
        forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(v -> {
            String _email = email.getText().toString().trim();
            if (!TextUtils.isEmpty(_email))
                mAuth.sendPasswordResetEmail(_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Reset link was sent",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Login.this, "unexpected error",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            else Toast.makeText(Login.this, "please write an email",
                    Toast.LENGTH_SHORT).show();
        });

        login.setOnClickListener(v -> {
            String _email = email.getText().toString().trim();
            String _password = password.getText().toString().trim();
            if (!TextUtils.isEmpty(_email) && !TextUtils.isEmpty(_password))
                mAuth.signInWithEmailAndPassword(_email, _password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user.isEmailVerified())
                                        startActivity(new Intent(Login.this, Login.class));
                                    else {
                                        Toast.makeText(Login.this, "email not verified",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this, "check your credentials.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, signup.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userID=mAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidprojectfsb-default-rtdb.europe-west1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference("users");
            myRef.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                    }
                    else {
                        String username =String.valueOf(task.getResult().child("username").getValue());
                        String email =String.valueOf(task.getResult().child("email").getValue());
                        String phone =String.valueOf(task.getResult().child("phone").getValue());
                        //User user=new User(username,email,phone);
                        SharedPreferences s = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor e = s.edit();
                        e.putString("email",email);
                        e.putString("username",username);
                        e.putString("phone",phone);
                        e.commit();
                    }
                }
            });
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }
}