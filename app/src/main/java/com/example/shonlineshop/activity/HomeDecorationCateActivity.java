package com.example.shonlineshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shonlineshop.Adapter.HomeAdapter;
import com.example.shonlineshop.Adapter.Product1Adapter;
import com.example.shonlineshop.Adapter.Product2Adapter;
import com.example.shonlineshop.Adapter.SearchAdapter;
import com.example.shonlineshop.Domain.ActivityDomain;
import com.example.shonlineshop.R;
import com.example.shonlineshop.api.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeDecorationCateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Product1Adapter product1Adapter;
    private Product2Adapter product2Adapter;
    private List<ActivityDomain> originalActivityDomain;
    private boolean isGridLayout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_decoration_cate);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        product1Adapter = new Product1Adapter(this, new ArrayList<>()); // Default to product1Adapter
        product2Adapter = new Product2Adapter(this, new ArrayList<>()); // Default to product2Adapter
        recyclerView.setAdapter(product1Adapter);

        // Set item click listener for product1Adapter
        product1Adapter.setOnItemClickListener(this::openDetailView);

        ImageView changeLinearImageView = findViewById(R.id.ChangeLinear);
        changeLinearImageView.setOnClickListener(view -> toggleLayoutManager());

        ImageView backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(view -> onBackPressed());

        makeApiRequest("Home Decoration");
    }

    private void openDetailView(ActivityDomain activityDomain) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemId", activityDomain.getId());
        intent.putExtra("itemName", activityDomain.getName());
        intent.putExtra("imageUrl", activityDomain.getImageUrl());
        intent.putExtra("itemPrice", activityDomain.getPrice());
        intent.putExtra("itemSize", activityDomain.getSize());
        intent.putExtra("itemDescription", activityDomain.getDescription());
        startActivity(intent);
    }

    private void makeApiRequest(String category) {
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://tochhit.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<ActivityDomain>> call = apiService.GetHomeShShop();

        call.enqueue(new Callback<List<ActivityDomain>>() {
            @Override
            public void onResponse(@NonNull Call<List<ActivityDomain>> call, @NonNull Response<List<ActivityDomain>> response) {
                if (response.isSuccessful()) {
                    List<ActivityDomain> activityDomain = response.body();
                    if (activityDomain != null && !activityDomain.isEmpty()) {
                        originalActivityDomain = new ArrayList<>(activityDomain);
                        if ("all".equals(category)) {
                            // Use the current adapter type (product1Adapter or product2Adapter)
                            if (isGridLayout) {
                                product1Adapter.setData(activityDomain);
                            } else {
                                product2Adapter.setData(activityDomain);
                            }
                        } else {
                            List<ActivityDomain> filteredList = filterByActivity(activityDomain, category);
                            if (isGridLayout) {
                                product1Adapter.setData(filteredList);
                            } else {
                                product2Adapter.setData(filteredList);
                            }
                        }
                    } else {
                        Log.e("APIResponse", "Empty or null response body");
                        Toast.makeText(HomeDecorationCateActivity.this, "Error: Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("APIResponse", "Error: " + response.message());
                    Toast.makeText(HomeDecorationCateActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ActivityDomain>> call, @NonNull Throwable t) {
                Log.e("APIResponse", "Network error: " + t.getMessage());
                Toast.makeText(HomeDecorationCateActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<ActivityDomain> filterByActivity(List<ActivityDomain> activityDomain, String category) {
        return activityDomain.stream()
                .filter(item -> category.equalsIgnoreCase(item.getCategory()))
                .collect(Collectors.toList());
    }

    private void toggleLayoutManager() {
        RecyclerView.LayoutManager layoutManager;

        if (isGridLayout) {
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(product2Adapter);
            product2Adapter.setOnItemClickListener(this::openDetailView);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setAdapter(product1Adapter);
            product1Adapter.setOnItemClickListener(this::openDetailView);
        }

        recyclerView.setLayoutManager(layoutManager);
        isGridLayout = !isGridLayout;

        // Fetch data from API based on the selected layout manager type
        makeApiRequest("Home Decoration");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Handle the back button click here (navigate back)
    }
}