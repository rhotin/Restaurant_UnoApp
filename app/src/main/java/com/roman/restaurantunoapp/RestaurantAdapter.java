package com.roman.restaurantunoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by roman on 11/5/2015.
 */
public class RestaurantAdapter  extends BaseAdapter {

    ArrayList<RestaurantObject> list;
    Context context;

    RestaurantAdapter(Context c, ArrayList<RestaurantObject> restaurantObjects){
        this.context = c;
        this.list = restaurantObjects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_rest_row, parent, false);

        TextView restName = (TextView) row.findViewById(R.id.restTextView);

        RestaurantObject theRestObject = list.get(position);
        restName.setText(theRestObject.restName);

        return row;
    }
}
