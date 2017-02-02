package com.khavronsky.bottle;


import android.util.Log;

class WaterScreenPresenter {

    private final static String TAG = "MyLog WaterScreenPresenter";

    private DataOfWaterConsumed waterConsumed;
    private IView view;

    WaterScreenPresenter(IView view) {
        this.view = view;
        Log.d(TAG, "WaterScreenPresenter: constructor");
    }

    void getDate(int date) {
        Log.d(TAG, "getDate: ");
        waterConsumed = TestingWithFakeData.getDataOfWaterConsumed(date);
        Log.d(TAG, "waterConsumed" + waterConsumed.getAmountOfWaterConsumed());
        view.show(waterConsumed);
    }

    interface IView {
        void show(DataOfWaterConsumed dataOfWaterConsumed);
    }
    void addConsumedWater(int date, int capacity){
        TestingWithFakeData.addWaterConsumed(date, capacity);
        getDate(date);
    }
}
