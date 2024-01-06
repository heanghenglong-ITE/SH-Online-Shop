package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shonlineshop.R;
import com.example.shonlineshop.databinding.ActivityLoginBinding;

import org.w3c.dom.Text;

public class    LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    int attempt_counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonLogin.setOnClickListener(v -> navigateToMain());

        TextView textViewClick = findViewById(R.id.register_type);
        ImageView imageViewClickFb = findViewById(R.id.facebook);
        ImageView imageViewClickIg = findViewById(R.id.instragram);
        ImageView imageViewClickTt = findViewById(R.id.twitter);
        textViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAnotherActivity();
            }
        });
        imageViewClickFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });

        imageViewClickIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstragram();
            }
        });

        imageViewClickTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitter();
            }
        });
    }

    private void navigateToMain() {
        if (binding.editTextTextEmailAddress.getText().toString().equals("team") &&
                binding.editTextPassword.getText().toString().equals("123")) {
            Toast.makeText(this, "User and Password is correct", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"Username and Password is not correct", Toast.LENGTH_SHORT).show();
            attempt_counter--;
            if (attempt_counter == 0){
                binding.buttonLogin.setEnabled(false);
            }
        }
    }
    private void navigateToAnotherActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openFacebook(){
        Uri uri = Uri.parse("https://web.facebook.com/login/?_rdc=1&_rdr");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void openInstragram(){
        Uri uri = Uri.parse("https://web.facebook.com/login/?_rdc=1&_rdr");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void openTwitter(){
        Uri uri = Uri.parse("https://web.facebook.com/login/?_rdc=1&_rdr");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}