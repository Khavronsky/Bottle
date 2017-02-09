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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_water_capacity_fragment, container, false);
        NewWaterCapacity newWaterCapacity = (NewWaterCapacity) view.findViewById(R.id.new_water_capacity_in_fragment);
        newWaterCapacity.setFragmentManager(getChildFragmentManager());
        if (TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().size() > 3) {
            newWaterCapacity.setData(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().get(3));
        } else {
            newWaterCapacity.setData(null);
        }
        newWaterCapacity.subscribeToButtonListener(new NewWaterCapacity.ButtonListener() {
            @Override
            public void buttonClick(ModelOfCapacityType modelOfCapacityType, NewWaterCapacity.ButtonBehavior behavior) {
                switch (behavior) {
                    case CREATE_NEW_TYPE:
                        modelOfCapacityType.setId(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().size());
                        List<ModelOfCapacityType> list = TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes();
                        list.add(modelOfCapacityType);
                        TestingWithFakeData.getDataForWaterScreen().setModelOfCapacityTypes(list);
                    case CHANGE_TYPE:
                        TestingWithFakeData.getDataForWaterScreen().replaceCapacityType(modelOfCapacityType);
                        break;
                    case DELETE_TYPE:
                        TestingWithFakeData.getDataForWaterScreen().removeCapacityType(modelOfCapacityType);
                        break;
                }
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}