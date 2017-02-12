package com.simplyu.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by rupesh on 2/10/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String query ;
    public EarthquakeLoader(Context context, String q) {
        super(context);
        query = q ;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.e("Earth_Quake","loadInBackGround") ;

        if(query == null || query.length() == 0){
            return null ;
        }

        return QueryUtil.fetchEarthquakes(query);


    }



}
