package com.khavronsky.bottle;


public class FakeDate {
    private int value;
    private String title;

    public FakeDate(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public FakeDate() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
