package com.jaioneirizar.todolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

   private ArrayList<String> todos;
    private Button btnAdd;
    private TextView todoText;
    private ListView todoListView;
    private ArrayAdapter <String> aa;
    private final String DATA= "datos";
    private ArrayList data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!= null){
            todos= savedInstanceState.getStringArrayList(DATA);
            Log.d( "CHANGE","TEST 1" + data);

        }

        todos= new ArrayList<String>();
        aa= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todos);


        btnAdd = (Button)findViewById(R.id.btnAdd);
        todoText = (TextView) findViewById(R.id.todoText);
        todoListView = (ListView)findViewById(R.id.todoListView);
        todoListView.setAdapter(aa);
        this.addEventListener();


    }



    protected void onResume(){
        super.onResume();
        aa.notifyDataSetChanged();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(DATA,todos);
        super.onSaveInstanceState(outState);


    }protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        todos.addAll(savedInstanceState.getStringArrayList(DATA));




    }


    private void addEventListener(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todo= todoText.getText().toString();
                todos.add(0,todo);
                aa.notifyDataSetChanged();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
