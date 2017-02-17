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

import static com.khavronsky.bottle.MyLog.TAG;

public class DefaultCapacityFragment extends Fragment {
    TextView textView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    AdapterToDefCapRecycler adapter;
    TextView addCapButton;
    NewWaterCapacityFragment.IDataUpdater updater;
    Bundle state;
    NewWaterCapacityFragment fgd;
    boolean showFGD = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: savedInstanceState = [" + savedInstanceState + "]");
        final View view = inflater.inflate(R.layout.default_capacity_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.title_of_def_cap);
        addCapButton = (TextView) view.findViewById(R.id.add_new_capacity_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.default_capacity_list);
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new AdapterToDefCapRecycler();
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        state = new Bundle();
        adapter.setModelList(TestingWithFakeData.getDataForWaterScreen().getModelOfCapacityTypes());

        if (savedInstanceState != null) {
            showFGD = savedInstanceState.getBoolean("showFGD");
            Log.d(TAG, "onCreateView() state: " + showFGD);
            if(showFGD) {
                state.putAll(savedInstanceState);
                Log.d(TAG, "onCreateView() state: " + state);
                startWaterCapacityEditor(view, TestingWithFakeData.getDataForWaterScreen().getCapacityType(state.getInt("id", 0)), state.getInt("index"));
            }
        }
        adapter.subscribeToChooseListener(new AdapterToDefCapRecycler.IRBChooseListener() {
            @Override
            public void chooseDefaultCapacityType(int capacityID) {
                TestingWithFakeData.getDataForWaterScreen().checkedCapacityTypeAsDefault(capacityID);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void chooseCapacityTypeForEdit(int capacityID, int index) {
                Log.d("KhSY", "запуск редактирования емкости " + capacityID + " индекс " + index);
                state.putInt("id", capacityID);
                state.putInt("index", index);
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
        fgd = new NewWaterCapacityFragment();
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
            public void offShow() {
                showFGD = false;
                state.putBoolean("showFGD", false);
            }

            @Override
            public void update() {
                updateAdapter();
            }

        });
        fgd.show(getFragmentManager(), "");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(state);
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

    @Override
    public void onPause() {
        super.onPause();
        if(fgd!=null) {
            fgd.dismiss();
            fgd=null;
            state.putBoolean("showFGD", true);
        }else {
            state.putBoolean("showFGD", false);

        }
    }

    private void updateAdapter() {
        setDefCapIfUndefined();
        adapter.notifyDataSetChanged();
    }
}
