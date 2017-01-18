package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton plusButton;
    ImageButton minusButton;
    CirclesSlideIndicator slideIndicator;
    TextView titleCapacity;
    ChangeCapacityCallback capacityCallback;
    int currentCapacityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



    }

    private void init() {
        plusButton = (ImageButton) findViewById(R.id.button_plus);
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        titleCapacity = (TextView) findViewById(R.id.tv_title_of_capacity);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
    }

    interface ChangeCapacityCallback{
        void buttonPlusOrMinusPressed (int dataModelID, boolean buttonPressed);
    }
    void subscribeCCCallback (ChangeCapacityCallback callback){
        this.capacityCallback = callback;
    }
}
