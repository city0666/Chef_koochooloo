package com.xtronlabs.koochooloo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.adapter.RecipeListAdapter;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.util.network.response_models.Recipes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeFragment extends Fragment {

    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.imgBtnSound)
    ImageButton mImgBtnSound;
    @BindView(R.id.recipeList)
    RecyclerView mRecipeList;
    @BindView(R.id.imgBottomLeft)
    ImageButton mImgBottomLeft;
    @BindView(R.id.imgBottomRight)
    ImageButton mImgBottomRight;
    @BindView(R.id.lblSelectedCountry)
    TextView mLblSelectedCountry;
    private GeTAllRecipeProcessor mRecipeProcessor = new GeTAllRecipeProcessor();
    private RecipeListAdapter mRecipeListAdapter;

    public RecipeFragment() {
    }

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBottomLeft, R.id.imgBottomRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                break;
            case R.id.imgBtnSound:
                break;
            case R.id.imgBottomLeft:
                break;
            case R.id.imgBottomRight:
                break;
        }
    }

    class GeTAllRecipeProcessor implements ProcessResponseInterface<Recipes> {

        @Override
        public void processResponse(Recipes response) {
            if (mRecipeListAdapter == null)
                mRecipeListAdapter = new RecipeListAdapter(response.recipes, getActivity());
            mRecipeList.setAdapter(mRecipeListAdapter);
            mRecipeList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        }
    }

}
