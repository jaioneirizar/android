package com.jaioneirizar.dualactivitycamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {


   private Button btnsend;
   private Button btncamera;
   private EditText lblText;
   private TextView viewlbl;
   private ImageView imageView;
    private	static	final	int	messageActivity	=	1;
    private	static	final	int	cameraActivity	=	2;
    private Uri fileUri;
    public static final String Message= "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtainViews();
        addEventListener();
    }

    private void obtainViews() {

        btnsend= (Button) findViewById(R.id.btnsend);
        btncamera= (Button) findViewById(R.id.btnCamera);
        lblText = (EditText) findViewById(R.id.lblText);
        viewlbl = (TextView) findViewById(R.id.viewlbl);
        imageView =(ImageView) findViewById(R.id.imageView);



    }

   private void addEventListener() {

        btnsend.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){


                String message = lblText.getText().toString();

                if(message.length()>0) {

                    Intent Activityintent = new Intent(MainActivity.this, lblActivity.class);


                    Activityintent.putExtra(Message, lblText.getText().toString());
                    startActivityForResult(Activityintent, messageActivity);
                }else{

                    Toast toast = Toast.makeText(MainActivity.this,getResources().getString(R.string.no_text),Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){

                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                // start the image capture Intent
                startActivityForResult(intent, cameraActivity);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_CANCELED) {


            switch (requestCode) {

                case (messageActivity):
                    String message= data.getStringExtra(Message);
                    viewlbl.setText(message);
                    break;


                case (cameraActivity):


                   Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imageBitmap);

                    break;


            }


        }


    }
}
