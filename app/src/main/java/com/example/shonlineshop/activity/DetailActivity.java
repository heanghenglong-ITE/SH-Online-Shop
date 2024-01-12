package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shonlineshop.R;
import com.example.shonlineshop.fragment.ShoppingCartFragment;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView TextView;
    private Button moreButton;

    private RelativeLayout btnAddCart;

    private boolean isTextExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView = findViewById(R.id.TextDescription);
        moreButton = findViewById(R.id.moreButton);
        btnAddCart = findViewById(R.id.BtnAddCart);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide other views
                findViewById(R.id.Rela1).setVisibility(View.GONE);
                findViewById(R.id.Rela2).setVisibility(View.GONE);
                findViewById(R.id.Rela3).setVisibility(View.GONE);
                findViewById(R.id.BtnAddCart).setVisibility(View.GONE);

                // Show only the fragmentContainer
                findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);

                // Assuming ShoppingCartFragment is the correct fragment for your shopping cart
                ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();

                // Replace or add the ShoppingCartFragment to the current activity
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, shoppingCartFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });




        String initialText = "Your initial text here";
        TextView.setText(initialText);
        moreButton.setVisibility(View.VISIBLE);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle the visibility of the extended text
                if (isTextExpanded) {
                    // Collapse the TextView to show limited lines
                    TextView.setMaxLines(3);
                    moreButton.setText("more...");
                } else {
                    // Expand the TextView to show the full text
                    TextView.setMaxLines(Integer.MAX_VALUE);
                    moreButton.setText("less...");
                }
                isTextExpanded = !isTextExpanded;
            }
        });


        ImageView BackBotton = findViewById(R.id.BackButton);

        BackBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int itemId = getIntent().getIntExtra("itemId", -1);
        String itemName = getIntent().getStringExtra("itemName");
        String itemPrice = getIntent().getStringExtra("itemPrice");
        String itemDiscount = getIntent().getStringExtra("itemDiscount");
        String itemAfterprice = getIntent().getStringExtra("itemAfterprice");
        String itemSize = getIntent().getStringExtra("itemSize");
        String itemDescription = getIntent().getStringExtra("itemDescription");

        ImageView detailImageDetail = findViewById(R.id.ImageDetail);
        TextView detailTextName = findViewById(R.id.TextName);
        TextView detailTextPrice = findViewById(R.id.TextPrice);
        TextView detailTextSize = findViewById(R.id.TextSize);
        TextView detailTextDescription = findViewById(R.id.TextDescription);

        Picasso.get().load(getIntent().getStringExtra("imageUrl")).into(detailImageDetail);

        detailTextName.setText(itemName);
        detailTextPrice.setText(itemPrice);
        detailTextSize.setText(itemSize);
        detailTextDescription.setText(itemDescription);

    }
    @Override
    public void onBackPressed() {
        if (findViewById(R.id.fragmentContainer).getVisibility() == View.VISIBLE) {
            findViewById(R.id.fragmentContainer).setVisibility(View.GONE);
            findViewById(R.id.Rela1).setVisibility(View.VISIBLE);
            findViewById(R.id.Rela2).setVisibility(View.VISIBLE);
            findViewById(R.id.Rela3).setVisibility(View.VISIBLE);
            findViewById(R.id.BtnAddCart).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

}