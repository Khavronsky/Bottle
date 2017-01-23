package com.khavronsky.bottle;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdditionWater extends View implements View.OnClickListener{
    private final Context context;
    ImageButton plusButton;
    ImageButton minusButton;
    CirclesSlideIndicator slideIndicator;
    TextView titleCapacity;
    TextView valueOfCapacity;
    ButtonListener buttonListener;

    int currentCapacityID;
    int currentScreen;
    int currentCapacityValueOnScreen;
    List<DataModel> dataModelList;
    AdapterToBaseFragment adapterToBaseFragment;
    private ViewPager viewPager;

    public AdditionWater(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public AdditionWater(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AdditionWater(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        myWidth = getWidth();
//        myHeight = getHeight();
    }

    private void init() {
        ClassForLightTesting testing = new ClassForLightTesting(this);
        dataModelList = testing.getList();
        plusButton = (ImageButton) findViewById(R.id.button_plus);
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        titleCapacity = (TextView) findViewById(R.id.tv_title_of_capacity);
        valueOfCapacity = (TextView) findViewById(R.id.tv_value_of_capacity);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
//        slideIndicator.setCountOfCircle(/*dataModelList.size()*/ 3);
        currentCapacityID = dataModelList.get(0).getId();
        currentCapacityValueOnScreen = dataModelList.get(0).getCapacityStep();
        viewPager = (ViewPager) findViewById(R.id.my_pager);
        adapterToBaseFragment = new AdapterToBaseFragment(((AppCompatActivity)context).getSupportFragmentManager(), dataModelList);
//        viewPager.setAdapter(adapterToBaseFragment);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        setViewParamFromList(dataModelList.get(currentScreen));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentScreen = position;
                currentCapacityID = dataModelList.get(position).getId();
                currentCapacityValueOnScreen = dataModelList.get(position).getCapacityStep();
                setViewParamFromList(dataModelList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setViewParamFromList(DataModel dataModel) {

        titleCapacity.setText(dataModel.getTitle());
        valueOfCapacity.setText(String.valueOf(dataModel.getCapacityStep()));
        slideIndicator.setFocusedCircle(currentScreen);
        slideIndicator.invalidate();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button_plus:
                Toast.makeText(context, "PLUS PRESSED", Toast.LENGTH_SHORT).show();
                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, true);
                break;
            case R.id.button_minus:
                Toast.makeText(context, "MINUS PRESSED", Toast.LENGTH_SHORT).show();
                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, false);
                break;
        }
    }

    interface ButtonListener {
        void buttonPlusOrMinusPressed(int dataModelID, boolean buttonPressed);
    }
    void setOnButtonPlusMinusListener(ButtonListener buttonListener){
        this.buttonListener = buttonListener;
    }
}
