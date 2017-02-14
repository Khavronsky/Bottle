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
    private RadioButton radioButton;
    private IRBListener irbListener;

    public DefaultCapViewHolder(View v) {
        super(v);
        capacityTitle = (TextView) v.findViewById(R.id.def_cap_title);
        capacityStep = (TextView) v.findViewById(R.id.def_cap_step);
        imgOfCapacityType = (ImageView) v.findViewById(R.id.def_cap_img);
        radioButton = (RadioButton) v.findViewById(R.id.def_cap_radioButton);

    }

    public void setParameters(final ModelOfCapacityType parameters) {
        capacityTitle.setText(parameters.getTitle());
        capacityStep.setText(String.valueOf(parameters.getCapacityStep()));
        imgOfCapacityType.setImageResource(parameters.getPics().getRes());
        radioButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                irbListener.chooseCapacityTypeForEdit(parameters.getId());
                return true;
            }
        });
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irbListener.chooseDefaultCapacityType(parameters.getId());
            }
        });
    }

    public void subscribeToRButtonClick(IRBListener listener){
        irbListener = listener;
    }

    public interface IRBListener {
        void chooseCapacityTypeForEdit(int capacityID);
        void chooseDefaultCapacityType(int capacityID);
    }
}
