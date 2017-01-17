package com.khavronsky.bottle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterToBaseFragment extends FragmentPagerAdapter {
    List<DataModel> dataModels = new ArrayList<>();

    interface VPCallback {
        void vpCallback(int id);
    }

    private VPCallback callback;

    public AdapterToBaseFragment(FragmentManager fm, List<DataModel> dataModels) {
        super(fm);
        this.dataModels = dataModels;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        Fragment fragment = new BaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
