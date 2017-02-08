package com.khavronsky.bottle.MyCustomViews;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.khavronsky.bottle.R;

public class MyDateChanger extends CardView implements View.OnClickListener {
    private final static String TAG = "MyLog MyDateChanger";
    private int currentDate = 15;

    ImageButton previousDate;
    ImageButton nextDate;
    TextView showDate;
    IDateChanged event;

    public interface IDateChanged {
        void changesHappened();
    }

    public void subscribeToChanges(IDateChanged event) {
        this.event = event;
    }

    public MyDateChanger(Context context) {
        super(context);
        init();
    }

    public MyDateChanger(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDateChanger(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.my_date_changer, this);

        previousDate = (ImageButton) findViewById(R.id.previous_date);
        previousDate.setOnClickListener(this);
        nextDate = (ImageButton) findViewById(R.id.next_date);
        nextDate.setOnClickListener(this);
        showDate = (TextView) findViewById(R.id.date_title);
        showCurrentDate();
    }

    public int getCurrentDate() {
        return currentDate;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case (R.id.next_date):
                if (currentDate < 31) {
                    currentDate++;
                    event.changesHappened();
                    showCurrentDate();
                }
                break;
            case (R.id.previous_date):
                if (currentDate > 1) {
                    currentDate--;
                    event.changesHappened();
                    showCurrentDate();
                }
                break;
        }
    }

    void showCurrentDate() {
        String s = " октября";
        s = (currentDate) + s;
        showDate.setText(s);
        showDate.invalidate();
    }
}