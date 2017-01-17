package com.khavronsky.bottle;


class DataModel {

    private int id;
    private CapacityType pics;
    private String title;
    private int capacityValue;
    private int capacityStep;
    public CapacityType getPics() {
        return pics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPics(CapacityType pics) {
        this.pics = pics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacityValue() {
        return capacityValue;
    }

    public void setCapacityValue(int capacityValue) {
        this.capacityValue = capacityValue;
    }

    public int getCapacityStep() {
        return capacityStep;
    }

    public void setCapacityStep(int capacityStep) {
        this.capacityStep = capacityStep;
    }
}

