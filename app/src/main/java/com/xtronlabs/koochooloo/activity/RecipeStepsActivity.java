package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.FragmentRecipeSteps;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeStepNew;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

import java.util.ArrayList;

public class RecipeStepsActivity extends BaseActivity {

    public static final String RECIPE_STEPS ="steps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        Intent callingIntent = getIntent();
        ArrayList<RecipeStepNew> recipeSteps = callingIntent.getParcelableArrayListExtra(RECIPE_STEPS);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, FragmentRecipeSteps.newInstance(recipeSteps),FragmentRecipeSteps.TAG)
                .commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            continueMusic = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = true;
        MusicManager.start(this, R.raw.theme_song);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic)
            MusicManager.pause();
    }


}
