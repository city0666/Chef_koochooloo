package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "fragmentMain";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContentHolder, fragment)
                    .commit();
        }else {
            fragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContentHolder, fragment,FRAGMENT_TAG)
                    .commit();
        }

    }
}
