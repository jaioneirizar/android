package com.jaioneirizar.earthquakes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jaioneirizar.earthquakes.fragments.EarthQuakeListFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;


public class EarthQuakeActivityDetail extends ActionBarActivity {

    private EarthQuake earthQuake;
    private TextView lblid;
    private TextView lblsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake_activity_detail);


        lblid= (TextView) findViewById(R.id.lblid);
        lblsite= (TextView) findViewById(R.id.lblsite);
        Intent detailIntent = getIntent();
        earthQuake= detailIntent.getParcelableExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        populateView();

    }





    private void populateView(){

        lblid.setText(earthQuake.get_id());
        lblsite.setText(earthQuake.getPlace());

    }
}
