package com.khavronsky.bottle.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khavronsky.bottle.Data.DataModelToAddWaterView;
import com.khavronsky.bottle.Fragments.WaterPicsOnViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterToWaterPicsOnViewPagerFragment extends FragmentPagerAdapter {
    private List<DataModelToAddWaterView> dataModelToAddWaterViews = new ArrayList<>();

    public AdapterToWaterPicsOnViewPagerFragment(FragmentManager fm, List<DataModelToAddWaterView> dataModelToAddWaterViews) {
        super(fm);
        this.dataModelToAddWaterViews = dataModelToAddWaterViews;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(WaterPicsOnViewPagerFragment.PICS, dataModelToAddWaterViews.get(position).getPics());
        WaterPicsOnViewPagerFragment fragment = new WaterPicsOnViewPagerFragment();
        fragment.setArguments(bundle);
        fragment.setCapacityType( dataModelToAddWaterViews.get(position).getPics());
        return fragment;
    }

    @Override
    public int getCount() {
        return dataModelToAddWaterViews.size();
    }
}
