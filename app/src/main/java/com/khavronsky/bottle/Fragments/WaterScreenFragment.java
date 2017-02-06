package com.khavronsky.bottle.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khavronsky.bottle.Data.DataOfWaterConsumed;
import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.MyCustomViews.AdditionWater;
import com.khavronsky.bottle.MyCustomViews.MyDateChanger;
import com.khavronsky.bottle.R;
import com.khavronsky.bottle.Presenters.WaterScreenPresenter;

public class WaterScreenFragment extends Fragment implements WaterScreenPresenter.IView {

    private final static String TAG = "MyLog WaterScreenFragment";
    int currentDate;
    AdditionWater additionWater;
    MyDateChanger dateChanger;
    WaterScreenPresenter presenter;
    TextView showConsumedWater;

   public static android.support.v4.app.FragmentManager getChildFragmentManager = null; //TODO move to AdditionWater


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.water_screen_fragment, container, false);
        getChildFragmentManager = getChildFragmentManager(); //TODO move to AdditionWater
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setReference(this);
        presenter.getDate(currentDate);
    }

    @Override
    public void show(DataOfWaterConsumed waterConsumed) {
        showConsumedWater.setText(formatDataToShow(waterConsumed));
        showConsumedWater.invalidate();
    }

    private String formatDataToShow(DataOfWaterConsumed waterConsumed) {
        int amount = waterConsumed.getAmountOfWaterConsumed();
        int target = waterConsumed.getWaterTarget();

        int amount1 = amount / 1000;
        int amount2 = (amount % 1000) / 100;
        int amount3 = amount % 100 / 10;

        int target1 = target / 1000;
        int target2 = (target % 1000) / 100;
        int target3 = target % 100 / 10;

        String showData = "" + amount1;
        if (amount3 != 0) {
            showData = showData + "," + amount2 + amount3;
        } else {
            if (amount2 != 0) {
                showData = showData + "," + amount2;
            }
        }
        showData = showData + "/" + target1;
        if (target3 != 0) {
            showData = showData + "," + target2 + target3;
        } else {
            if (target2 != 0) {
                showData = showData + "," + target2;
            }
        }
        showData = showData + " л";
        return showData;
    }

    private void init(View view) {
        Log.d(TAG, "init:");
        additionWater = (AdditionWater) view.findViewById(R.id.addition_water);
        dateChanger = (MyDateChanger) view.findViewById(R.id.date_changer);
        currentDate = dateChanger.getCurrentDate();
        presenter = new WaterScreenPresenter();
        showConsumedWater = (TextView) view.findViewById(R.id.consumed_water);
        dateChanger.subscribeToChanges(new MyDateChanger.IDateChanged() {
            @Override
            public void changesHappened() {
                currentDate = dateChanger.getCurrentDate();
                Log.d(TAG, "presenter: " + presenter + " date " + currentDate);
                presenter.getDate(currentDate);
            }
        });

        additionWater.setDataModelToAddWaterViewList(TestingWithFakeData.getDataModelToAddWaterViewList());
        additionWater.setOnButtonPlusMinusListener(new AdditionWater.ButtonListener() {
            @Override
            public void buttonPlusOrMinusPressed(int dataModelID, int capacity) {
                Log.d(TAG, "buttonPlusOrMinusPressed: cap" + capacity);
                presenter.addConsumedWater(currentDate, capacity);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clearReference();
    }
}
