package com.example.shonlineshop.activity;


import androidx.appcompat.app.AppCompatActivity;


import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.shonlineshop.R;


import org.w3c.dom.Text;

public class PaymentMethodActivity extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Back to Payment", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

}