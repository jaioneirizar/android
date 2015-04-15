package com.jaioneirizar.earthquakes.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;

public class EarthQuakesProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse("content://com.jaioneirizar.provider.earthquakes/earthquakes");

    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;
    private static final UriMatcher uriMatcher;
    private EarthQuakeOpenHelper earthQuakeHelper;
    //private SQLiteDatabase db;


    public static class Columns implements BaseColumns {

        public static final String KEY_ID = "_id";
        public static final String KEY_PLACE = "place";
        public static final String KEY_MAGNITUDE = "magnitude";
        public static final String KEY_LAT = "lat";
        public static final String KEY_LONG = "Long";
        public static final String KEY_URL = "url";
        public static final String KEY_DEPTH = "depth";
        public static final String KEY_TIME = "time";

    }

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.jaioneirizar.provider.earthquakes", "earthquakes", ALLROWS);
        uriMatcher.addURI("com.jaioneirizar.provider.earthquakes", "earthquakes/#", SINGLE_ROW);

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        earthQuakeHelper = new EarthQuakeOpenHelper(getContext(), EarthQuakeOpenHelper.DATABASE_NAME, null, EarthQuakeOpenHelper.DATABASE_VERSION);
        return false;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        //	Return	a	string	that	identifies	the	MIME	type
        //	for	a	Content	Provider	URI
        switch (uriMatcher.match(uri)) {

            case ALLROWS:
                return "vnd.android.cursor.dir/vnd.jaioneirizar.provider.earthquakes";
            case SINGLE_ROW:

                return "vnd.android.cursor.item/vnd.jaioneirizar.provider.earthquakes";
            default:
                throw new IllegalArgumentException("Unsupported	URI:	" + uri);

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
         SQLiteDatabase db;
        db = earthQuakeHelper.getWritableDatabase();
        long id = db.insert(EarthQuakeOpenHelper.DATABASE_TABLE, null, values);

        if (id > -1) {
            Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, id);

            //NECESARIO SI NO NO FUNCIONA
            getContext().getContentResolver().notifyChange(insertedId, null);
            return insertedId;

        } else {
            return null;
        }

    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

         SQLiteDatabase db;
        try {
            db = earthQuakeHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = earthQuakeHelper.getReadableDatabase();
        }
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        //	If	this	is	a	row	query,	limit	the	result	set	to	the	passed	in	row.

        switch (uriMatcher.match(uri)) {

            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(Columns._ID + "=?");
                selectionArgs = new String[]{rowID};
            default:
                break;

        }
        queryBuilder.setTables(EarthQuakeOpenHelper.DATABASE_TABLE);
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class EarthQuakeOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "earthQuakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION = 1;


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


    }
}
