package com.khavronsky.bottle.Data;


import com.khavronsky.bottle.R;

public enum CapacityType {
    BOTTLE(R.drawable.water_botle_full),
    DROP(R.drawable.water_glass_full),
    GLASS(R.drawable.water_drop_full);
    private int res;
    CapacityType(int res){
        this.res = res;
    }

    public int getRes() {
        return res;
    }
}
