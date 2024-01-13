package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shonlineshop.R;

public class FavItemActivity extends AppCompatActivity {

    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_item);

        backButton = findViewById(R.id.back_button_fav);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Back to Account", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}