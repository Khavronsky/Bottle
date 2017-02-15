package com.khavronsky.bottle.Data;


import java.util.ArrayList;
import java.util.List;

public class TestingWithFakeData {

    private static DataForWaterScreen dataForWaterScreen;


    static {
        createDataForWaterScreen();
        createDataModelToAddWaterViewList();
        createDataOfWaterConsumed();
        createDefaultValues();
    }

    private static void createDataForWaterScreen() {
        dataForWaterScreen = new DataForWaterScreen();
    }

    private static void createDefaultValues() {
        List<ModelOfCapacityType> defaultValues = new ArrayList<>();
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

        defaultValues.add(first);
        defaultValues.add(second);
        defaultValues.add(third);

        dataForWaterScreen.setDefaultValues(defaultValues);
    }

    private static void createDataModelToAddWaterViewList() {
        List<ModelOfCapacityType> modelOfCapacityTypeList;
        ModelOfCapacityType first = new ModelOfCapacityType();
        ModelOfCapacityType second = new ModelOfCapacityType();
        ModelOfCapacityType third = new ModelOfCapacityType();
        ModelOfCapacityType fourth = new ModelOfCapacityType();

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

        fourth.setPics(CapacityType.DROP);
        fourth.setId(3);
        fourth.setTitle("Pipetka");
        fourth.setCapacityStep(50);

        modelOfCapacityTypeList = new ArrayList<>();
        modelOfCapacityTypeList.add(first);
        modelOfCapacityTypeList.add(second);
        modelOfCapacityTypeList.add(third);
        modelOfCapacityTypeList.add(fourth);
        dataForWaterScreen.setModelOfCapacityTypes(modelOfCapacityTypeList);
    }

    private static void createDataOfWaterConsumed() {
        List<DataOfWaterConsumed> newList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            newList.add(new DataOfWaterConsumed(i, 0, 2000));
        }
        dataForWaterScreen.setWaterConsumedList(newList);
    }

    public static DataForWaterScreen getDataForWaterScreen() {
        return dataForWaterScreen;
    }

    public static void addWaterConsumed(int date, int dataModelID, boolean b) {
        DataOfWaterConsumed tmp = dataForWaterScreen.getWaterConsumed(date);
        int capacityValue = dataForWaterScreen.getModelOfCapacityTypes().get(dataModelID).getCapacityStep();
        if (!b) {
            capacityValue *= -1;
        }
        capacityValue += tmp.getAmountOfWaterConsumed();
        if (capacityValue < 0) {
            capacityValue = 0;
        }
        tmp.setAmountOfWaterConsumed(capacityValue);
        dataForWaterScreen.getWaterConsumedList().add(dataModelID, tmp);
    }
}