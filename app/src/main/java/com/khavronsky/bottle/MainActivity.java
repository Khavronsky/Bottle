package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AdditionWater additionWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        additionWater = (AdditionWater) findViewById(R.id.addition_water);
        additionWater.setDataModelList(TestingWithFakeData.getList());
        additionWater.setOnButtonPlusMinusListener(new AdditionWater.ButtonListener() {
            @Override
            public void buttonPlusOrMinusPressed(int dataModelID, boolean whichButtonPressed) {
                String toastString = "MINUS PRESSED";
                if (whichButtonPressed){
                    toastString = "PLUS PRESSED";
                }
                Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_SHORT).show();
            }
        });
    }
}