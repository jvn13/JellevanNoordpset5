package com.example.jelle.jellevannoord_pset5;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class OrderBadge {

    private TextView cartBadge;

    public OrderBadge(TextView cartBadge) {
        this.cartBadge = cartBadge;
    }

    public void onOrderSetChanged(Context context) {
        RestoDatabase db = RestoDatabase.getInstance(context);
        int amount = db.getTotalAmount();
        if(amount == 0) {
            this.cartBadge.setVisibility(View.GONE);
        } else {
            this.cartBadge.setVisibility(View.VISIBLE);
            this.cartBadge.setText(String.valueOf(amount));
        }
    }
}

