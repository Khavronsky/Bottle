package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khavronsky.bottle.Data.DataModelToAddWaterView;

import java.util.ArrayList;
import java.util.List;

public class AdapterToAddWaterFragment extends FragmentPagerAdapter {
    private List<DataModelToAddWaterView> dataModelToAddWaterViews = new ArrayList<>();

    public AdapterToAddWaterFragment(FragmentManager fm, List<DataModelToAddWaterView> dataModelToAddWaterViews) {
        super(fm);
        this.dataModelToAddWaterViews = dataModelToAddWaterViews;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AddWaterFragment.PICS, dataModelToAddWaterViews.get(position).getPics());
        AddWaterFragment fragment = new AddWaterFragment();
        fragment.setArguments(bundle);
        fragment.setCapacityType( dataModelToAddWaterViews.get(position).getPics());
        return fragment;
    }

    @Override
    public int getCount() {
        return dataModelToAddWaterViews.size();
    }
}
