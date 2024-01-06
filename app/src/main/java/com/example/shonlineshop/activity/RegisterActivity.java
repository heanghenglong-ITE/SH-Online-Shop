package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shonlineshop.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView textViewClick = findViewById(R.id.login_type);
        textViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginActivity();
            }
        });


    }

    private void navigateToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}