package ru.itis.calculatorv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[] numButtons;
    private Button clear;
    private Button add;
    private Button mul;
    private Button sub;
    private Button div;
    private Button skbL;
    private Button skbR;
    private Button point;
    private Button equals;
    private String text = "";
    private String result = "";
    private TextView txtMain;
    private TextView txtResult;
    private MyCalculator calculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new MyCalculator();
        txtResult = (TextView) findViewById(R.id.txt_result);
        numButtons = new Button[10];
        numButtons[0] = (Button) findViewById(R.id.btn_0);
        numButtons[1] = (Button) findViewById(R.id.btn_1);
        numButtons[2] = (Button) findViewById(R.id.btn_2);
        numButtons[3] = (Button) findViewById(R.id.btn_3);
        numButtons[4] = (Button) findViewById(R.id.btn_4);
        numButtons[5] = (Button) findViewById(R.id.btn_5);
        numButtons[6] = (Button) findViewById(R.id.btn_6);
        numButtons[7] = (Button) findViewById(R.id.btn_7);
        numButtons[8] = (Button) findViewById(R.id.btn_8);
        numButtons[9] = (Button) findViewById(R.id.btn_9);
        clear = (Button) findViewById(R.id.btn_clear);
        add = (Button) findViewById(R.id.btn_add);
        mul = (Button) findViewById(R.id.btn_mul);
        sub = (Button) findViewById(R.id.btn_sub);
        div = (Button) findViewById(R.id.btn_div);
        skbL = (Button) findViewById(R.id.btn_skb_l);
        skbR = (Button) findViewById(R.id.btn_skb_r);
        point = (Button) findViewById(R.id.btn_point);
        equals = (Button) findViewById(R.id.btn_equals);

        txtMain = (TextView) findViewById(R.id.txt_main);

        numButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="0";
                txtMain.setText(text);
            }
        });
        numButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="1";
                txtMain.setText(text);
            }
        });
        numButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="2";
                txtMain.setText(text);
            }
        });
        numButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="3";
                txtMain.setText(text);
            }
        });
        numButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="4";
                txtMain.setText(text);
            }
        });
        numButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="5";
                txtMain.setText(text);
            }
        });
        numButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="6";
                txtMain.setText(text);
            }
        });
        numButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="7";
                txtMain.setText(text);
            }
        });
        numButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="8";
                txtMain.setText(text);
            }
        });
        numButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="9";
                txtMain.setText(text);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.length()>0){
                    text = text.substring(0, text.length()-1);
                    txtMain.setText(text);
                }
            }
        });
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                text = "";
                txtMain.setText("");
                return true;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAndAdd('+');
                txtMain.setText(text);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAndAdd('*');
                txtMain.setText(text);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //−
                if(text.length()>0){
                    if(text.charAt(text.length()-1) == '('){
                        text+= "-";
                    } else {
                        testAndAdd((char) 8722);
                    }
                } else {
                    text += "-";
                }
                txtMain.setText(text);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAndAdd('/');
                txtMain.setText(text);
            }
        });
        skbL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+="(";
                txtMain.setText(text);
            }
        });
        skbR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text+=")";
                txtMain.setText(text);
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAndAdd('.');
                txtMain.setText(text);
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double res = calculator.getResult(txtMain.getText().toString());
                    result = Double.toString(res);
                    if (result.endsWith(".0")) {
                        result = result.substring(0, result.length() - 2);
                    }
                    txtResult.setText(result);

                } catch (Exception e){
                    txtResult.setText("E");
                }
            }
        });
    }

    private void testAndAdd(char c){
        int l = text.length();
        if(l > 0){
            char temp = text.charAt(l - 1);
            if((temp >= '0' && temp <= '9') || temp == '(' || temp == ')'){
                text += c;
            } else {
                text = text.substring(0, l-1) + c;
            }
        } else {
            text += c;
        }
    }
}