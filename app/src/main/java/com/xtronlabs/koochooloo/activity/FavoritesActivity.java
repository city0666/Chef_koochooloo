package com.xtronlabs.koochooloo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.FavoritesFragment;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

public class FavoritesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, new FavoritesFragment())
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
