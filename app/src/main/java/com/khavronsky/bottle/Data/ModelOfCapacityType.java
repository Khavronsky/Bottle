package com.khavronsky.bottle.Data;


public class ModelOfCapacityType {

    private int id;
    private CapacityType pics;
    private String title;
    private int capacityStep;
    private boolean defaultCapacity = false;

    public boolean isDefaultCapacity() {
        return defaultCapacity;
    }

    public void setDefaultCapacity(boolean defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getCapacityStep() {
        return capacityStep;
    }

    public void setCapacityStep(int capacityStep) {
        this.capacityStep = capacityStep;
    }
}

