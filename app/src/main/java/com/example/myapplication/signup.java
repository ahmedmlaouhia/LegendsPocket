package com.example.myapplication;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText email,password,username,phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        username=findViewById(R.id.username);
        phone=findViewById(R.id.phone);
        Button signup =findViewById(R.id.outlinedButton);
        Button login =findViewById(R.id.textButton);

        signup.setOnClickListener(v -> {
            String _email=email.getText().toString().trim();
            String _password=password.getText().toString().trim();
            String _username=username.getText().toString().trim();
            String _phone=phone.getText().toString().trim();
            if (!TextUtils.isEmpty(_email) && !TextUtils.isEmpty(_password))
            mAuth.createUserWithEmailAndPassword(_email, _password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(signup.this, "Verification email was sent",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidprojectfsb-default-rtdb.europe-west1.firebasedatabase.app");
                                DatabaseReference myRef = database.getReference("users").child(user.getUid());
                                myRef.child("username").setValue(_username);
                                myRef.child("email").setValue(_email);
                                myRef.child("phone").setValue(_phone);
                                mAuth.signOut();
                                startActivity(new Intent(signup.this,Login.class));
                            } else {
                                System.out.println(task.getException()+"\t "+_email);
                                Toast.makeText(signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            else
                Toast.makeText(signup.this, "please provide all fields.",
                        Toast.LENGTH_SHORT).show();
        });
        login.setOnClickListener(v -> {
            startActivity(new Intent(signup.this,Login.class));
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(signup.this,MainActivity.class));
        }
    }
}