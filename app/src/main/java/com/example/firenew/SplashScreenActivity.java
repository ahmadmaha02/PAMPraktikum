package com.example.firenew;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        button = findViewById(R.id.start);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SplashScreenActivity.this, login.class);
        startActivity(intent);
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(SplashScreenActivity.this, "Log In First",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
