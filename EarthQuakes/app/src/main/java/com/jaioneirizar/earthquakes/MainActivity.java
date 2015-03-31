package com.jaioneirizar.earthquakes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.services.DownloadEarthQuakesService;
import com.jaioneirizar.earthquakes.tasks.DowloadEarthQuakesTask;


public class MainActivity extends ActionBarActivity implements DowloadEarthQuakesTask.AddEarthQuakeInterface {

    private static final int PREFS_ACTIVITY = 1 ;
    private EarthQuakeDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent download = new Intent(this, DownloadEarthQuakesService.class);
        startService(download);
      /*  DowloadEarthQuakesTask task = new DowloadEarthQuakesTask(this,this);

        task.execute(getString(R.string.earthquakesurl));*/




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

       // dbHelper.queryAll();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent prefIntent;
            prefIntent = new Intent(this, SettingsActivity.class);
            startActivityForResult(prefIntent, PREFS_ACTIVITY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void notifyTotal(int Total) {
        String msg= getString(R.string.Mensaje,Total);
        Toast toast=  Toast.makeText( this,msg + Total, Toast.LENGTH_LONG);
        toast.show();
    }

  //  @Override
   /* public  void addEarthQuake(EarthQuake earthquake) {

        //earthQcuakes.add(0, earthquake);
        //aa.notifyDataSetChanged();
        ContentValues newValues = new ContentValues();

        newValues.put(dbHelper.KEY_TIME, earthquake.getTime().getTime());
        newValues.put(dbHelper.KEY_PLACE, earthquake.getPlace());
        newValues.put(dbHelper.KEY_LONG, earthquake.getCoords().getLng());
        newValues.put(dbHelper.KEY_LAT, earthquake.getCoords().getLat());
        newValues.put(dbHelper.KEY_URL, earthquake.getUrl());
        newValues.put(dbHelper.KEY_MAGNITUDE, earthquake.getMagnitude());
        newValues.put(dbHelper.KEY_DEPTH, earthquake.getCoords().getDepth());

        dbHelper.createRow(newValues);
        Log.d("CHANGE","JAIONE");



    }*/
}
