package com.khavronsky.bottle.MyCustomViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.khavronsky.bottle.R;


public class ProgressCompletionOfWaterTarget extends CardView {
    public ProgressCompletionOfWaterTarget(Context context) {
        super(context);
        init();
    }

    public ProgressCompletionOfWaterTarget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressCompletionOfWaterTarget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.new_water_target_progress_completion, this);
    }
}
