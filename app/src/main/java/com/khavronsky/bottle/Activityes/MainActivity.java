package com.khavronsky.bottle.Activityes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.khavronsky.bottle.Fragments.DefaultCapacityFragment;
import com.khavronsky.bottle.Fragments.NewWaterCapacityFragment;
import com.khavronsky.bottle.Fragments.WaterScreenFragment;
import com.khavronsky.bottle.R;

public class MainActivity extends AppCompatActivity {
//    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        fragment1 = new WaterScreenFragment();
//        fragment2 = new NewWaterCapacityFragment();
    }

    public void startFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main, fragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.water_consumed):
                startFragment(new WaterScreenFragment());
                break;
            case (R.id.new_capacity):
                NewWaterCapacityFragment fgd= new NewWaterCapacityFragment();
                fgd.show(getSupportFragmentManager(),"");
                break;
            case (R.id.def_cap_list):
                startFragment(new DefaultCapacityFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}