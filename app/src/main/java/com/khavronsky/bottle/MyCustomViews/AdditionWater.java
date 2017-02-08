package com.khavronsky.bottle.MyCustomViews;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.khavronsky.bottle.Adapters.AdapterToWaterPicsOnViewPagerFragment;
import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.R;

import java.util.ArrayList;
import java.util.List;

public class AdditionWater extends CardView implements View.OnClickListener {
    private ImageButton plusButton;
    private ImageButton minusButton;
    private CirclesSlideIndicator slideIndicator;
    private TextView titleCapacity;
    private TextView valueOfCapacity;
    ButtonListener buttonListener;

    private int currentCapacityID;
    private int currentScreen;
    private List<ModelOfCapacityType> modelOfCapacityTypeList = new ArrayList<>();
    private AdapterToWaterPicsOnViewPagerFragment adapterToAddWaterFragment;
    private ViewPager viewPager;
    private FragmentManager childFragmentManager;

    public AdditionWater(Context context) {
        super(context);
        init();
    }

    public AdditionWater(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdditionWater(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setModelOfCapacityTypeList(final List<ModelOfCapacityType> modelOfCapacityTypeList) {
        this.modelOfCapacityTypeList = modelOfCapacityTypeList;
        firstSetView();
    }

    public void setFragmentManager(FragmentManager childFragmentManager) {
        if (viewPager != null) {
            this.childFragmentManager = childFragmentManager;
        }
        setAdapter();
    }

    private void setAdapter() {
        adapterToAddWaterFragment = new AdapterToWaterPicsOnViewPagerFragment(childFragmentManager, modelOfCapacityTypeList);
        viewPager.setAdapter(adapterToAddWaterFragment);
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
        slideIndicator.setCountOfCircle(modelOfCapacityTypeList.size());
        currentCapacityID = modelOfCapacityTypeList.get(0).getId();
        if (childFragmentManager != null) {
            setAdapter();
        }
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        setViewParamFromList(modelOfCapacityTypeList.get(currentScreen));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentScreen = position;
                currentCapacityID = modelOfCapacityTypeList.get(position).getId();
                setViewParamFromList(modelOfCapacityTypeList.get(position));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setViewParamFromList(ModelOfCapacityType modelOfCapacityType) {
        titleCapacity.setText(modelOfCapacityType.getTitle());
        valueOfCapacity.setText(String.valueOf(modelOfCapacityType.getCapacityStep()));
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

    public interface ButtonListener {
        void buttonPlusOrMinusPressed(int dataModelID, boolean plusOrMinusPressed);
    }

    public void setOnButtonPlusMinusListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
}