package com.xtronlabs.koochooloo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.CountryFragment;

public class CountryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, new CountryFragment(),CountryFragment.TAG)
                .commit();
    }
}
