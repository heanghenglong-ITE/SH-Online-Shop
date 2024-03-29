package com.example.shonlineshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shonlineshop.Domain.ActivityDomain;
import com.example.shonlineshop.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
public class Product2Adapter extends RecyclerView.Adapter<Product2Adapter.ViewHolder> {

    private final Context context;
    private final List<ActivityDomain> activityDomains;
    private OnItemClickListener onItemClickListener;

    public Product2Adapter(Context context, List<ActivityDomain> activityDomains) {
        this.context = context;
        this.activityDomains = activityDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_product2_cate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityDomain activityDomain = activityDomains.get(position);
        holder.textName.setText(activityDomain.getName());
        holder.textPrice.setText(activityDomain.getPrice());

        // Load image using Picasso with error handling
        Picasso.get()
                .load(activityDomain.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
                .error(R.drawable.ic_launcher_foreground) // Error image if the loading fails
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Image loaded successfully
                    }

                    @Override
                    public void onError(Exception e) {
                        // Error loading image
                    }
                });

        // Set click listener on the item view
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(activityDomain);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textPrice, textName, TextDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName = itemView.findViewById(R.id.textName);
        }
    }

    public void setData(List<ActivityDomain> newData) {
        activityDomains.clear();
        activityDomains.addAll(newData);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(ActivityDomain activityDomain);
    }
    public void setDisplayMode(boolean isGridLayout) {
        // Implement the logic to handle the display mode (if needed)
        notifyDataSetChanged(); // Refresh the adapter after updating the display mode
    }

}
