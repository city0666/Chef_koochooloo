package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.RecipeFragment;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

public class RecipeActivity extends BaseActivity {

    public static final String COUNTRY_ID = "countryID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipees);
        Intent callingIntent = getIntent();
        int countryId=0;
        if (callingIntent != null) countryId = callingIntent.getIntExtra(COUNTRY_ID,0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, RecipeFragment.newInstance(countryId))
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
