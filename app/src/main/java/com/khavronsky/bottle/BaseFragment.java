package com.khavronsky.bottle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BaseFragment extends Fragment {
    static final String PICS = "pics";

    ImageView imageView;
    CapacityType capacityType=CapacityType.BOTTLE;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.picture_capacity);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setCapacityTypeImage(capacityType);
    }

    public void setCapacityType(CapacityType capacityType)

    {
        this.capacityType=capacityType;
    }
    public void setCapacityTypeImage(CapacityType capacityType)

    {
        switch (capacityType) {
            case BOTTLE:
                imageView.setImageResource(R.drawable.water_botle_full);
                break;
            case GLASS:
                imageView.setImageResource(R.drawable.water_glass_full);
                break;
            case DROP:
                imageView.setImageResource(R.drawable.water_drop_full);
                break;
        }


    }
}
