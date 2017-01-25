package com.khavronsky.bottle;


import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class ClassForLightTesting implements AdditionWater.ButtonListener {

    ClassForLightTesting(AdditionWater testedObject) {
        testedObject.setOnButtonPlusMinusListener(this);
    }

    @Override
    public void buttonPlusOrMinusPressed(int dataModelID, boolean whichButtonPressed) {
        Log.d("TEST RUNNING", "buttonPlusOrMinusPressed: " + dataModelID + whichButtonPressed);
        String toastString = "MINUS PRESSED";
        if (whichButtonPressed){
            toastString = "PLUS PRESSED";
        }
        Toast.makeText(new MainActivity().getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
    }

    static List<DataModel> getList() {
        List<DataModel> dataModelList;
        DataModel first = new DataModel();
        DataModel second = new DataModel();
        DataModel third = new DataModel();

        first.setPics(CapacityType.BOTTLE);
        first.setId(0);
        first.setTitle("Bottle");
        first.setCapacityStep(500);

        second.setPics(CapacityType.DROP);
        second.setId(1);
        second.setTitle("Drop");
        second.setCapacityStep(100);

        third.setPics(CapacityType.GLASS);
        third.setId(2);
        third.setTitle("Glass");
        third.setCapacityStep(300);

        dataModelList = new ArrayList<>();
        dataModelList.add(first);
        dataModelList.add(second);
        dataModelList.add(third);
        return dataModelList;
    }
}
