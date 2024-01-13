    package com.example.shonlineshop.fragment;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.RelativeLayout;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import androidx.viewpager.widget.ViewPager;

    import com.example.shonlineshop.Adapter.HomeAdapter;
    import com.example.shonlineshop.Adapter.ViewPagerAdapter;
    import com.example.shonlineshop.Domain.ActivityDomain;
    import com.example.shonlineshop.R;
    import com.example.shonlineshop.activity.CategoriesActivity;
    import com.example.shonlineshop.activity.DetailActivity;
    import com.example.shonlineshop.activity.FavItemActivity;
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

        private ViewPager viewPager;
        private ViewPagerAdapter viewPagerAdapter;
        private List<ActivityDomain> viewPagerData;

        // Declare Retrofit as a class variable
        private Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://tochhit.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inside your HomeFragment
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);

            recyclerView = rootView.findViewById(R.id.RecyclerView2);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            adapter = new HomeAdapter(getActivity(), new ArrayList<>());
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this::openDetailView);

            viewPager = rootView.findViewById(R.id.viewPager);

            // Use requireActivity() to get the FragmentActivity
            viewPagerAdapter = new ViewPagerAdapter(requireActivity(), new ArrayList<>(), viewPager);
            viewPager.setAdapter(viewPagerAdapter);

            ImageView btnCate = rootView.findViewById(R.id.btnCate);
            RelativeLayout favClcik = rootView.findViewById(R.id.RelaFavorit);
            btnCate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle button click, navigate to CategoriesActivity
                    navigateToCategoriesActivity();
                }
            });
            favClcik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteClick();
                }
            });

            makeApiRequest("all");
            fetchViewPagerData();

            return rootView;
        }
        private void navigateToCategoriesActivity() {
            Intent intent = new Intent(getActivity(), CategoriesActivity.class);
            startActivity(intent);
        }
        private void favoriteClick(){
            Intent intent = new Intent(getActivity(), FavItemActivity.class);
            startActivity(intent);
        }


        private void openDetailView(ActivityDomain activityDomain) {
            // Implement your logic for opening the detail view
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("itemId", activityDomain.getId());
            intent.putExtra("itemName", activityDomain.getName());
            intent.putExtra("imageUrl", activityDomain.getImageUrl());
            intent.putExtra("itemPrice", activityDomain.getPrice());
            intent.putExtra("itemSize", activityDomain.getSize());
            intent.putExtra("itemDescription", activityDomain.getDescription());
            startActivity(intent);
        }

        private void makeApiRequest(String category) {
            ApiService apiService = httpClient.create(ApiService.class);
            Call<List<ActivityDomain>> call = apiService.GetShShop2();

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

        private void fetchViewPagerData() {
            ApiService apiService = httpClient.create(ApiService.class);
            Call<List<ActivityDomain>> call = apiService.GetShShop3();

            call.enqueue(new Callback<List<ActivityDomain>>() {
                @Override
                public void onResponse(@NonNull Call<List<ActivityDomain>> call,
                                       @NonNull Response<List<ActivityDomain>> response) {
                    if (response.isSuccessful()) {
                        viewPagerData = response.body();
                        if (viewPagerData != null && !viewPagerData.isEmpty()) {
                            updateViewPager(viewPagerData);
                        } else {
                            Log.e("APIResponse", "Empty or null ViewPager response body");
                        }
                    } else {
                        Log.e("APIResponse", "Error fetching ViewPager data: " + response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<ActivityDomain>> call,
                                      @NonNull Throwable t) {
                    Log.e("APIResponse", "Network error fetching ViewPager data: " + t.getMessage());
                }
            });
        }

        private void updateViewPager(List<ActivityDomain> viewPagerData) {
            // Implement logic to update your ViewPager with the data
            viewPagerAdapter.setData(viewPagerData);
        }

    }
