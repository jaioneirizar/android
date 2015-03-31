package com.jaioneirizar.earthquakes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
        Log.d("CHANGE", String.valueOf(lblid));
        earthQuake= detailIntent.getParcelableExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        //earthQuake= detailIntent.getExtras(EarthQuakeListFragment.EARTHQUAKES_ITEM);


        //Log.d("change", earthQuake.get_id());
        populateView();

    }





    private void populateView(){

        Log.d("JAIONE", earthQuake.get_id());

       // lblid.setText(earthQuake.get_id());
        lblsite.setText(earthQuake.getPlace());

    }
}
