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
import com.khavronsky.bottle.Data.ModelOfCapacityType;
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
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new AdapterToDefCapRecycler();
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setModelList(TestingWithFakeData.getModelOfCapacityTypeList());
        adapter.subscribeToChooseListener(new AdapterToDefCapRecycler.IRBChooseListener() {
            @Override
            public void chooseDefaultCapacityType(int capacityID) {
                String s = "Choose default capacity -> id " + capacityID;
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void chooseCapacityTypeForEdit(int capacityID) {
                String s = "Edit capacity -> id " + capacityID;
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                startWaterCapacityEditor(TestingWithFakeData.getDataForWaterScreen().getCapacityType(capacityID));
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWaterCapacityEditor(null);
            }
        });

        return view;
    }

    private void startWaterCapacityEditor(ModelOfCapacityType model) {
        NewWaterCapacityFragment fgd= new NewWaterCapacityFragment();
        fgd.setModel(model);
        fgd.subscribeToUpdater(new NewWaterCapacityFragment.IDataUpdater() {
            @Override
            public void update() {
                adapter.notifyDataSetChanged();
            }
        });
        fgd.show(getFragmentManager(),"");
    }

}
