package com.khavronsky.bottle;


class DataModel {

    private CapacityType pics;
    private String title;
    private int value;
    private int step;

    public CapacityType getPics() {
        return pics;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}

