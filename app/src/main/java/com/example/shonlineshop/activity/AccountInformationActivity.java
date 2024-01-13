package com.example.shonlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shonlineshop.R;

public class AccountInformationActivity extends AppCompatActivity {
    private ImageView backButton;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    private static final String SHARED_HEAD_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);

        backButton = findViewById(R.id.back_button_info);
        editTextName = findViewById(R.id.editText_username);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPhone = findViewById(R.id.editText_phone);
        editTextPassword = findViewById(R.id.editText_password);
        saveButton = findViewById(R.id.change_button);

        sharedPreferences = getSharedPreferences(SHARED_HEAD_NAME, MODE_PRIVATE);

        loadData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(AccountInformationActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, editTextName.getText().toString());
        editor.putString(KEY_EMAIL, editTextEmail.getText().toString());
        editor.putString(KEY_PHONE, editTextPhone.getText().toString());
        editor.putString(KEY_PASSWORD, editTextPassword.getText().toString());
        editor.apply();
    }

    private void loadData() {
        String name = sharedPreferences.getString(KEY_NAME, "");
        String email = sharedPreferences.getString(KEY_EMAIL, "");
        String phone = sharedPreferences.getString(KEY_PHONE, "");
        String password = sharedPreferences.getString(KEY_PASSWORD, "");

        editTextName.setText(name);
        editTextEmail.setText(email);
        editTextPhone.setText(phone);
        editTextPassword.setText(password);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}