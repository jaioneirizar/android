package com.jaioneirizar.earthquakes.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jaioneirizar.earthquakes.EarthQuakeActivityDetail;
import com.jaioneirizar.earthquakes.R;

import com.jaioneirizar.earthquakes.fragments.dummy.DummyContent;
import com.jaioneirizar.earthquakes.model.Coordinate;
import com.jaioneirizar.earthquakes.model.EarthQuake;
import com.jaioneirizar.earthquakes.tasks.DowloadEarthQuakesTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.jaioneirizar.earthquakes.adapters.earthQuakesAdapter;

import static android.widget.Toast.makeText;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the //{@link //OnFragmentInteractionListener}
 * interface.
 */
public class EarthQuakeListFragment extends ListFragment implements DowloadEarthQuakesTask.AddEarthQuakeInterface

{

    public static final String EARTHQUAKES_ITEM = "EARTHQUAKES" ;
    private static final String DATA = "datos";

    private JSONObject json;
    private ArrayList<EarthQuake> earthQuakes;
    private ArrayAdapter<EarthQuake> aa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        earthQuakes = new ArrayList<EarthQuake>();

        DowloadEarthQuakesTask task = new DowloadEarthQuakesTask(this);
        task.execute(getString(R.string.earthquakesurl));

       /* Thread t=  new Thread(new Runnable() {
            @Override
            public void run() {
                updatedEarthQuake();


            }
        });
        t.start();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=super.onCreateView(inflater, container, savedInstanceState);

        //aa = new earthQuakesAdapter(getActivity(),R.layout.earthquakes_list,earthQuakes);


        earthQuakes= new ArrayList<EarthQuake>();
        aa = new earthQuakesAdapter(getActivity(),R.layout.earthquakes_list, earthQuakes);

      if(savedInstanceState!= null) {

          ArrayList<EarthQuake> tmp = savedInstanceState.getParcelableArrayList(DATA);

          if (tmp != null) {

              earthQuakes.addAll(tmp);
          }
      }
        setListAdapter(aa);

        return layout;

    }


    @Override
    public void addEarthQuake(EarthQuake earthquake) {
        earthQuakes.add(0, earthquake);
        aa.notifyDataSetChanged();
    }

    @Override
    public void notifyTotal(int Total) {

        CharSequence text = Total+"Terremotos";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getActivity(), text, duration);
        toast.show();
    }




    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        EarthQuake earthQuake= earthQuakes.get(position);

        Intent detailIntent = new Intent(getActivity(), EarthQuakeActivityDetail.class);
        detailIntent.putExtra(EARTHQUAKES_ITEM,earthQuake);
        startActivity(detailIntent);
    }
}