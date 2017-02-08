package com.khavronsky.bottle.Data;


import java.util.List;

public class DataForWaterScreen {

    private List<ModelOfCapacityType> modelOfCapacityTypes;
    private List<DataOfWaterConsumed> waterConsumedList;
    private List<ModelOfCapacityType> defaultValues;

    public List<ModelOfCapacityType> getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(List<ModelOfCapacityType> defaultValues) {
        this.defaultValues = defaultValues;
    }

    public List<ModelOfCapacityType> getModelOfCapacityTypes() {
        return modelOfCapacityTypes;
    }

    public void setModelOfCapacityTypes(List<ModelOfCapacityType> modelOfCapacityTypes) {
        this.modelOfCapacityTypes = modelOfCapacityTypes;
    }

    public List<DataOfWaterConsumed> getWaterConsumedList() {
        return waterConsumedList;
    }

    public void setWaterConsumedList(List<DataOfWaterConsumed> waterConsumedList) {
        this.waterConsumedList = waterConsumedList;
    }

    public DataOfWaterConsumed getWaterConsumed(int date) {

        for (DataOfWaterConsumed waterConsumed :
                waterConsumedList) {
            if (waterConsumed.getDate() == date) {
                return waterConsumed;
            }
        }
        return null;
    }

    public ModelOfCapacityType getCapacityType(int id) {
        for (ModelOfCapacityType capacityType :
                modelOfCapacityTypes) {
            if (capacityType.getId() == id) {
                return capacityType;
            }
        }
        return null;
    }

    public void replaceCapacityType(ModelOfCapacityType capacityType) {
        int index = modelOfCapacityTypes.indexOf(getCapacityType(capacityType.getId()));
        modelOfCapacityTypes.set(index, capacityType);
    }

    public void removeCapacityType(ModelOfCapacityType capacityType) {
        modelOfCapacityTypes.remove(capacityType);
    }
}