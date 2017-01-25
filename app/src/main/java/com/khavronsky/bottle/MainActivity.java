package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    ImageButton plusButton;
//    ImageButton minusButton;
//    CirclesSlideIndicator slideIndicator;
//    TextView titleCapacity;
//    TextView valueOfCapacity;
//    ButtonListener buttonListener;

    //    int currentCapacityID;
//    int currentScreen;
//    int currentCapacityValueOnScreen;
//    List<DataModel> dataModelList;
//    AdapterToBaseFragment adapterToBaseFragment;
//    private ViewPager viewPager;

    AdditionWater additionWater;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        additionWater = (AdditionWater) findViewById(R.id.addition_water);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ХУЯК", Toast.LENGTH_SHORT).show();
            }
        });


//        ClassForLightTesting testing = new ClassForLightTesting(this);
//        dataModelList = testing.getList();
//
//        init();
//        setViewParamFromList(dataModelList.get(currentScreen));
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentScreen = position;
//                currentCapacityID = dataModelList.get(position).getId();
//                currentCapacityValueOnScreen = dataModelList.get(position).getCapacityStep();
//                setViewParamFromList(dataModelList.get(position));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

//    private void init() {
//        plusButton = (ImageButton) findViewById(R.id.button_plus);
//        minusButton = (ImageButton) findViewById(R.id.button_minus);
//        titleCapacity = (TextView) findViewById(R.id.tv_title_of_capacity);
//        valueOfCapacity = (TextView) findViewById(R.id.tv_value_of_capacity);
//        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
//        slideIndicator.setCountOfCircle(dataModelList.size());
//        currentCapacityID = dataModelList.get(0).getId();
//        currentCapacityValueOnScreen = dataModelList.get(0).getCapacityStep();
//        viewPager = (ViewPager) findViewById(R.id.my_pager);
//        adapterToBaseFragment = new AdapterToBaseFragment(getSupportFragmentManager(), dataModelList);
//        viewPager.setAdapter(adapterToBaseFragment);
//        plusButton.setOnClickListener(this);
//        minusButton.setOnClickListener(this);
//
//    }
//
//    private void setViewParamFromList(DataModel dataModel) {
//
//        titleCapacity.setText(dataModel.getTitle());
//        valueOfCapacity.setText(String.valueOf(dataModel.getCapacityStep()));
//        slideIndicator.setFocusedCircle(currentScreen);
//        slideIndicator.invalidate();
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id){
//            case R.id.button_plus:
//                Toast.makeText(this, "PLUS PRESSED", Toast.LENGTH_SHORT).show();
//                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, true);
//                break;
//            case R.id.button_minus:
//                Toast.makeText(this, "MINUS PRESSED", Toast.LENGTH_SHORT).show();
//                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, false);
//                break;
//        }
//    }
//
//    interface ButtonListener {
//        void buttonPlusOrMinusPressed(int dataModelID, boolean buttonPressed);
//    }
//    void setOnButtonPlusMinusListener(ButtonListener buttonListener){
//        this.buttonListener = buttonListener;
//    }
}
