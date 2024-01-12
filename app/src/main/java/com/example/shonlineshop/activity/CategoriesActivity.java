package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.shonlineshop.R;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        RelativeLayout btnAll = findViewById(R.id.BtnAll);
        RelativeLayout btnHomeDecoration = findViewById(R.id.BtnHomeDecoration);
        RelativeLayout BtnCurtain = findViewById(R.id.BtnCurtain);
        RelativeLayout BtnStickyPaperWall = findViewById(R.id.BtnStickyPaperWall);
        RelativeLayout BtnSpices = findViewById(R.id.BtnSpices);
        ImageView backButton = findViewById(R.id.BackButton);

        // Set click listener for BtnAll
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AllCateActivity when BtnAll is clicked
                Intent intent = new Intent(CategoriesActivity.this, AllCateActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for BtnHomeDecoration
        btnHomeDecoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, HomeDecorationCateActivity.class);
                startActivity(intent);
            }
        });
        BtnCurtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, CurtainCateActivity.class);
                startActivity(intent);
            }
        });
        BtnStickyPaperWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, StickyPaperWallCateActivity.class);
                startActivity(intent);
            }
        });
        BtnSpices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, SpicesCateActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to go back or finish the current activity
                finish(); // This will finish the current activity and go back
            }
        });
    }
}