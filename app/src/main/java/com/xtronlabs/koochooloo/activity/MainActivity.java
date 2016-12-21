package com.xtronlabs.koochooloo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.HomeFragment;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

public class MainActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = "fragmentMain";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContentHolder, fragment)
                    .commit();
        } else {
            fragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContentHolder, fragment, FRAGMENT_TAG)
                    .commit();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
            KoochoolooApp.playMusic = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = false;
        if (!KoochoolooApp.playMusic) return;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
    }


}
