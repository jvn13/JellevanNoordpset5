package com.example.jelle.jellevannoord_pset5;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuFragment extends ListFragment {

    private ArrayList<Dish> itemsArrayList = new ArrayList();
    private ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = this.getArguments();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        adapter = new CustomListAdapter(getContext(), R.layout.menu_row, itemsArrayList);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, "https://resto.mprog.nl/menu?category=" + arguments.getString("category"), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jArray = response.optJSONArray("items");
                        if (jArray != null) {
                            for (int i=0;i<jArray.length();i++){
                                JSONObject item = jArray.optJSONObject(i);
                                itemsArrayList.add(new Dish(item.optInt("id"), item.optInt("price"), item.optString("name"),item.optString("image_url")));
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsObjRequest);
        this.setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        RestoDatabase db = RestoDatabase.getInstance(getContext());
        Dish dish = (Dish) l.getItemAtPosition(position);
        if(db.containsID(dish.getId())) {
            db.updateItem(dish.getId());
            MainActivity.orderBadge.onOrderSetChanged(getContext());
        } else {
            db.addItem(dish.getId(), dish.getName(), dish.getPrice(), dish.getImage());
            MainActivity.orderBadge.onOrderSetChanged(getContext());
        }
        // create snackbar to display item has been added
    }


}
