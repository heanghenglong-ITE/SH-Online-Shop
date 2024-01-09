package com.example.shonlineshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shonlineshop.Adapter.ProductShoppingAdapter;
import com.example.shonlineshop.Domain.ShoppingDomain;
import com.example.shonlineshop.R;
import com.example.shonlineshop.activity.PaymentMethodActivity;
import com.example.shonlineshop.databinding.FragmentShoppingCartBinding;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment {

    private FragmentShoppingCartBinding binding;
    private RecyclerView.Adapter adapterShopping;
    private RecyclerView recyclerViewShopping;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false);
        View rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        TextView textView = rootView.findViewById(R.id.payment_textview);
        ImageView imageViewClick = rootView.findViewById(R.id.imageview_click);

        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentMethodActivity.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentMethodActivity.class);
                startActivity(intent);
            }
        });

        initRecyclerView(rootView);

        return rootView;
    }

    private void initRecyclerView(View rootView) {
        ArrayList<ShoppingDomain> shoppingDomains = new ArrayList<>();
        shoppingDomains.add(new ShoppingDomain("Curtain 1","USD 15","img1"));
        shoppingDomains.add(new ShoppingDomain("Curtain 2","USD 16","img2"));
        shoppingDomains.add(new ShoppingDomain("Curtain 3","USD 17","img3"));

        recyclerViewShopping = rootView.findViewById(R.id.recyclerView);
        recyclerViewShopping.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapterShopping = new ProductShoppingAdapter(shoppingDomains);
        recyclerViewShopping.setAdapter(adapterShopping);
    }
}