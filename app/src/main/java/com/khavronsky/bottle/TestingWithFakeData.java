package com.khavronsky.bottle;


import java.util.ArrayList;
import java.util.List;

class TestingWithFakeData {

    static List<DataModel> getFakeDataModelList() {
        List<DataModel> dataModelList;
        DataModel first = new DataModel();
        DataModel second = new DataModel();
        DataModel third = new DataModel();

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

        dataModelList = new ArrayList<>();
        dataModelList.add(first);
        dataModelList.add(second);
        dataModelList.add(third);
        return dataModelList;
    }
    static List <FakeDate> getFakeDateList(){
        List<FakeDate> dates = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            dates.add(new FakeDate(i, "октября"));
        }
        return dates;
    }
}
