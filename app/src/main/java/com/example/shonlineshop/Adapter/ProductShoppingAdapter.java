package com.example.shonlineshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shonlineshop.Domain.ShoppingDomain;
import com.example.shonlineshop.R;

import java.util.ArrayList;

public class ProductShoppingAdapter extends RecyclerView.Adapter<ProductShoppingAdapter.ViewHolder> {

    ArrayList<ShoppingDomain> items =new ArrayList<>();

    public ProductShoppingAdapter(ArrayList<ShoppingDomain> items){
        this.items = items;
    }
    @NonNull
    @Override
    public ProductShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product_shopping,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductShoppingAdapter.ViewHolder holder, int position) {
        holder.productName.setText(items.get(position).getProductName());
        holder.productPrice.setText(items.get(position).getProductPrice());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImageUrl()
        ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
