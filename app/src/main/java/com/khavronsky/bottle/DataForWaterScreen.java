package com.khavronsky.bottle;


import java.util.List;

public class DataForWaterScreen {
    private List<DataModelToAddWaterView> dataModelToAddWaterViews;
    private List<DataOfWaterConsumed> dateList;

    public List<DataModelToAddWaterView> getDataModelToAddWaterViews() {
        return dataModelToAddWaterViews;
    }

    public void setDataModelToAddWaterViews(List<DataModelToAddWaterView> dataModelToAddWaterViews) {
        this.dataModelToAddWaterViews = dataModelToAddWaterViews;
    }

    public List<DataOfWaterConsumed> getDateList() {
        return dateList;
    }

    public void setDateList(List<DataOfWaterConsumed> dateList) {
        this.dateList = dateList;
    }
}
