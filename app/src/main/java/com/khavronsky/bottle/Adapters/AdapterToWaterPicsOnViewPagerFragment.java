package com.khavronsky.bottle.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.Fragments.WaterPicsOnViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterToWaterPicsOnViewPagerFragment extends FragmentPagerAdapter {
    private List<ModelOfCapacityType> modelOfCapacityTypes = new ArrayList<>();

    public AdapterToWaterPicsOnViewPagerFragment(FragmentManager fm, List<ModelOfCapacityType> modelOfCapacityTypes) {
        super(fm);
        this.modelOfCapacityTypes = modelOfCapacityTypes;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(WaterPicsOnViewPagerFragment.PICS, modelOfCapacityTypes.get(position).getPics());
        WaterPicsOnViewPagerFragment fragment = new WaterPicsOnViewPagerFragment();
        fragment.setArguments(bundle);
        fragment.setCapacityType( modelOfCapacityTypes.get(position).getPics());
        return fragment;
    }

    @Override
    public int getCount() {
        return modelOfCapacityTypes.size();
    }
}
