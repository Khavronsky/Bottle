package com.khavronsky.bottle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class WaterScreenFragment extends Fragment implements WaterScreenPresenter.IView {

    private final static String TAG = "MyLog WaterScreenFragment";

    AdditionWater additionWater;
    MyDateChanger dateChanger;
    WaterScreenPresenter presenter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.water_screen_fragment, container, false);
        init(view);
        return view;
    }

    @Override
    public void show() {

    }

    private void init(View view){
        Log.d(TAG, "init:");
        additionWater = (AdditionWater) view.findViewById(R.id.addition_water);
        dateChanger = (MyDateChanger) view.findViewById(R.id.date_changer);
        presenter = new WaterScreenPresenter(this);
        dateChanger.subscribeToChanges(new MyDateChanger.IDateChanged() {
            @Override
            public void changesHappened() {
                int date = dateChanger.getCurrentDate();
                Log.d(TAG, "presenter: " + presenter + " date " + date);
                presenter.getDate(date);
            }
        });

        additionWater.setDataModelToAddWaterViewList(TestingWithFakeData.getFakeDataModelList());
        additionWater.setOnButtonPlusMinusListener(new AdditionWater.ButtonListener() {
            @Override
            public void buttonPlusOrMinusPressed(int dataModelID, boolean whichButtonPressed) {
                String toastString = "MINUS PRESSED";
                if (whichButtonPressed){
                    toastString = "PLUS PRESSED";
                }
                Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
