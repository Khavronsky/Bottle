package com.khavronsky.bottle.Data;


import java.util.List;

public class DataForWaterScreen {
    private List<ModelOfCapacityType> modelOfCapacityTypes;
    private List<DataOfWaterConsumed> dateList;

    public List<ModelOfCapacityType> getModelOfCapacityTypes() {
        return modelOfCapacityTypes;
    }

    public void setModelOfCapacityTypes(List<ModelOfCapacityType> modelOfCapacityTypes) {
        this.modelOfCapacityTypes = modelOfCapacityTypes;
    }

    public List<DataOfWaterConsumed> getDateList() {
        return dateList;
    }

    public void setDateList(List<DataOfWaterConsumed> dateList) {
        this.dateList = dateList;
    }
}
