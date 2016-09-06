package com.xtronlabs.koochooloo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.RecipeFragment;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipees);
        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, RecipeFragment.newInstance())
                .commit();
    }
}
