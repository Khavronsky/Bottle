package com.khavronsky.bottle.MyCustomViews;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.khavronsky.bottle.Adapters.AdapterToWaterPicsOnViewPagerFragment;
import com.khavronsky.bottle.Data.DataForWaterScreen;
import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.R;

import java.util.ArrayList;
import java.util.List;

public class NewWaterCapacity extends CardView implements View.OnClickListener {
    private final Context context;
    TextView title;
    TextView buttonDel;
    TextView buttonCancel;
    TextView buttonSave;
    EditText inputTitle;
    ViewPager viewPager;
    NumberPicker numberPicker;
    CirclesSlideIndicator slideIndicator;
    private int currentCapacityID;
    FragmentManager childFragmentManager;

    DataForWaterScreen data;

    private List<ModelOfCapacityType> modelOfCapacityTypeList = new ArrayList<>();
    AdapterToWaterPicsOnViewPagerFragment adapter;
    private int currentScreen;

    public NewWaterCapacity(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NewWaterCapacity(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public NewWaterCapacity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void setData(DataForWaterScreen data) {
        this.data = data;
        this.modelOfCapacityTypeList = data.getModelOfCapacityTypes();
        firstSetView();
    }

    public void setChildFragmentManager(FragmentManager childFragmentManager){
        if(viewPager != null){
            this.childFragmentManager = childFragmentManager;
        }
        setAdapter();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.new_water_capacity, this);

        title = (TextView) findViewById(R.id.capacity_title);
        buttonDel = (TextView) findViewById(R.id.button_del);
        buttonCancel = (TextView) findViewById(R.id.button_cancel);
        buttonSave = (TextView) findViewById(R.id.button_save);
        inputTitle = (EditText) findViewById(R.id.edit_capacity_title);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);

    }

    private void firstSetView(){
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        slideIndicator.setCountOfCircle(modelOfCapacityTypeList.size());
        slideIndicator.setFocusedCircle(currentScreen);
        slideIndicator.invalidate();
        currentCapacityID = modelOfCapacityTypeList.get(0).getId();
        if (childFragmentManager != null){
            setAdapter();
        }
        setPicker();
        setListenerToViewPager();
    }

    private void setListenerToViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentScreen = position;
                Log.d("MyX", "NewWaterCapacity pos" + position);
                currentCapacityID = modelOfCapacityTypeList.get(position).getId();
                slideIndicator.setFocusedCircle(currentScreen);
                slideIndicator.invalidate();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setPicker() {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(40);
        numberPicker.setDisplayedValues(displayedValuesFormat());
        numberPicker.setWrapSelectorWheel(false);
    }

    @NonNull
    private String[] displayedValuesFormat() {
        String[] capacityValues = new String[41];
        for (int i = 0; i < capacityValues.length; i++) {
            int x = 20 + i;
            String number = Integer.toString(x*50);
            capacityValues[i] = number;
        }
        return capacityValues;
    }

    private void setAdapter() {
        adapter = new AdapterToWaterPicsOnViewPagerFragment(childFragmentManager, modelOfCapacityTypeList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button_cancel:
                (Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT)).show();
                break;
            case R.id.button_save:
                (Toast.makeText(getContext(), "save", Toast.LENGTH_SHORT)).show();
                break;
            case R.id.button_del:
                (Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT)).show();
                break;
        }
    }
}
