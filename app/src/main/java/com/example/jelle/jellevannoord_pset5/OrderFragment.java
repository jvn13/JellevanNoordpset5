package com.example.jelle.jellevannoord_pset5;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class OrderFragment extends DialogFragment implements View.OnClickListener {

    static RestoDatabase db;
    static RestoAdapter adapter;
    ListView orderList;
    Button cancelButton;
    Button orderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        orderList = view.findViewById(R.id.order_list);
        orderList.setOnItemLongClickListener(new listLongClickListener());
        cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
        orderButton = view.findViewById(R.id.order_button);
        orderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        db = RestoDatabase.getInstance(getContext());
        adapter = new RestoAdapter(getContext(), db.selectAll(), 0);
        orderList.setAdapter(adapter);
    }

    public static void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_button:
                dismiss();
                break;
            case R.id.order_button:
                break;
        }
    }

    private class listLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            final LinearLayout linearLayout = (LinearLayout) view;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Do you really want to delete this item?").setTitle("Delete");
            // Add the buttons
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    TextView itemId = (TextView) linearLayout.getChildAt(1);
                    db.delete(itemId.getText().toString());
                    updateData();
                    MainActivity.orderBadge.onOrderSetChanged(getContext());
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
    }
}
