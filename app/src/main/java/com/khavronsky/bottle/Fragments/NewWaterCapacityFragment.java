package com.khavronsky.bottle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.MyCustomViews.NewWaterCapacity;
import com.khavronsky.bottle.R;

import java.util.List;


public class NewWaterCapacityFragment extends Fragment {
    NewWaterCapacity newWaterCapacity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_water_capacity_fragment, container, false);
        newWaterCapacity = (NewWaterCapacity) view.findViewById(R.id.new_water_capacity_in_fragment);
        newWaterCapacity.setChildFragmentManager(getChildFragmentManager());
//        newWaterCapacity.setData(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().get(3));
        newWaterCapacity.setData(null);
        newWaterCapacity.subscribeToButtonListener(new NewWaterCapacity.ButtonListener() {
            @Override
            public void buttonClick(ModelOfCapacityType modelOfCapacityType, boolean newType) {
                if (modelOfCapacityType != null){
                    if(newType){
                        modelOfCapacityType.setId(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().size());
                        List<ModelOfCapacityType> list = TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes();
                        list.add(modelOfCapacityType);
                        TestingWithFakeData.getDataForWaterScreen().setModelOfCapacityTypes(list);
                    }
                }
            }
        });
        return view;
    }
}