package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.RecipeDetailsFragment;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

public class RecipeDetailsActivity extends BaseActivity {

    public static final String ID ="id";
    public static final String TITLE ="id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        String title=null;
        int id=0;
        Intent callingIntent = getIntent();
        if(callingIntent != null){
            title = callingIntent.getStringExtra(TITLE);
            id = callingIntent.getIntExtra(ID,0);
        }


        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, RecipeDetailsFragment.newInstance(id,title))
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
