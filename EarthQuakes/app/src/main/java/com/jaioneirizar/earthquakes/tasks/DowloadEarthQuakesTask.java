package com.jaioneirizar.earthquakes.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.model.Coordinate;
import com.jaioneirizar.earthquakes.model.EarthQuake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cursomovil on 25/03/15.
 */
public class DowloadEarthQuakesTask extends AsyncTask<String, EarthQuake,Integer> {

    public interface AddEarthQuakeInterface{
        public void addEarthQuake(EarthQuake earthquake);
    }

    private AddEarthQuakeInterface target;

    public DowloadEarthQuakesTask(AddEarthQuakeInterface target){
        this.target= target;
    }
    @Override
    protected Integer doInBackground(String... urls) {
        if(urls.length > 0) {
            updatedEarthQuake(urls[0]);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(EarthQuake... earthQuakes) {
        super.onProgressUpdate(earthQuakes);
        target.addEarthQuake(earthQuakes[0]);
    }

    private void updatedEarthQuake(String earthquakesFeed) {

       JSONObject json;


       //earthquakesFeed = new URL(earthquakesFeed);
       // String earthquakesFeed= getString(R.string.earthquakesurl);

        try{

            URL url = new URL(earthquakesFeed);
            //	Create	a	new	HTTP	URL	connection
            URLConnection connection	=	url.openConnection();
            HttpURLConnection httpConnection	=	(HttpURLConnection)connection;
            int	responseCode	=	httpConnection.getResponseCode();
            if	(responseCode	==	HttpURLConnection.HTTP_OK)	{


                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(
                                httpConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                json = new JSONObject(responseStrBuilder.toString());
                JSONArray earthquakes = json.getJSONArray("features");

                for (int i = earthquakes.length()-1; i >= 0; i--) {
                    processEarthQuakeTask(earthquakes.getJSONObject(i));
                }


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void processEarthQuakeTask(JSONObject jsonObject) {

        try {
            String id = jsonObject.getString("id");
            JSONArray jSoncoords =jsonObject.getJSONObject("geometry").getJSONArray("coordinates");
            Coordinate coords = new Coordinate(jSoncoords.getDouble(0), jSoncoords.getDouble(1),jSoncoords.getDouble(2));
            JSONObject properties = jsonObject.getJSONObject("properties");

            EarthQuake earthQuake= new EarthQuake();
            //earthQuake.set_id(properties.getString("id"));
            earthQuake.set_id(id);
            earthQuake.setPlace(properties.getString("place"));
            earthQuake.setMagnitude(properties.getDouble("mag"));
            earthQuake.setTime(properties.getInt("time"));
            earthQuake.setUrl(properties.getString("url"));
            earthQuake.setCoords(coords);

            Log.d("EQ", earthQuake.toString());

           /*Necesario para conectarse con la vista, llama a la funcion on ProgressUpdate, para esto es necesario la
           * interface*/
           publishProgress(earthQuake);
            //earthQuakes.add(0,earthQuake);
            //aa.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
