package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.RecipeFragment;

public class RecipeActivity extends AppCompatActivity {

    public static final String COUNTRY_ID = "countryID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipees);
        Intent callingIntent = getIntent();
        int countryId=0;
        if (callingIntent != null) countryId = callingIntent.getIntExtra(COUNTRY_ID,0);
        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, RecipeFragment.newInstance(countryId))
                .commit();
    }
}
