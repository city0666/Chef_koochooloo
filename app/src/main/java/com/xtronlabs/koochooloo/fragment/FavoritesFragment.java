package com.xtronlabs.koochooloo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtronlabs.koochooloo.R;

/**
 * Created by Xtron005 on 06-09-2016.
 */
public class FavoritesFragment extends Fragment {


    public FavoritesFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_two_favourites,container,false);
    }
}
