package com.khavronsky.bottle;


import java.util.List;

public class DataForWaterScreen {
    private List<DataModelToAddWaterView> dataModelToAddWaterViews;
    private List<FakeDate> dateList;

    public List<DataModelToAddWaterView> getDataModelToAddWaterViews() {
        return dataModelToAddWaterViews;
    }

    public void setDataModelToAddWaterViews(List<DataModelToAddWaterView> dataModelToAddWaterViews) {
        this.dataModelToAddWaterViews = dataModelToAddWaterViews;
    }

    public List<FakeDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<FakeDate> dateList) {
        this.dateList = dateList;
    }
}
