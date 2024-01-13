package com.example.shonlineshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shonlineshop.Domain.ActivityDomain;
import com.example.shonlineshop.R;
import com.example.shonlineshop.activity.DetailActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private final Context context;
    private final List<ActivityDomain> activityDomain;
    private ViewPager viewPager;
    private Handler handler;
    private Runnable runnable;
    private int currentPosition = 0;
    private final int DELAY_MS = 3000; // Delay in milliseconds between auto-scrolls

    public ViewPagerAdapter(Context context, List<ActivityDomain> activityDomain, ViewPager viewPager) {
        this.context = context;
        this.activityDomain = activityDomain;
        this.viewPager = viewPager;
        this.handler = new Handler(Looper.getMainLooper());
        initAutoScroll();
    }

    private void initAutoScroll() {
        runnable = () -> {
            if (currentPosition == activityDomain.size()) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
            viewPager.setCurrentItem(currentPosition, true);
            handler.postDelayed(runnable, DELAY_MS);
        };

        handler.postDelayed(runnable, DELAY_MS);
    }

    @Override
    public int getCount() {
        return activityDomain.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_view_pager_item_layout, container, false);

        ActivityDomain currentItem = activityDomain.get(position);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textName = view.findViewById(R.id.textName);
        TextView textPrice = view.findViewById(R.id.textPrice);

        textName.setText(currentItem.getName());
        textPrice.setText(currentItem.getPrice());

        Picasso.get()
                .load(currentItem.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Image loaded successfully
                    }

                    @Override
                    public void onError(Exception e) {
                        // Error loading image
                    }
                });
        view.setOnClickListener(v -> {
            // Assuming you have an Intent to start the detail activity
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("itemId", currentItem.getId());  // Use currentItem instead of activityDomain
            intent.putExtra("itemName", currentItem.getName());  // Use currentItem instead of activityDomain
            intent.putExtra("imageUrl", currentItem.getImageUrl());  // Use currentItem instead of activityDomain
            intent.putExtra("itemPrice", currentItem.getPrice());
            intent.putExtra("itemSize", currentItem.getSize());
            intent.putExtra("itemDescription", currentItem.getDescription());// Use currentItem instead of activityDomain
            // Add any other data you want to pass to the detail activity
            context.startActivity(intent);
        });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setData(List<ActivityDomain> newData) {
        // Clear existing data and add the new data
        activityDomain.clear();
        activityDomain.addAll(newData);
        notifyDataSetChanged();
    }

}
