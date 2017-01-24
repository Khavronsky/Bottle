package com.khavronsky.bottle;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdditionWater extends FrameLayout implements View.OnClickListener {
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

//        init1();
        init2();
    }

    public AdditionWater(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        init1();
        init2();
    }

    public AdditionWater(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        init1();
        init2();
    }
//
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout( changed,  left,  top,  right,  bottom);
    }


    private void init() {

        ClassForLightTesting testing = new ClassForLightTesting(this);
        dataModelList = testing.getList();

        titleCapacity.setText("TEXT");
        slideIndicator.setCountOfCircle(dataModelList.size());
        currentCapacityID = dataModelList.get(0).getId();
        currentCapacityValueOnScreen = dataModelList.get(0).getCapacityStep();
        adapterToBaseFragment = new AdapterToBaseFragment(((FragmentActivity) context).getSupportFragmentManager(), dataModelList);
        viewPager.setAdapter(adapterToBaseFragment);
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

    private void init1() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.addition_water, this);

        plusButton = (ImageButton) findViewById(R.id.button_plus);
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        titleCapacity = (TextView) findViewById(R.id.tv_title_of_capacity);
        valueOfCapacity = (TextView) findViewById(R.id.tv_value_of_capacity);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
    }

    private void init2() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.addition_water, null);
        addView(rootView);

        plusButton = (ImageButton) rootView.findViewById(R.id.button_plus);
        minusButton = (ImageButton) rootView.findViewById(R.id.button_minus);
        titleCapacity = (TextView) rootView.findViewById(R.id.tv_title_of_capacity);
        valueOfCapacity = (TextView) rootView.findViewById(R.id.tv_value_of_capacity);
        slideIndicator = (CirclesSlideIndicator) rootView.findViewById(R.id.circlesSlideIndicator);
        viewPager = (ViewPager) rootView.findViewById(R.id.my_pager);

        init();
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
        switch (id) {
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

    void setOnButtonPlusMinusListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
}
