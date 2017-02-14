package com.khavronsky.bottle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khavronsky.bottle.Adapters.AdapterToDefCapRecycler;
import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.R;


public class DefaultCapacityFragment extends Fragment {
    TextView textView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    AdapterToDefCapRecycler adapter;
    TextView addCapButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_capacity_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.title_of_def_cap);
        addCapButton = (TextView) view.findViewById(R.id.add_new_capacity_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.default_capacity_list);
        adapter = new AdapterToDefCapRecycler();
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setModelList(TestingWithFakeData.getModelOfCapacityTypeList());
        adapter.subscribeToChooseListener(new AdapterToDefCapRecycler.IRBChooseListener() {
            @Override
            public void chooseDefaultCapacityType(int capacityID) {
                String s = "id = " + capacityID;
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
