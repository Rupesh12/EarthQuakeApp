/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class EarthquakeActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter adapter ;
    private TextView mEmptyView ;
    private ListView earthquakeListView = null ;
    private final String QUERY = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        Bundle query = new Bundle();
        query.putString("query",QUERY);
        mEmptyView = (TextView) findViewById(R.id.empty_view) ;

         earthquakeListView = (ListView) findViewById(R.id.list);
        assert earthquakeListView != null;
        earthquakeListView.setEmptyView(findViewById(R.id.empty_view));


        adapter = new EarthquakeAdapter(this,new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        getLoaderManager().initLoader(0,query,EarthquakeActivity.this) ;

    }

    private void updateUI(List<Earthquake> earthquakes){

        if(earthquakes == null){

            return ;
        }

        adapter.addAll(earthquakes);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adapter.getItem(position).getmUrl()));
                if(browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
            }
        });
    }


    @Override
    public android.content.Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
      //  Log.e(LOG_TAG,"onCreate") ;
        return new EarthquakeLoader(this,args);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Earthquake>> loader, List<Earthquake> data) {
      //  Log.e(LOG_TAG,"onFinished") ;
        View loading_spinner = findViewById(R.id.loading_spinner);

        loading_spinner.setVisibility(View.GONE);
        mEmptyView.setText(R.string.no_earthquakes_found_ground_is_literally_stable);

        updateUI(data);

    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Earthquake>> loader) {
        Log.e(LOG_TAG,"onReset") ;
        adapter.clear();
    }
}
