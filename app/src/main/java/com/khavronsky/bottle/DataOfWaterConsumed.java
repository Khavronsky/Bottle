package com.khavronsky.bottle;


public class DataOfWaterConsumed {

    private int amountOfWaterConsumed;
    private int waterTarget;

    public DataOfWaterConsumed(int amountOfWaterConsumed, int waterTarget) {
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
