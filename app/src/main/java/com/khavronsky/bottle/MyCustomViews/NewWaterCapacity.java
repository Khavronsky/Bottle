package com.khavronsky.bottle.MyCustomViews;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.khavronsky.bottle.Adapters.AdapterToWaterPicsOnViewPagerFragment;
import com.khavronsky.bottle.Data.DataModelToAddWaterView;
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
    private List<DataModelToAddWaterView> dataModelToAddWaterViewList = new ArrayList<>();
    AdapterToWaterPicsOnViewPagerFragment adapter;

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

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.new_water_capacity, this);

        title = (TextView) findViewById(R.id.capacity_title);
        buttonDel = (TextView) findViewById(R.id.button_del);
        buttonCancel = (TextView) findViewById(R.id.button_cancel);
        buttonSave = (TextView) findViewById(R.id.button_save);
        inputTitle = (EditText) findViewById(R.id.edit_capacity_title);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
        adapter = new AdapterToWaterPicsOnViewPagerFragment(((FragmentActivity) context).getSupportFragmentManager(), dataModelToAddWaterViewList);
    }

    @Override
    public void onClick(View v) {

    }
}
