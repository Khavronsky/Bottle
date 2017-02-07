package com.khavronsky.bottle.Data;


public class DataOfWaterConsumed {

    private int amountOfWaterConsumed;
    private int waterTarget;

    public int getDate() {
        return date;
    }

    private int date;

    public DataOfWaterConsumed(int date, int amountOfWaterConsumed, int waterTarget) {
        this.date = date;
        this.amountOfWaterConsumed = amountOfWaterConsumed;
        this.waterTarget = waterTarget;
    }

    public DataOfWaterConsumed() {

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
