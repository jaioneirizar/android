package com.jaioneirizar.earthquakes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.fragments.EarthQuakeListFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;
public class EarthQuakeActivityDetail extends ActionBarActivity {

    private EarthQuake earthQuake;
    private TextView lblid;
    private TextView lblsite;
    private TextView lblurl;

    private EarthQuakeDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_earth_quake_activity_detail);
        db = new EarthQuakeDB(this);


        lblid= (TextView) findViewById(R.id.lblid);
        lblsite= (TextView) findViewById(R.id.lblsite);
        lblurl= (TextView) findViewById(R.id.lblurl);
        Intent detailIntent = getIntent();
       // Log.d("CHANGE", String.valueOf(lblid));
      //  earthQuake= detailIntent.getParcelableExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        //earthQuake= detailIntent.getExtras(EarthQuakeListFragment.EARTHQUAKES_ITEM);

        String id = detailIntent.getStringExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        earthQuake = db.getById(id);

        //Log.d("change", earthQuake.get_id());
        populateView(earthQuake);

    }





    private void populateView(EarthQuake earthquake){

        lblid.setText(earthQuake.get_id());
        lblsite.setText(earthQuake.getPlace());
        lblurl.setText(earthQuake.getUrl());
       // lblurl.setText(Html.fromHtml("<a href=" + earthQuake.getUrl() + "></a>"));

    }
}
