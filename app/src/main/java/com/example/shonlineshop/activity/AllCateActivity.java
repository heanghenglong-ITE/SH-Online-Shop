package com.example.shonlineshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shonlineshop.Adapter.HomeAdapter;
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

public class AllCateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private SearchAdapter searchAdapter;
    private List<ActivityDomain> originalActivityDomain;
    private boolean isGridLayout = true; // Flag to track the current layout manager type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cate);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        homeAdapter = new HomeAdapter(this, new ArrayList<>()); // Default to HomeAdapter
        searchAdapter = new SearchAdapter(this, new ArrayList<>()); // Default to SearchAdapter
        recyclerView.setAdapter(homeAdapter);

        // Set item click listener for HomeAdapter
        homeAdapter.setOnItemClickListener(this::openDetailView);

        ImageView changeLinearImageView = findViewById(R.id.ChangeLinear);
        changeLinearImageView.setOnClickListener(view -> toggleLayoutManager());

        ImageView backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(view -> onBackPressed());

        makeApiRequest("all");
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
                            // Use the current adapter type (HomeAdapter or SearchAdapter)
                            if (isGridLayout) {
                                homeAdapter.setData(activityDomain);
                            } else {
                                searchAdapter.setData(activityDomain);
                            }
                        } else {
                            List<ActivityDomain> filteredList = filterByActivity(activityDomain, category);
                            if (isGridLayout) {
                                homeAdapter.setData(filteredList);
                            } else {
                                searchAdapter.setData(filteredList);
                            }
                        }
                    } else {
                        Log.e("APIResponse", "Empty or null response body");
                        Toast.makeText(AllCateActivity.this, "Error: Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("APIResponse", "Error: " + response.message());
                    Toast.makeText(AllCateActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ActivityDomain>> call, @NonNull Throwable t) {
                Log.e("APIResponse", "Network error: " + t.getMessage());
                Toast.makeText(AllCateActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
            recyclerView.setAdapter(searchAdapter);
            searchAdapter.setOnItemClickListener(this::openDetailView);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.setOnItemClickListener(this::openDetailView);
        }

        recyclerView.setLayoutManager(layoutManager);
        isGridLayout = !isGridLayout;

        // Fetch data from API based on the selected layout manager type
        makeApiRequest("all");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Handle the back button click here (navigate back)
    }
}
