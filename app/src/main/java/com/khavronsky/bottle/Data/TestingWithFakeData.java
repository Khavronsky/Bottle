package com.khavronsky.bottle.Data;


import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class TestingWithFakeData {
    private static SparseArray<DataOfWaterConsumed> consumedWaterList = new SparseArray(); //TODO change to ArrayList
    private static List<ModelOfCapacityType> modelOfCapacityTypeList;
    private static DataForWaterScreen dataForWaterScreen;

    static {
        createDataModelToAddWaterViewList();
        createDataOfWaterConsumed();
        createDataForWaterScreen();
    }
    private static void createDataForWaterScreen(){
        dataForWaterScreen = new DataForWaterScreen();
        dataForWaterScreen.setModelOfCapacityTypes(modelOfCapacityTypeList);
//        dataForWaterScreen.setDateList((List<DataOfWaterConsumed>) consumedWaterList); TODO add DataOfWaterConsumed into a dataForWaterScreen
    }

    public static DataForWaterScreen getDataForWaterScreen() {
        return dataForWaterScreen;
    }

    private static void createDataModelToAddWaterViewList(){
        ModelOfCapacityType first = new ModelOfCapacityType();
        ModelOfCapacityType second = new ModelOfCapacityType();
        ModelOfCapacityType third = new ModelOfCapacityType();

        first.setPics(CapacityType.BOTTLE);
        first.setId(0);
        first.setTitle("Bottle");
        first.setCapacityStep(500);

        second.setPics(CapacityType.DROP);
        second.setId(1);
        second.setTitle("Drop");
        second.setCapacityStep(100);

        third.setPics(CapacityType.GLASS);
        third.setId(2);
        third.setTitle("Glass");
        third.setCapacityStep(250);

        modelOfCapacityTypeList = new ArrayList<>();
        modelOfCapacityTypeList.add(first);
        modelOfCapacityTypeList.add(second);
        modelOfCapacityTypeList.add(third);
    }

    public static List<ModelOfCapacityType> getModelOfCapacityTypeList() {
        return modelOfCapacityTypeList;
    }

    private static void createDataOfWaterConsumed(){
        for (int i = 1; i <= 31; i++) {
            consumedWaterList.put(i, new DataOfWaterConsumed(0, 2000));
        }
    }

    public static DataOfWaterConsumed getDataOfWaterConsumed(int date){
        return consumedWaterList.get(date);
    }

    public static void addWaterConsumed(int date, int capacity){ //TODO change date to ID
        Log.d("MyLog Fake", "addWaterConsumed: cap" + capacity);
        DataOfWaterConsumed tmp = consumedWaterList.get(date);
        int capacitySumm = tmp.getAmountOfWaterConsumed() + capacity;
        Log.d("MyLog Fake", "addWaterConsumed: capSumm" + capacitySumm);

        if (capacitySumm < 0) {
            capacitySumm = 0;
        }
        tmp.setAmountOfWaterConsumed(capacitySumm);
        consumedWaterList.put(date, tmp);
    }

}
