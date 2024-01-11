package com.example.shonlineshop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shonlineshop.Adapter.HomeAdapter;
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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<ActivityDomain> originalActivityDomain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.RecyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new HomeAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this::openDetailView);

        makeApiRequest("all");

        return rootView;
    }

    private void openDetailView(ActivityDomain activityDomain) {
        // Implement your logic for opening the detail view
    }

    private void makeApiRequest(String category) {
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://tochhit.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create Service object
        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<ActivityDomain>> call = apiService.GetHomeShShop();

        call.enqueue(new Callback<List<ActivityDomain>>() {
            @Override
            public void onResponse(@NonNull Call<List<ActivityDomain>> call, @NonNull Response<List<ActivityDomain>> response) {
                if (response.isSuccessful()) {
                    List<ActivityDomain> activityDomain = response.body();
                    if (activityDomain != null && !activityDomain.isEmpty()) {
                        originalActivityDomain = new ArrayList<>(activityDomain);  // Save the original data
                        if ("all".equals(category)) {
                            adapter.setData(activityDomain);
                        } else {
                            // Filter by category
                            List<ActivityDomain> filteredList = filterByActivity(activityDomain, category);
                            adapter.setData(filteredList);
                        }
                    } else {
                        Log.e("APIResponse", "Empty or null response body");
                        Toast.makeText(getActivity(), "Error: Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("APIResponse", "Error: " + response.message());
                    Toast.makeText(getActivity(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ActivityDomain>> call, @NonNull Throwable t) {
                Log.e("APIResponse", "Network error: " + t.getMessage());
                Toast.makeText(getActivity(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<ActivityDomain> filterByActivity(List<ActivityDomain> categoryDomains, String category) {
        // Filter the list based on the selected category
        // You can implement your own logic here
        // For simplicity, this example filters by matching the category name
        return categoryDomains.stream()
                .filter(item -> category.equalsIgnoreCase(item.getCategory()))
                .collect(Collectors.toList());
    }
}
