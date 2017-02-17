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

import static com.khavronsky.bottle.MyLog.TAG;

public class NewWaterCapacityFragment extends DialogFragment {

    ModelOfCapacityType model = null;
    NewWaterCapacity newWaterCapacity;
    IDataUpdater updater;

    private void setModel() {
        model = getArguments().getParcelable("model");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "NewWaterCapacityFragment / onCreateView: ");
        View view = inflater.inflate(R.layout.new_water_capacity_fragment, container, false);
        newWaterCapacity = (NewWaterCapacity) view.findViewById(R.id.new_water_capacity_in_fragment);
        newWaterCapacity.setFragmentManager(getChildFragmentManager());
        setModel();
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
                        modelOfCapacityType.setId(idForNewCapacity());
                        List<ModelOfCapacityType> list = TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes();
                        list.add(modelOfCapacityType);
                        TestingWithFakeData.getDataForWaterScreen().setModelOfCapacityTypes(list);
                        Log.d("KhSY", "новая емкость id=" + modelOfCapacityType.getId());
                        break;
                    case CHANGE_TYPE:
                        TestingWithFakeData.getDataForWaterScreen().replaceCapacityType(modelOfCapacityType);
                        break;
                    case DELETE_TYPE:
                        TestingWithFakeData.getDataForWaterScreen().removeCapacityType(modelOfCapacityType);
                        if (updater != null) {
                            updater.deleteCapType();
                        }
                        break;
                }
                Log.d(TAG, "buttonClick() " + updater);
                if (updater != null) {
                    updater.update();
                }
                dismiss();
                if (updater!=null){
                    updater.offShow();
                }
            }
        });
    }

    public interface IDataUpdater {
        void update();

        void deleteCapType();
        void offShow();
    }

    void subscribeToUpdater(IDataUpdater updater) {
        this.updater = updater;
    }

    private int idForNewCapacity() {
        int i = 0;
        for (ModelOfCapacityType model :
                TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes()) {
            if (model.getId() > i) {
                i = model.getId();
            }
        }
        return i + 1;
    }
}