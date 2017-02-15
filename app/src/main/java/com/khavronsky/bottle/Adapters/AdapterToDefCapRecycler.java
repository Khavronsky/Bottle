package com.khavronsky.bottle.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.MyCustomViews.DefaultCapViewHolder;
import com.khavronsky.bottle.R;

import java.util.List;

public class AdapterToDefCapRecycler extends RecyclerView.Adapter<DefaultCapViewHolder> {

    private List<ModelOfCapacityType> modelList;
    private IRBChooseListener chooseListener;
    private int defaultCapacity;

    public void setDefaultCapacity(int defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    public void setModelList(List<ModelOfCapacityType> modelList) {
        this.modelList = modelList;
    }

    @Override
    public DefaultCapViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_for_default_capacity_list, viewGroup, false);
        return new DefaultCapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DefaultCapViewHolder holder, final int position) {
        holder.setChecked(defaultCapacity == position);
        holder.setParameters(modelList.get(position));
        holder.subscribeToICapItemClickListener(new DefaultCapViewHolder.ICapItemClickListener() {

            @Override
            public void rButtonClick(int capacityID) {
                chooseListener.chooseDefaultCapacityType(capacityID);
                defaultCapacity = position;
            }

            @Override
            public void capItemClick(int capacityID) {
                chooseListener.chooseCapacityTypeForEdit(capacityID);
            }
        });
        if (position == 0) holder.hideDivider();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void subscribeToChooseListener(IRBChooseListener listener) {
        chooseListener = listener;
    }

    public interface IRBChooseListener {
        void chooseDefaultCapacityType(int capacityID);
        void chooseCapacityTypeForEdit(int capacityID);
    }
}
