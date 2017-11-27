package com.example.jelle.jellevannoord_pset5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Dish> {

    private Context mContext;
    int mResource;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Dish> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameTV = convertView.findViewById(R.id.nameTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);

        nameTV.setText(getItem(position).getName());
        priceTV.setText("€" + String.valueOf(getItem(position).getPrice()));
        return convertView;
    }
}
