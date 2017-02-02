package com.khavronsky.bottle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WaterScreenFragment extends Fragment implements WaterScreenPresenter.IView {

    private final static String TAG = "MyLog WaterScreenFragment";
    int currentDate;
    AdditionWater additionWater;
    MyDateChanger dateChanger;
    WaterScreenPresenter presenter;
    TextView showConsumedWater;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.water_screen_fragment, container, false);
        init(view);
        return view;
    }

    @Override
    public void show(DataOfWaterConsumed waterConsumed) {
        showConsumedWater.setText(formatDataToShow(waterConsumed));
    }

    private String formatDataToShow(DataOfWaterConsumed waterConsumed) {
        int amount = waterConsumed.getAmountOfWaterConsumed();
        int amount2 = amount % 100 == 0 ? (amount % 1000 / 100) : (amount % 1000 / 10);
        int target = waterConsumed.getWaterTarget();
        int target1 = target % 100 == 0 ? (target % 1000 / 100) : (target % 1000 / 10);
        return amount / 1000 + "," + amount2 + "/" + target / 1000 + "," + target1 + " л";
    }

    private void init(View view) {
        Log.d(TAG, "init:");
        additionWater = (AdditionWater) view.findViewById(R.id.addition_water);
        dateChanger = (MyDateChanger) view.findViewById(R.id.date_changer);
        presenter = new WaterScreenPresenter(this);
        showConsumedWater = (TextView) view.findViewById(R.id.consumed_water);
        dateChanger.subscribeToChanges(new MyDateChanger.IDateChanged() {
            @Override
            public void changesHappened() {
                currentDate = dateChanger.getCurrentDate();
                Log.d(TAG, "presenter: " + presenter + " date " + currentDate);
                presenter.getDate(currentDate);
            }
        });

        additionWater.setDataModelToAddWaterViewList(TestingWithFakeData.getDataModelToAddWaterViewList());
        additionWater.setOnButtonPlusMinusListener(new AdditionWater.ButtonListener() {
            @Override
            public void buttonPlusOrMinusPressed(int dataModelID, int capacity) {
                Log.d(TAG, "buttonPlusOrMinusPressed: cap" + capacity);
                presenter.addConsumedWater(currentDate, capacity);
            }
        });
    }
}
