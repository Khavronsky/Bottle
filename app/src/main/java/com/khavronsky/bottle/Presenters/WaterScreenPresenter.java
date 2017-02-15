package com.khavronsky.bottle.Presenters;


import com.khavronsky.bottle.Data.DataOfWaterConsumed;
import com.khavronsky.bottle.Data.TestingWithFakeData;

public class WaterScreenPresenter extends AbstractPresenter<WaterScreenPresenter.IView> {

    public void refreshData(int date) {
        if (getMyObj() != null){
            getMyObj().show(TestingWithFakeData.getDataForWaterScreen().getWaterConsumed(date));
        }
    }

    public void addConsumedWater(int currentDate, int dataModelID, boolean addOrSubtract){
        TestingWithFakeData.addWaterConsumed(currentDate, dataModelID, addOrSubtract);
        refreshData(currentDate);
    }

    public interface IView {
        void show(DataOfWaterConsumed dataOfWaterConsumed);
    }
}