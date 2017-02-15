package com.khavronsky.bottle.MyCustomViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.R;


public class DefaultCapViewHolder extends RecyclerView.ViewHolder {
    private TextView capacityTitle;
    private TextView capacityStep;
    private ImageView imgOfCapacityType;
    private ImageView divider;
    private RadioButton radioButton;
    private ICapItemClickListener iCapItemClickListener;
    private View capItem;
    private boolean isChecked = false;

    public DefaultCapViewHolder(View v) {
        super(v);
        capacityTitle = (TextView) v.findViewById(R.id.def_cap_title);
        capacityStep = (TextView) v.findViewById(R.id.def_cap_step);
        imgOfCapacityType = (ImageView) v.findViewById(R.id.def_cap_img);
        radioButton = (RadioButton) v.findViewById(R.id.def_cap_radioButton);
        capItem = v.findViewById(R.id.def_cap_item);
        divider = (ImageView) v.findViewById(R.id.def_cap_divider);
    }
    public void hideDivider(){
        divider.setVisibility(View.INVISIBLE);
    }

    public void setParameters(final ModelOfCapacityType parameters) {
        capacityTitle.setText(parameters.getTitle());
        capacityStep.setText(String.valueOf(parameters.getCapacityStep()));
        imgOfCapacityType.setImageResource(parameters.getPics().getRes());
        isChecked = parameters.isDefaultCapacity();
        radioButton.setChecked(isChecked);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCapItemClickListener.rButtonClick(parameters.getId());
                isChecked = true;
            }
        });
        capItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCapItemClickListener.capItemClick(parameters.getId());
            }
        });
    }

    public void subscribeToICapItemClickListener(ICapItemClickListener listener){
        iCapItemClickListener = listener;
    }

    public interface ICapItemClickListener {
        void rButtonClick(int capacityID);
        void capItemClick(int capacityID);
    }
}
