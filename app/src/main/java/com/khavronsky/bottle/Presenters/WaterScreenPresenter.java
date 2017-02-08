package com.khavronsky.bottle.Presenters;


import android.util.Log;

import com.khavronsky.bottle.Data.DataOfWaterConsumed;
import com.khavronsky.bottle.Data.TestingWithFakeData;

public class WaterScreenPresenter extends AbstractPresenter<WaterScreenPresenter.IView> {

    private final static String TAG = "MyLog WaterScreenPresenter";

    private DataOfWaterConsumed waterConsumed;


    public WaterScreenPresenter() {
        Log.d(TAG, "WaterScreenPresenter: constructor");
    }

    public void getDate(int date) {
        Log.d(TAG, "getDate: ");
        waterConsumed = TestingWithFakeData.getDataOfWaterConsumed(date);
        Log.d(TAG, "waterConsumed" + waterConsumed.getAmountOfWaterConsumed());
        if (getMyObj() != null){
            getMyObj().show(waterConsumed);
        }
    }

    public void addConsumedWater(int currentDate, int dataModelID, boolean addOrSubtract){
        TestingWithFakeData.addWaterConsumed(currentDate, dataModelID, addOrSubtract);
        getDate(currentDate);
    }

    public interface IView {
        void show(DataOfWaterConsumed dataOfWaterConsumed);
    }
}
