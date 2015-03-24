package com.jaioneirizar.dualactivitycamera;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class lblActivity extends ActionBarActivity {

    private Button btnsend;
    private EditText editTextView;
    private TextView lblText;
    private Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbl);

        obtainViews();
        Bundle reicieveParams = getIntent().getExtras();
        lblText.setText(reicieveParams.getString(MainActivity.Message));
        addEventListener();





    }

    private void obtainViews() {

        btnsend= (Button) findViewById(R.id.btnsend);
        editTextView=(EditText) findViewById(R.id.editTextView);
        lblText = (TextView) findViewById(R.id.lblText);
        btnclose = (Button) findViewById(R.id.btnclose);





    }


    private void addEventListener (){

        btnsend.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){


                String message = editTextView.getText().toString();
                if(message.length()>0) {

                Intent	result	=	new	Intent();
                result.putExtra(MainActivity.Message, editTextView.getText().toString());

                setResult(RESULT_OK, result);

                finish();


            }else {
                    Toast toast = Toast.makeText(lblActivity.this,getResources().getString(R.string.no_text),Toast.LENGTH_SHORT);
                    toast.show();}
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){

                setResult(RESULT_CANCELED);

                finish();


            }
        });


    }






}
