package com.khavronsky.bottle.MyCustomViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.khavronsky.bottle.R;


public class DefaulCapViewHolder extends RecyclerView.ViewHolder {
    private TextView capacityTitle;
    private TextView capacityStep;
    private ImageView imgOfCapacityType;
    private RadioButton radioButton;

    public DefaulCapViewHolder(View v) {
        super(v);
        capacityTitle = (TextView) v.findViewById(R.id.def_cap_title);
        capacityStep = (TextView) v.findViewById(R.id.def_cap_step);
        imgOfCapacityType = (ImageView) v.findViewById(R.id.def_cap_img);

    }
}
