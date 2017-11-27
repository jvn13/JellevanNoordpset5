package com.example.jelle.jellevannoord_pset5;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RestoAdapter extends ResourceCursorAdapter {

    public RestoAdapter(Context context, Cursor cursor, int flags) {
        super(context, R.layout.order_row, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id = view.findViewById(R.id.idTV);
        id.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));

        TextView name = view.findViewById(R.id.nameTV);
        name.setText(cursor.getString(cursor.getColumnIndex("name")));

        TextView amount = view.findViewById(R.id.amountTV);
        amount.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("amount"))));

        TextView price = view.findViewById(R.id.priceTV);
        price.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("price")) * cursor.getInt(cursor.getColumnIndex("amount"))));
    }
}
