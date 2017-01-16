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
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        plusButton = (ImageButton) view.findViewById(R.id.button_plus);
        minusButton = (ImageButton) view.findViewById(R.id.button_minus);
        imageView = (ImageView) view.findViewById(R.id.picture_capacity);
        titleCapacity = (TextView) view.findViewById(R.id.title_of_capacity);
        slideIndicator = (CirclesSlideIndicator) view.findViewById(R.id.circlesSlideIndicator);

        Bundle bundle = getArguments();
        if (bundle != null){


        }
        return view;
    }
}
