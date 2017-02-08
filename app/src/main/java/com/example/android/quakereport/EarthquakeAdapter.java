package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rupesh on 2/8/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){

        super(context,0,earthquakes) ;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView ;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake current = getItem(position) ;

        TextView magnitude = (TextView) listItemView.findViewById(R.id.mag) ;
        TextView place = (TextView) listItemView.findViewById(R.id.place) ;
        TextView date = (TextView) listItemView.findViewById(R.id.date) ;

        magnitude.setText(current.getmMag());
        place.setText(current.getmPlace());
        date.setText(current.getmDate());


        return listItemView ;
    }
}
