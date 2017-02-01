package com.khavronsky.bottle;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class WaterScreenPresenter {

    private final static String TAG = "MyLog WaterScreenPresenter";

    private List<FakeDate> dateList = new ArrayList<>();
    private IView view;

    WaterScreenPresenter(IView view) {
        this.view = view;
        Log.d(TAG, "WaterScreenPresenter: constructor");
    }

    FakeDate getDate(int date) {
        Log.d(TAG, "getDate: ");
        dateList = TestingWithFakeData.getFakeDateList();
        FakeDate fakeDate = dateList.get(date);
        view.show();
        return fakeDate;
    }

    interface IView {
        void show();
    }
}
