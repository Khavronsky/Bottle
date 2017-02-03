package com.khavronsky.bottle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khavronsky.bottle.Data.CapacityType;

public class AddWaterFragment extends Fragment {
    static final String PICS = "pics";

    ImageView imageView;
    CapacityType capacityType = CapacityType.BOTTLE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_water_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.picture_capacity);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setCapacityTypeImage(capacityType);
    }

    public void setCapacityType(CapacityType capacityType) {
        this.capacityType = capacityType;
    }

    public void setCapacityTypeImage(CapacityType capacityType) {
        imageView.setImageResource(capacityType.getRes());
    }
}