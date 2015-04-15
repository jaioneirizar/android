package com.jaioneirizar.earthquakes.providers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.jaioneirizar.earthquakes.model.EarthQuake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cursomovil on 27/03/15.
 */
public class EarthQuakeDB {

    private  SQLiteDatabase db;
  //  private EarthQuakeOpenHelper dbhelper;
    private EarthQuakeDB Helper;
    private Context context;

    //columnas de la tabla//

    /*public static final String KEY_ID = "_id";
    public static final String KEY_PLACE = "place";
    public static final String KEY_MAGNITUDE = "magnitude";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LONG = "Long";
    public static final String KEY_URL = "url";
    public static final String KEY_DEPTH = "depth";
    public static final String KEY_TIME = "time";*/

    public static final String[] KEYS_ALL = {EarthQuakesProvider.Columns.KEY_ID,EarthQuakesProvider.Columns.KEY_PLACE,EarthQuakesProvider.Columns.KEY_MAGNITUDE, EarthQuakesProvider.Columns.KEY_LAT, EarthQuakesProvider.Columns.KEY_LONG, EarthQuakesProvider.Columns.KEY_URL,EarthQuakesProvider.Columns.KEY_DEPTH, EarthQuakesProvider.Columns.KEY_TIME };

    public EarthQuakeDB (Context context){

        this.context= context;

        /*this.dbhelper= new EarthQuakeOpenHelper(context, EarthQuakeOpenHelper.DATABASE_NAME, null, EarthQuakeOpenHelper.DATABASE_VERSION);
        this.db = dbhelper.getWritableDatabase();*/
    }


  /*  public List <EarthQuake> getAll(){
        return query(null, null);
    }*/

    public List <EarthQuake> getAllByMagnitude(int magnitude){
       // String orderby= EarthQuakesProvider.Columns.KEY_TIME + "DESC";
        String where = EarthQuakesProvider.Columns.KEY_MAGNITUDE + ">=?";

        String [] whereArgs = {
                String.valueOf(magnitude)
        };
        return query(where, whereArgs);
       /* ContentResolver cr = context.getContentResolver();
        cr.query(EarthQuakesProvider.CONTENT_URI, KEYS_ALL,
                String [] whereArgs = {
                String.valueOf(magnitude)
        };*/





    }


  /*  public EarthQuake getById(String id){
        EarthQuake eq = null;

        String where = KEY_ID + "=?";
        String whereArgs[] = {id};
        List <EarthQuake> q = query(where, whereArgs);
        if (q.size()>0){
            eq =  q.get(0);
        }
        return eq;

    }*/

    private List<EarthQuake> query(String where, String[]  whereArgs) {

        ContentResolver cr = context.getContentResolver();

        List <EarthQuake> earthQuakes = new ArrayList<EarthQuake>();

         String orderBy= EarthQuakesProvider.Columns.KEY_TIME + " DESC";
        Cursor cursor;
        cursor = cr.query(
                EarthQuakesProvider.CONTENT_URI,
                KEYS_ALL,
                where,
                whereArgs,
                orderBy
        );

        HashMap<String, Integer> indexes = new HashMap<String, Integer>();
        for (int i=0; i< KEYS_ALL.length; i++){
            indexes.put(KEYS_ALL[i],cursor.getColumnIndex(KEYS_ALL[i]));
        }
        while (cursor.moveToNext()){

            EarthQuake earthquake=new EarthQuake();
            earthquake.set_id(cursor.getString(indexes.get(EarthQuakesProvider.Columns.KEY_ID)));
            earthquake.setPlace(cursor.getString(indexes.get(EarthQuakesProvider.Columns.KEY_PLACE)));
            earthquake.setMagnitude(cursor.getDouble(indexes.get(EarthQuakesProvider.Columns.KEY_MAGNITUDE)));
            earthquake.getCoords().setLat(cursor.getDouble(indexes.get(EarthQuakesProvider.Columns.KEY_LAT)));
            earthquake.getCoords().setLng(cursor.getDouble(indexes.get(EarthQuakesProvider.Columns.KEY_LONG)));
            earthquake.getCoords().setDepth(cursor.getDouble(indexes.get(EarthQuakesProvider.Columns.KEY_DEPTH)));
            earthquake.setUrl(cursor.getString(indexes.get(EarthQuakesProvider.Columns.KEY_URL)));
            earthquake.setTime(cursor.getLong(indexes.get(EarthQuakesProvider.Columns.KEY_TIME)));


            earthQuakes.add(earthquake);



        }



        return earthQuakes;

    }


    public void createRow(EarthQuake earthquake) {

        ContentValues newValues = new ContentValues();


        newValues.put(EarthQuakesProvider.Columns.KEY_ID, earthquake.get_id());
        newValues.put(EarthQuakesProvider.Columns.KEY_TIME, earthquake.getTime().getTime());
        newValues.put(EarthQuakesProvider.Columns.KEY_PLACE, earthquake.getPlace());
        newValues.put(EarthQuakesProvider.Columns.KEY_LONG, earthquake.getCoords().getLng());
        newValues.put(EarthQuakesProvider.Columns.KEY_LAT, earthquake.getCoords().getLat());
        newValues.put(EarthQuakesProvider.Columns.KEY_URL, earthquake.getUrl());
        newValues.put(EarthQuakesProvider.Columns.KEY_MAGNITUDE, earthquake.getMagnitude());
        newValues.put(EarthQuakesProvider.Columns.KEY_DEPTH, earthquake.getCoords().getDepth());

        ContentResolver cr = context.getContentResolver();
        cr.insert(EarthQuakesProvider.CONTENT_URI,newValues);


        /*try{
            db.insertOrThrow(EarthQuakeOpenHelper.DATABASE_TABLE, null, newValues);
        }catch(SQLiteException ex) {

        }*/

    }




   /* private class EarthQuakeOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME ="earthQuakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION  = 1;


        //private  static  final String DATABASE_CREATE= "CREATE TABLE" + DATABASE_TABLE + "_id PRIMARY KEY, place TEXT, magnitude REAL, lat REAL,long REAL, depth REAL, url TEXT, time INTEGER";
        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_TABLE +
                "(_id  TEXT PRIMARY KEY, place TEXT, magnitude REAL, lat REAL , Long REAL, url TEXT,depth REAL, time INTEGER)";


        public EarthQuakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }






    }*/
}
