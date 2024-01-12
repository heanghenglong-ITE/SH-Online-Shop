package com.example.shonlineshop.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shonlineshop.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    public CustomArrayAdapter(Context context, int resource, String[] deliveryOptions) {
        super(context, resource, deliveryOptions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);

        // Set the text size and color
        textView.setTextSize(18); // Set the desired text size
        textView.setTextColor(Color.BLUE); // Set the desired text color

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);

        // Set the text size and color for the dropdown items
        textView.setTextSize(20); // Set the desired text size
        textView.setTextColor(Color.BLACK); // Set the desired text color

        return view;
    }
}