package com.jaioneirizar.calculadora;



        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;

        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.jaioneirizar.calculadora.CalcLogic;


public class MainActivity extends ActionBarActivity {


    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private Button btnDot;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnClear;
    private Button btnEqual;
    private TextView lblResult;
    private String operator;
    private String number2;
    private Boolean limpiar;


    CalcLogic calc = new CalcLogic();


    private View.OnClickListener numbersEvent = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(limpiar) {
                lblResult.setText("");
                limpiar = false;
            }
            Button b = (Button) v;
            lblResult.setText(lblResult.getText() + b.getText().toString());
        }
    };
    private View.OnClickListener operatorsEvent = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            operator = b.getText().toString();
            calc.setTotal(Double.parseDouble(lblResult.getText().toString()));

            limpiar = true;



        }
    };
    private View.OnClickListener equalEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number2 = lblResult.getText().toString();
            switch (operator) {
                case "+":
                    calc.add(number2);
                    break;
                case "-":
                    calc.subtract(number2);
                    break;
                case "X":
                    calc.multiply(number2);
                    break;
                case "/":
                    calc.divide(number2);
                    break;

            }
            lblResult.setText(calc.getTotalString());

        
        }
    };
    private View.OnClickListener clearEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calc.setTotal(0);
            lblResult.setText(calc.getTotalString());
            limpiar = true;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnZero = (Button) findViewById(R.id.btnZero);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setText("");

        lblResult.setText(calc.getTotalString());

        limpiar = true;

        this.addEventListeners();

        Log.d("CHANGE", "Calculadora en onCreate: " + calc.getTotalString());
    }

    private void addEventListeners(){

        btnOne.setOnClickListener(numbersEvent);
        btnTwo.setOnClickListener(numbersEvent);
        btnThree.setOnClickListener(numbersEvent);
        btnFour.setOnClickListener(numbersEvent);
        btnFive.setOnClickListener(numbersEvent);
        btnSix.setOnClickListener(numbersEvent);
        btnSeven.setOnClickListener(numbersEvent);
        btnEight.setOnClickListener(numbersEvent);
        btnNine.setOnClickListener(numbersEvent);
        btnZero.setOnClickListener(numbersEvent);
        btnDot.setOnClickListener(numbersEvent);

        btnPlus.setOnClickListener(operatorsEvent);
        btnMinus.setOnClickListener(operatorsEvent);
        btnMultiply.setOnClickListener(operatorsEvent);
        btnDivide.setOnClickListener(operatorsEvent);
        btnEqual.setOnClickListener(equalEvent);

        btnClear.setOnClickListener(clearEvent);
    }


}