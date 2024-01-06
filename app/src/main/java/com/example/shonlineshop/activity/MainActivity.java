package com.example.shonlineshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.shonlineshop.R;
import com.example.shonlineshop.fragment.AccountFragment;
import com.example.shonlineshop.fragment.HomeFragment;
import com.example.shonlineshop.fragment.SearchFragment;
import com.example.shonlineshop.fragment.ShoppingCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(this);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

    }


    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selecteFragment;
        if (item.getItemId() == R.id.home_menu){
            selecteFragment = new HomeFragment();
        } else if (item.getItemId() == R.id.search_menu) {
            selecteFragment = new SearchFragment();
        } else if (item.getItemId() == R.id.shopping_cart_menu) {
            selecteFragment = new ShoppingCartFragment();
        } else if (item.getItemId() == R.id.account_menu) {
            selecteFragment = new AccountFragment();
        }else {
            return false;
        };

        loadFragment(selecteFragment);
        return true;
    }

}