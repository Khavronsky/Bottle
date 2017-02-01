package com.khavronsky.bottle;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdditionWater extends CardView implements View.OnClickListener {
    private final Context context;
    private ImageButton plusButton;
    private ImageButton minusButton;
    private CirclesSlideIndicator slideIndicator;
    private TextView titleCapacity;
    private TextView valueOfCapacity;
    ButtonListener buttonListener;

    private int currentCapacityID;
    private int currentScreen;
    private List<DataModelToAddWaterView> dataModelToAddWaterViewList = new ArrayList<>();
    private AdapterToAddWaterFragment adapterToAddWaterFragment;
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

    public void setDataModelToAddWaterViewList(final List<DataModelToAddWaterView> dataModelToAddWaterViewList) {
        this.dataModelToAddWaterViewList = dataModelToAddWaterViewList;
        firstSetView();

    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.addition_water, this);

        plusButton = (ImageButton) findViewById(R.id.button_plus);
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        titleCapacity = (TextView) findViewById(R.id.tv_title_of_capacity);
        valueOfCapacity = (TextView) findViewById(R.id.tv_value_of_capacity);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
    }

    private void firstSetView() {
        slideIndicator.setCountOfCircle(dataModelToAddWaterViewList.size());
        currentCapacityID = dataModelToAddWaterViewList.get(0).getId();
        adapterToAddWaterFragment = new AdapterToAddWaterFragment(((FragmentActivity) context).getSupportFragmentManager(), dataModelToAddWaterViewList);
        viewPager.setAdapter(adapterToAddWaterFragment);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        setViewParamFromList(dataModelToAddWaterViewList.get(currentScreen));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentScreen = position;
                currentCapacityID = dataModelToAddWaterViewList.get(position).getId();
                setViewParamFromList(dataModelToAddWaterViewList.get(position));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setViewParamFromList(DataModelToAddWaterView dataModelToAddWaterView) {
        titleCapacity.setText(dataModelToAddWaterView.getTitle());
        valueOfCapacity.setText(String.valueOf(dataModelToAddWaterView.getCapacityStep()));
        slideIndicator.setFocusedCircle(currentScreen);
        slideIndicator.invalidate();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_plus:
                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, true);
                break;
            case R.id.button_minus:
                buttonListener.buttonPlusOrMinusPressed(currentCapacityID, false);
                break;
        }
    }

    interface ButtonListener {
        void buttonPlusOrMinusPressed(int dataModelID, boolean whichButtonPressed);
    }

    void setOnButtonPlusMinusListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
}