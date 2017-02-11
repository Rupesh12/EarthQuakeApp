package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by rupesh on 2/10/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String query ;
    public EarthquakeLoader(Context context, Bundle bundle) {
        super(context);
        query = bundle.getString("query") ;

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
