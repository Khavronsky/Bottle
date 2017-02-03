package com.khavronsky.bottle.Activityes;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.khavronsky.bottle.Fragments.WaterScreenFragment;
import com.khavronsky.bottle.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment();
    }

    public void startFragment() {

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main, new WaterScreenFragment())
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}