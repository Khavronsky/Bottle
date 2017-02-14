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
    public void onBindViewHolder(DefaultCapViewHolder holder, int position) {
        holder.setParameters(modelList.get(position));
        holder.subscribeToRButtonClick(new DefaultCapViewHolder.IRBListener() {

            @Override
            public void rButtonClick(int capacityID) {
                chooseListener.chooseDefaultCapacityType(capacityID);
            }
        });
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
    }
}
