package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.CountryFragment;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

public class CountryDetailsActivity extends BaseActivity {


    public static final String COUNTRY_FLAG_LINK = "link";
    public static final String COUNTRY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent callingIntent = getIntent();
        if (callingIntent == null)
            throw new NullPointerException("No intent or data found to select country");

        String link = callingIntent.getStringExtra(COUNTRY_FLAG_LINK);
        int id = callingIntent.getIntExtra(RecipeActivity.COUNTRY_ID, 0);
        String countryName = callingIntent.getStringExtra(COUNTRY_NAME);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, CountryFragment.newInstance(id, link, countryName), CountryFragment.TAG)
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
        if (!KoochoolooApp.playMusic) return;
        continueMusic = true;
        MusicManager.start(this, R.raw.theme_song);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
            KoochoolooApp.playMusic = false;
        }
    }


}
