package com.example.shonlineshop.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shonlineshop.R;
import com.example.shonlineshop.activity.AboutUsActivity;
import com.example.shonlineshop.activity.AccountInformationActivity;
import com.example.shonlineshop.activity.ContactUsActivity;
import com.example.shonlineshop.activity.FavItemActivity;
import com.example.shonlineshop.activity.LoginActivity;
import com.example.shonlineshop.activity.OrderInformationActivity;
import com.example.shonlineshop.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        ImageView imageViewClickOrder = rootView.findViewById(R.id.order_click);
        ImageView imageViewClickAccInfo = rootView.findViewById(R.id.account_click);
        ImageView imageViewClickInterest = rootView.findViewById(R.id.interest_click);
        ImageView imageViewClickContact = rootView.findViewById(R.id.contact_click);
        ImageView imageViewClickAbout = rootView.findViewById(R.id.about_click);
        ImageView imageViewClickLogout = rootView.findViewById(R.id.logout_click);

        imageViewClickOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderInformationActivity.class);
                startActivity(intent);
            }
        });
        imageViewClickAccInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountInformationActivity.class);
                startActivity(intent);
            }
        });
        imageViewClickInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavItemActivity.class);
                startActivity(intent);
            }
        });
        imageViewClickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
        imageViewClickAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        imageViewClickLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return rootView;


    }
}