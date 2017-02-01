package com.khavronsky.bottle;


import java.util.ArrayList;
import java.util.List;

class TestingWithFakeData {
    private static List<FakeDate> dates = new ArrayList<>();
    private static List<DataModelToAddWaterView> dataModelToAddWaterViewList;

    static List<DataModelToAddWaterView> getFakeDataModelList() {

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

    static List <FakeDate> getFakeDateList(){

        for (int i = 1; i <= 31; i++) {
            dates.add(new FakeDate(i, "октября"));
        }
        return dates;
    }

    static List<DataForWaterScreen> getDataForWaterScreenList(){
        List<DataForWaterScreen> dataForWaterScreenList = new ArrayList<>();
        DataForWaterScreen dataForWaterScreen;
        for (int i = 1; i <= 31; i++){
            dataForWaterScreen = new DataForWaterScreen();
            dataForWaterScreen.setDateList(dates);
            dataForWaterScreen.setDataModelToAddWaterViews(dataModelToAddWaterViewList);
            dataForWaterScreenList.add(dataForWaterScreen);
        }
        return dataForWaterScreenList;
    }
}
