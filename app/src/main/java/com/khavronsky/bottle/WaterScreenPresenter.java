package com.khavronsky.bottle;


import android.util.Log;

import com.khavronsky.bottle.Data.DataOfWaterConsumed;
import com.khavronsky.bottle.Data.TestingWithFakeData;

class WaterScreenPresenter extends AbstractPresenter<WaterScreenPresenter.IView> {

    private final static String TAG = "MyLog WaterScreenPresenter";

    private DataOfWaterConsumed waterConsumed;


    WaterScreenPresenter() {
        Log.d(TAG, "WaterScreenPresenter: constructor");
    }

    void getDate(int date) {
        Log.d(TAG, "getDate: ");
        waterConsumed = TestingWithFakeData.getDataOfWaterConsumed(date);
        Log.d(TAG, "waterConsumed" + waterConsumed.getAmountOfWaterConsumed());
        if (getMyObj() != null){
            getMyObj().show(waterConsumed);
        }
    }

    void addConsumedWater(int date, int capacity){
        TestingWithFakeData.addWaterConsumed(date, capacity);
        getDate(date);
    }

    interface IView {
        void show(DataOfWaterConsumed dataOfWaterConsumed);
    }
}
