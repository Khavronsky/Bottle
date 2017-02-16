package com.khavronsky.bottle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    NewWaterCapacityFragment.IDataUpdater updater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.default_capacity_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.title_of_def_cap);
        addCapButton = (TextView) view.findViewById(R.id.add_new_capacity_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.default_capacity_list);
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new AdapterToDefCapRecycler();
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        setCallback();
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setModelList(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes());
        adapter.subscribeToChooseListener(new AdapterToDefCapRecycler.IRBChooseListener() {
            @Override
            public void chooseDefaultCapacityType(int capacityID) {
                TestingWithFakeData.getDataForWaterScreen().checkedCapacityTypeAsDefault(capacityID);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void chooseCapacityTypeForEdit(int capacityID, int index) {
                Log.d("KhSY", "запуск редактирования емкости " + capacityID + " индекс " + index);
                startWaterCapacityEditor(view, TestingWithFakeData.getDataForWaterScreen().getCapacityType(capacityID), index);
                updateAdapter();
            }
        });
        recyclerView.setAdapter(adapter);
        updateAdapter();

        addCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWaterCapacityEditor(view, null, 0);
            }
        });

        return view;
    }

    private void startWaterCapacityEditor(final View view, final ModelOfCapacityType model, final int index) {
        NewWaterCapacityFragment fgd = new NewWaterCapacityFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("model", model);
        fgd.setArguments(bundle);
        fgd.subscribeToUpdater(new NewWaterCapacityFragment.IDataUpdater() {
            @Override
            public void deleteCapType() {
                Snackbar.make(view, "Ты чё? Ща правда удалю!", Snackbar.LENGTH_LONG).setAction("Бес попутал", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.setDefaultCapacity(false);
                        TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().add(index, model);
                        updateAdapter();
                    }
                }).show();
            }

            @Override
            public void update() {
                updateAdapter();
            }
        });
        fgd.show(getFragmentManager(), "");
    }

    private void setDefCapIfUndefined() {
        if (!TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().isEmpty()) {
            boolean checkDefCap = false;
            for (ModelOfCapacityType model :
                    TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes()) {
                if (model.isDefaultCapacity()) {
                    checkDefCap = true;
                }

            }
            if (!checkDefCap) {
                int id = TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().get(0).getId();
                TestingWithFakeData.getDataForWaterScreen().checkedCapacityTypeAsDefault(id);
            }
        }
    }

//    void setCallback(final View view){
//        updater = new NewWaterCapacityFragment.IDataUpdater() {
//            @Override
//            public void deleteCapType() {
//                Snackbar.make(view, "Ты чё? Ща правда удалю!", Snackbar.LENGTH_LONG).setAction("Бес попутал", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ModelOfCapacityType model = TestingWithFakeData.getDataForWaterScreen().getCapacityType()
//                        model.setDefaultCapacity(false);
//                        TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes().add(index, model);
//                        updateAdapter();
//                    }
//                }).show();
//            }
//
//            @Override
//            public void update() {
//                updateAdapter();
//            }
//        };
//    }

    private void updateAdapter() {
        setDefCapIfUndefined();
        adapter.notifyDataSetChanged();
    }
}
