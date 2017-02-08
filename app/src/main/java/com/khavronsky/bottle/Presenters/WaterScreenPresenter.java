package com.khavronsky.bottle.Presenters;


import com.khavronsky.bottle.Data.DataOfWaterConsumed;
import com.khavronsky.bottle.Data.TestingWithFakeData;

public class WaterScreenPresenter extends AbstractPresenter<WaterScreenPresenter.IView> {

    public void getDate(int date) {
        if (getMyObj() != null){
            getMyObj().show(TestingWithFakeData.getDataOfWaterConsumed(date));
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
