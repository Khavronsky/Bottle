package com.khavronsky.bottle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.MyCustomViews.NewWaterCapacity;
import com.khavronsky.bottle.R;


public class NewWaterCapacityFragment extends Fragment {
    NewWaterCapacity newWaterCapacity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_water_capacity_fragment, container, false);
        newWaterCapacity = (NewWaterCapacity) view.findViewById(R.id.new_water_capacity_in_fragment);
        newWaterCapacity.setChildFragmentManager(getChildFragmentManager());
//        newWaterCapacity.setDataModelToAddWaterViewList(TestingWithFakeData.getDataModelToAddWaterViewList());
        newWaterCapacity.setData(TestingWithFakeData.getDataForWaterScreen());
        return view;
    }
}