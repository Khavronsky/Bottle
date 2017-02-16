package com.khavronsky.bottle.Data;


import android.os.Parcel;
import android.os.Parcelable;

public class ModelOfCapacityType implements Parcelable {

    private int id;
    private CapacityType pics;
    private String title;
    private int capacityStep;
    private boolean defaultCapacity = false;

    public ModelOfCapacityType(Parcel in) {
        id = in.readInt();
        title = in.readString();
        capacityStep = in.readInt();
        defaultCapacity = in.readByte() != 0;
    }

    public static final Creator<ModelOfCapacityType> CREATOR = new Creator<ModelOfCapacityType>() {
        @Override
        public ModelOfCapacityType createFromParcel(Parcel in) {
            return new ModelOfCapacityType(in);
        }

        @Override
        public ModelOfCapacityType[] newArray(int size) {
            return new ModelOfCapacityType[size];
        }
    };

    public ModelOfCapacityType() {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(capacityStep);
        dest.writeByte((byte) (defaultCapacity ? 1 : 0));
    }
}

