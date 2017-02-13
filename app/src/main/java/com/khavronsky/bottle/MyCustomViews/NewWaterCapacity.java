package com.khavronsky.bottle.MyCustomViews;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.khavronsky.bottle.Adapters.AdapterToWaterPicsOnViewPagerFragment;
import com.khavronsky.bottle.Data.CapacityType;
import com.khavronsky.bottle.Data.ModelOfCapacityType;
import com.khavronsky.bottle.Data.TestingWithFakeData;
import com.khavronsky.bottle.R;

import java.util.List;

public class NewWaterCapacity extends CardView implements View.OnClickListener {
    TextView title;
    TextView buttonDel;
    TextView buttonCancel;
    TextView buttonSave;
    EditText inputTitle;
    TextInputLayout textInputLayout;
    boolean titleIsCorrect = false;
    ViewPager viewPager;
    NumberPicker numberPicker;
    CirclesSlideIndicator slideIndicator;
    private CapacityType currentCapacityType = CapacityType.BOTTLE;
    FragmentManager childFragmentManager;

    ModelOfCapacityType modelOfCapacityType;
    List<ModelOfCapacityType> defaultValues;
    AdapterToWaterPicsOnViewPagerFragment adapter;
    ButtonListener listener;
    boolean newCapacityType = true;
    private int currentScreen;

    public NewWaterCapacity(Context context) {
        super(context);
        init();
    }

    public NewWaterCapacity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewWaterCapacity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(ModelOfCapacityType modelOfCapacityType) {
        if (modelOfCapacityType != null) {
            this.modelOfCapacityType = modelOfCapacityType;
            newCapacityType = false;
        } else {
            this.modelOfCapacityType = new ModelOfCapacityType();
        }
        firstSetView();
    }

    public void setFragmentManager(FragmentManager childFragmentManager) {
        this.childFragmentManager = childFragmentManager;
        setAdapter();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.new_water_capacity, this);

        textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        defaultValues = TestingWithFakeData.getDataForWaterScreen().getDefaultValues();
        title = (TextView) findViewById(R.id.capacity_title);
        buttonDel = (TextView) findViewById(R.id.button_del);
        buttonCancel = (TextView) findViewById(R.id.button_cancel);
        buttonSave = (TextView) findViewById(R.id.button_save);
        inputTitle = (EditText) findViewById(R.id.edit_capacity_title);
        slideIndicator = (CirclesSlideIndicator) findViewById(R.id.circlesSlideIndicator);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        setDividerColor(numberPicker, Color.rgb(45, 180, 229));
    }

    private void firstSetView() {
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonDel.setVisibility(INVISIBLE);
        inputTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s.toString();
                if (ss.matches("[A-Za-zА-Яа-я- ]+")) {
                    textInputLayout.setError("");
                    textInputLayout.setErrorEnabled(false);
                    titleIsCorrect = true;
                } else {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("ОЙ-ОЙ-ОЙ");
                    titleIsCorrect = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        setSlideIndicator();
        setAdapter();
        setPicker();
        setListenerToViewPager();
        if (!newCapacityType) {
            currentCapacityType = modelOfCapacityType.getPics();
            title.setText("Старая емкость");
            inputTitle.setText(modelOfCapacityType.getTitle());
            numberPicker.setValue(modelOfCapacityType.getCapacityStep() / 50);
            buttonDel.setVisibility(VISIBLE);
        }
    }

    private void setSlideIndicator() {
        slideIndicator.setCountOfCircle(defaultValues.size());
        slideIndicator.setFocusedCircle(currentCapacityType.ordinal());
        slideIndicator.invalidate();
    }

    private void setListenerToViewPager() {
        viewPager.setCurrentItem(currentCapacityType.ordinal());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentScreen = position;
                currentCapacityType = defaultValues.get(position).getPics();
                slideIndicator.setFocusedCircle(currentScreen);
                slideIndicator.invalidate();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setPicker() {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(60);
        numberPicker.setDisplayedValues(displayedValuesFormat());
        numberPicker.setWrapSelectorWheel(false);
    }

    @NonNull
    private String[] displayedValuesFormat() {
        String[] capacityValues = new String[61];
        for (int i = 0; i < capacityValues.length; i++) {
            String number = Integer.toString(i * 50);
            capacityValues[i] = number;
        }
        return capacityValues;
    }

    private void setAdapter() {
        if (viewPager != null && childFragmentManager != null) {
            adapter = new AdapterToWaterPicsOnViewPagerFragment(childFragmentManager, defaultValues);
            viewPager.setAdapter(adapter);
        }
    }

    public enum ButtonBehavior {
        CREATE_NEW_TYPE,
        CHANGE_TYPE,
        DELETE_TYPE,
        CLOSE_WINDOW
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        ButtonBehavior behavior = null;
        switch (id) {
            case R.id.button_cancel:
                (Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT)).show();
                behavior = ButtonBehavior.CLOSE_WINDOW;
                listener.buttonClick(modelOfCapacityType, behavior);
                break;
            case R.id.button_save:
                if (titleIsCorrect) {
                    (Toast.makeText(getContext(), "save", Toast.LENGTH_SHORT)).show();
                    String string = String.valueOf(inputTitle.getText());
                    modelOfCapacityType.setTitle(string);
                    modelOfCapacityType.setPics(currentCapacityType);
                    modelOfCapacityType.setCapacityStep(numberPicker.getValue() * 50);
                    if (newCapacityType) {
                        behavior = ButtonBehavior.CREATE_NEW_TYPE;
                    } else {
                        behavior = ButtonBehavior.CHANGE_TYPE;
                    }
                    listener.buttonClick(modelOfCapacityType, behavior);
                } else {
                    (Toast.makeText(getContext(), "ОЙ-ОЙ-ОЙ, НИЗЯ НАЖИМАТЬ", Toast.LENGTH_SHORT)).show();
                }
                break;
            case R.id.button_del:
                (Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT)).show();
                behavior = ButtonBehavior.DELETE_TYPE;
                listener.buttonClick(modelOfCapacityType, behavior);
                break;
        }
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public interface ButtonListener {
        void buttonClick(ModelOfCapacityType modelOfCapacityType, ButtonBehavior behavior);
    }

    public void subscribeToButtonListener(ButtonListener listener) {
        this.listener = listener;
    }
}
