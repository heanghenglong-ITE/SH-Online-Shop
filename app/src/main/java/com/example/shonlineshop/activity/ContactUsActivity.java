package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shonlineshop.R;

public class ContactUsActivity extends AppCompatActivity {

    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        backButton = findViewById(R.id.back_button_contact);
        ImageView imageViewClickfb = findViewById(R.id.facebook_contact);
        ImageView imageViewClickig = findViewById(R.id.instragram_contact);
        ImageView imageViewClicktt = findViewById(R.id.twitter_contact);

        imageViewClickfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });
        imageViewClickig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstragram();
            }
        });
        imageViewClicktt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitter();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Back to Account", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
    private void openFacebook(){
        Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=61551014832717");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void openInstragram() {
        Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=61551014832717");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void openTwitter() {
        Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=61551014832717");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}