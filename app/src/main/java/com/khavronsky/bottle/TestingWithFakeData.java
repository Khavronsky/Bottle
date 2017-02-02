package com.khavronsky.bottle;


import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

class TestingWithFakeData {
    private static SparseArray<DataOfWaterConsumed> consumedWaterList = new SparseArray();
    private static List<DataModelToAddWaterView> dataModelToAddWaterViewList;

    static {
        createDataOfWaterConsumed();
    }

    static List<DataModelToAddWaterView> getDataModelToAddWaterViewList() {

        DataModelToAddWaterView first = new DataModelToAddWaterView();
        DataModelToAddWaterView second = new DataModelToAddWaterView();
        DataModelToAddWaterView third = new DataModelToAddWaterView();

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

        dataModelToAddWaterViewList = new ArrayList<>();
        dataModelToAddWaterViewList.add(first);
        dataModelToAddWaterViewList.add(second);
        dataModelToAddWaterViewList.add(third);
        return dataModelToAddWaterViewList;
    }

    private static void createDataOfWaterConsumed(){
        for (int i = 1; i <= 31; i++) {
            consumedWaterList.put(i, new DataOfWaterConsumed(0, 2000));
        }
    }

    static DataOfWaterConsumed getDataOfWaterConsumed(int date){
        return consumedWaterList.get(date);
    }

    static void addWaterConsumed(int date, int capacity){
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

//    static List<DataForWaterScreen> getDataForWaterScreenList(){
//        List<DataForWaterScreen> dataForWaterScreenList = new ArrayList<>();
//        DataForWaterScreen dataForWaterScreen;
//        for (int i = 1; i <= 31; i++){
//            dataForWaterScreen = new DataForWaterScreen();
//            dataForWaterScreen.setDateList(consumedWaterList);
//            dataForWaterScreen.setDataModelToAddWaterViews(dataModelToAddWaterViewList);
//            dataForWaterScreenList.add(dataForWaterScreen);
//        }
//        return dataForWaterScreenList;
//    }
}
