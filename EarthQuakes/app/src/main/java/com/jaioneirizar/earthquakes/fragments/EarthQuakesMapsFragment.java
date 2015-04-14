package com.jaioneirizar.earthquakes.fragments;



import android.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.abstracts.AbstractMapFragment;
import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.fragments.EarthQuakeListFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
//public class EarthQuakesMapsFragment extends MapFragment implements GoogleMap.OnMapLoadedCallback {

public class EarthQuakesMapsFragment extends AbstractMapFragment {

  private  EarthQuake earthQuake;

   /* private EarthQuake earthQuake;
    private TextView lblid;
    private TextView lblsite;
    private TextView lblurl;
    private GoogleMap mapa =null;
    private MarkerOptions marker;
    private CameraUpdate camUpd;
    private Double Lng;
    private Double Lat;
    private List<EarthQuake> earthQuakes;*/


    @Override
    protected void obtenerData() {



        String id = getActivity().getIntent().getStringExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        earthQuake = db.getById(id);


    }

    @Override
    protected void PintarMapa() {
        MarkerOptions marker = crearMarker(earthQuake);

        mapa.addMarker(marker);

        CameraPosition camPos = new CameraPosition.Builder().target(marker.getPosition())
                .zoom(5)
                .build();

        CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd);
    }


   /*  @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

           View layout= super.onCreateView(inflater, container, savedInstanceState);
        mapa=getMap();
        mapa.setOnMapLoadedCallback(this);
        return layout;

    }


    public void setEarthQuakes(List<EarthQuake> earthquake) {

        this.earthQuakes = earthquake;

    }


    @Override
    public void onMapLoaded() {

            LatLngBounds.Builder builder = new LatLngBounds.Builder();



                    for (int i = 0; i < earthQuakes.size(); i++) {

                        LatLng eartqueakeposition = new LatLng(earthQuakes.get(i).getCoords().getLng(),
                                earthQuakes.get(i).getCoords().getLat());
                        String Place = earthQuakes.get(i).getPlace();
                        String Url = earthQuakes.get(i).getUrl();
                        Double Magnitude = earthQuakes.get(i).getMagnitude();

                        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        MarkerOptions marker = new MarkerOptions().position(eartqueakeposition).title(Place).snippet(String.valueOf(Magnitude));


                        mapa.addMarker(marker);
                        builder.include(marker.getPosition());


                    }

                    LatLngBounds bounds = builder.build();

                    // LatLng position = new LatLng(Lng, Lat);
                    // CameraPosition camPos = new CameraPosition.Builder().target(position)


                    if (earthQuakes.size() == 1) {
                        camUpd = CameraUpdateFactory.newLatLngZoom(new LatLng(earthQuakes.get(0).getCoords().getLat(),
                                earthQuakes.get(0).getCoords().getLng()), 0);
                    } else {
                        camUpd = CameraUpdateFactory.newLatLngBounds(bounds, 0);
                    }

                    mapa.animateCamera(camUpd);

                }*/
            }


