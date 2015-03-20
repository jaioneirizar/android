package com.jaioneirizar.calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private CalcLogic calc = new CalcLogic();


    private TextView result;
    private Double rs=0.0;
    private boolean clear = true;


    private Button btnzero;
    private Button btnone;
    private Button btntwo;
    private Button btnthree;
    private Button btnfour;
    private Button btnfive;
    private Button btnsix;
    private Button btnseven;
    private Button btneight;
    private Button btnnine;


   // private TextView pantalla;
    private Button btnadd;
    private Button btndiv;
    private Button btnmult;
    private Button btnclear;
    private Button btnrest;
    private Button btnequal;

    private View.OnClickListener  numberOnclickListener=  new View.OnClickListener (){

        @Override
    public  void onClick(View v){

            setNumber(((Button)v).getText().toString());

        }
    };


    private View.OnClickListener  operationOnclickListener=  new View.OnClickListener (){

        @Override
        public  void onClick(View v){

            setOperation(((Button)v).getText().toString());

        }
    };

    private String numbersDis= null;
    private String op= null;
    private String operando= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      btnzero = (Button)findViewById(R.id.btnzero);
      btnzero.setOnClickListener(numberOnclickListener);
      btnone = (Button)findViewById(R.id.btnone);
      btnone.setOnClickListener(this);
      btntwo = (Button)findViewById(R.id.btntwo);
      btntwo.setOnClickListener(this);
      btnthree = (Button)findViewById(R.id.btnthree);
      btnthree.setOnClickListener(this);
      btnfour = (Button)findViewById(R.id.btnfour);
      btnfour.setOnClickListener(this);
      btnfive = (Button)findViewById(R.id.btnfive);
      btnfive.setOnClickListener(this);
      btnsix = (Button)findViewById(R.id.btnsix);
      btnsix.setOnClickListener(this);
      btnseven = (Button)findViewById(R.id.btnseven);
      btnseven.setOnClickListener(this);
      btneight = (Button)findViewById(R.id.btneight);
      btneight.setOnClickListener(this);
      btnnine = (Button)findViewById(R.id.btnnine);
      btnnine.setOnClickListener(this);
      result = (TextView)findViewById(R.id.fieldResultado);

      //operaciones

      btnadd = (Button)findViewById(R.id.btnadd);
      btnadd.setOnClickListener(operationOnclickListener);
      btndiv = (Button)findViewById(R.id.btndiv);
      btndiv.setOnClickListener(this);
      btnmult = (Button)findViewById(R.id.btnmult);
      btnmult.setOnClickListener(this);
      btnclear = (Button)findViewById(R.id.btnclear);
      btnclear.setOnClickListener(this);
      btnrest = (Button)findViewById(R.id.btnrest);
      btnrest.setOnClickListener(this);
      btnequal = (Button)findViewById(R.id.btnequal);
      btnequal.setOnClickListener(this);



        TextView result = (TextView)findViewById(R.id.fieldResultado);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

       // updateField();
    }



  /*  @Override
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
    }*/






  /* public void setNumber(String operando) {
       // Button button = (Button)v;

       // String n = button.getText().toString();

        if(clear) {
            numbers.setLength(0);
            clear = false;
        }

        numbers.append(operando);

        updateField();
    }*/


    private void updateField() {
        result.setText(this.numbersDis);
    }


    private void calculate() {



        if (op == "+") {
            rs = calc.add(numbersDis);
            Log.d("CALC", rs.toString());
        } else if (op == "-") {
          rs = calc.subtract(numbersDis);
           // rs= calc.subtract(operando);
          //  rs = calc.subtract(numbers);
        } else if (op == "*") {
           // rs = calc.multiply(rs, Double.valueOf(numbers.toString()));
          rs = calc.multiply(numbersDis);

            rs= calc.multiply(operando);

        } else if (op == "/") {
            //String resultDev = calc.divide(rs, Double.valueOf(numbers.toString()));
          //  rs = Double.valueOf(resultDev);
            rs = calc.divide(numbersDis);
           // rs= calc.divide(operando);

        } else {
            rs = Double.valueOf(numbersDis);
            //rs= calc.multiply(operando);
        }



       // numbers.setLength(0);
       // numbersDis.concat(rs);

        Log.d("CALC","test" + numbersDis);

        updateField();
    }





}
