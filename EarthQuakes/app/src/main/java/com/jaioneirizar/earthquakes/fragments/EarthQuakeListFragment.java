package com.jaioneirizar.earthquakes.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the //{@link //OnFragmentInteractionListener}
 * interface.
 */
public class EarthQuakeListFragment extends ListFragment implements DowloadEarthQuakesTask.AddEarthQuakeInterface

{

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

        aa = new earthQuakesAdapter(getActivity(),R.layout.earthquakes_list,earthQuakes);
        setListAdapter(aa);

        return layout;

    }


    @Override
    public void addEarthQuake(EarthQuake earthquake) {
        earthQuakes.add(0, earthquake);
        aa.notifyDataSetChanged();
    }
}