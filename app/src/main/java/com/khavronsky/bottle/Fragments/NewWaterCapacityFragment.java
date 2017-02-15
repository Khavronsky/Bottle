package com.khavronsky.bottle.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.MyCustomViews.NewWaterCapacity;
import com.khavronsky.bottle.R;

import java.util.List;


public class NewWaterCapacityFragment extends DialogFragment {

    ModelOfCapacityType model = null;
    NewWaterCapacity newWaterCapacity;
    IDataUpdater updater;

    public void setModel(ModelOfCapacityType model) {
        this.model = model;
        Log.d("KhSY", " 1");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Log.d("KhSY", " 2");
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_water_capacity_fragment, container, false);
        newWaterCapacity = (NewWaterCapacity) view.findViewById(R.id.new_water_capacity_in_fragment);
        newWaterCapacity.setFragmentManager(getChildFragmentManager());
        Log.d("KhSY", " 3");
        startFragment();
        return view;
    }

    void startFragment() {
        newWaterCapacity.setData(model);
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
                        updater.deleteCapType();
                        break;
                }
                updater.update();
                dismiss();
            }
        });
    }

    public interface IDataUpdater{
        void update();
        void deleteCapType();
    }
    void subscribeToUpdater(IDataUpdater updater){
        this.updater = updater;
    }
}