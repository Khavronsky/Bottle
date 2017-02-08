package com.khavronsky.bottle.Data;


public class DataOfWaterConsumed {

    private int amountOfWaterConsumed;
    private int waterTarget;
    private int date;

    public DataOfWaterConsumed(int date, int amountOfWaterConsumed, int waterTarget) {
        this.date = date;
        this.amountOfWaterConsumed = amountOfWaterConsumed;
        this.waterTarget = waterTarget;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAmountOfWaterConsumed() {
        return amountOfWaterConsumed;
    }

    public void setAmountOfWaterConsumed(int amountOfWaterConsumed) {
        this.amountOfWaterConsumed = amountOfWaterConsumed;
    }

    public int getWaterTarget() {
        return waterTarget;
    }

    public void setWaterTarget(int waterTarget) {
        this.waterTarget = waterTarget;
    }
}
