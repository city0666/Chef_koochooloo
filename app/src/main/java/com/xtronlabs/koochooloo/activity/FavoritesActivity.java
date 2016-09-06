package com.xtronlabs.koochooloo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.FavoritesFragment;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        getFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, new FavoritesFragment())
                .commit();
    }
}
