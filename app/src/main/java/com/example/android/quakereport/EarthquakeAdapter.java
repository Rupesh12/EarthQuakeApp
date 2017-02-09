package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        TextView place_offset = (TextView) listItemView.findViewById(R.id.place_offset) ;
        TextView date = (TextView) listItemView.findViewById(R.id.date) ;
        TextView time = (TextView) listItemView.findViewById(R.id.time) ;
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // getting the background color for earthquake
        int magBackground = getMagnitudeColor(current.getmMag());
        magnitudeCircle.setColor(magBackground);
        String ori_place = current.getmPlace() ;
        String primary= "" ;
        String offset = "" ;
        if(ori_place.charAt(0) >= '0' && ori_place.charAt(0) <= '9'){
            int temp = ori_place.indexOf(" of");
            offset = ori_place.substring(0,temp+3) ;
            primary = ori_place.substring(offset.length()+1) ;
        }else{
            offset = "Near the ";
            primary = ori_place ;
        }

        magnitude.setText(formatMag(current.getmMag()));

        place.setText(primary);
        place_offset.setText(offset);





       Date dateObject  = new Date(current.getmDate());
        String formattedDate = formatDate(dateObject) ;
        date.setText(formattedDate);
        String formattedTime = formatTime(dateObject) ;
        time.setText(formattedTime);

        return listItemView ;
    }

    private int getMagnitudeColor(double v) {

        DecimalFormat decimalFormat = new DecimalFormat("0") ;
        String mag = decimalFormat.format(v) ;


        switch (mag){
            case "0":

            case"1":
                return ContextCompat.getColor(getContext(),R.color.magnitude1);
            case "2":
                return ContextCompat.getColor(getContext(),R.color.magnitude2);
            case "3":
                return ContextCompat.getColor(getContext(),R.color.magnitude3);
            case "4":
                return ContextCompat.getColor(getContext(),R.color.magnitude4);
            case "5":
                return ContextCompat.getColor(getContext(),R.color.magnitude5);
            case "6":
                return ContextCompat.getColor(getContext(),R.color.magnitude6);
            case "7" :
                return ContextCompat.getColor(getContext(),R.color.magnitude7);
            case "8":
                return ContextCompat.getColor(getContext(),R.color.magnitude8);
            case "9" :
                return ContextCompat.getColor(getContext(),R.color.magnitude9);
            case "10" :
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
            default:
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);

        }



    }

    private String formatMag(double l) {

        DecimalFormat decimalFormatter = new DecimalFormat("0.0") ;
        return decimalFormatter.format(l);

    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h :mm a");
        return timeFormat.format(dateObject);

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy") ;
        return dateFormat.format(dateObject) ;

    }
}
