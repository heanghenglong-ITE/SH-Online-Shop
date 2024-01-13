package com.example.shonlineshop.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

        RelativeLayout imageViewClickOrder = rootView.findViewById(R.id.order_click_re);
        RelativeLayout imageViewClickAccInfo = rootView.findViewById(R.id.acc_click_re);
        RelativeLayout imageViewClickInterest = rootView.findViewById(R.id.fav_clcik_re);
        RelativeLayout imageViewClickContact = rootView.findViewById(R.id.contact_clcik_re);
        RelativeLayout imageViewClickAbout = rootView.findViewById(R.id.about_click_re);
        RelativeLayout imageViewClickLogout = rootView.findViewById(R.id.logout_click_re);

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