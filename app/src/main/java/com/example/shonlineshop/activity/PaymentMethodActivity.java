package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.shonlineshop.R;
import com.stripe.android.view.CardFormView;
import com.stripe.android.view.CardMultilineWidget;

public class PaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

    }
}