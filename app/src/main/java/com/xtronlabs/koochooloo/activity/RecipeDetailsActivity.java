package com.xtronlabs.koochooloo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.RecipeDetailsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder,RecipeDetailsFragment.newInstance())
                .commit();
    }
}
