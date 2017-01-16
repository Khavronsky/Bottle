package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AdapterToBaseFragment extends FragmentPagerAdapter {
    private int fragmentCount;
    private CapacityType[] pics;
    private String[] title;
    private int[] value;
    private int[] capacityStep;


    public AdapterToBaseFragment(FragmentManager fm, List<DataModel> dataModels) {
        super(fm);
        fragmentCount = dataModels.size();
        for (int i = 0; i < fragmentCount; i++) {
            pics[i] = dataModels.get(i).getPics();
            title[i] = dataModels.get(i).getTitle();
            value[i] = dataModels.get(i).getValue();
            capacityStep[i] = dataModels.get(i).getCapacityStep();
        }
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.TITLE, title[position]);
        bundle.putInt(BaseFragment.VALUE, value[position]);
        bundle.putInt(BaseFragment.CAPACITY_STEP, capacityStep[position]);
        bundle.putSerializable(BaseFragment.PICS, pics[position]);
        Fragment fragment = new BaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }
}
