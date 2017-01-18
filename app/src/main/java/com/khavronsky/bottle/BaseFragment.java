package com.khavronsky.bottle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseFragment extends Fragment {
    static final String PICS = "pics";
    static final String TITLE = "title";
    static final String VALUE = "value";
    static final String CAPACITY_STEP = "capacityStep";

    ImageButton plusButton;
    ImageButton minusButton;
    ImageView imageView;
    CirclesSlideIndicator slideIndicator;
    TextView titleCapacity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        plusButton = (ImageButton) view.findViewById(R.id.button_plus);
        minusButton = (ImageButton) view.findViewById(R.id.button_minus);
        imageView = (ImageView) view.findViewById(R.id.picture_capacity);
        titleCapacity = (TextView) view.findViewById(R.id.tv_title_of_capacity);
        slideIndicator = (CirclesSlideIndicator) view.findViewById(R.id.circlesSlideIndicator);

        Bundle bundle = getArguments();
        if (bundle != null) {
            CapacityType pic = (CapacityType) bundle.getSerializable(PICS);
            if (pic != null) {
                switch (pic) {
                    case BOTTLE:
                        imageView.setImageResource(R.drawable.water_botle_full);
                        return view;
                    case GLASS:
                        imageView.setImageResource(R.drawable.water_glass_full);
                        return view;
                    case DROP:
                        imageView.setImageResource(R.drawable.water_drop_full);
                        return view;
                }
            }
        }
        return view;
    }
}
