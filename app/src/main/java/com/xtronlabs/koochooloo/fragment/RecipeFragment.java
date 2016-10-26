package com.xtronlabs.koochooloo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.activity.RecipeActivity;
import com.xtronlabs.koochooloo.adapter.RecipeListAdapter;
import com.xtronlabs.koochooloo.util.network.request.GetRecipesForCountryRequest;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.util.network.response_models.Recipe;
import com.xtronlabs.koochooloo.util.network.response_models.Recipes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeFragment extends Fragment implements ProcessResponseInterface<Recipes> {

    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.imgBtnSound)
    ImageButton mImgBtnSound;
    @BindView(R.id.recipeList)
    RecyclerView mRecipeList;
    @BindView(R.id.imgBtnSettings)
    ImageButton mImgBottomLeft;
    @BindView(R.id.imgBtnFavorites)
    ImageButton mImgBottomRight;
    @BindView(R.id.lblSelectedCountry)
    TextView mLblSelectedCountry;
    private GeTAllRecipeProcessor mRecipeProcessor = new GeTAllRecipeProcessor();
    private RecipeListAdapter mRecipeListAdapter;


    public RecipeFragment() {
    }

    public static RecipeFragment newInstance(int countryId) {
        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putInt(RecipeActivity.COUNTRY_ID, countryId);
        recipeFragment.setArguments(args);
        return recipeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle b = getArguments();
        if (b != null) {
            int countryId = b.getInt(RecipeActivity.COUNTRY_ID, 0);
            if (countryId <= 0) return;
            new GetRecipesForCountryRequest(getActivity(),this,countryId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBtnSettings, R.id.imgBtnFavorites})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                break;
            case R.id.imgBtnSound:
                break;
            case R.id.imgBtnSettings:
                break;
            case R.id.imgBtnFavorites:
                break;
        }
    }

    @Override
    public void processResponse(Recipes response) {
        if (response == null) return;
        List<Recipe> recipeList = response.recipes;
        if (recipeList == null) return;

        mRecipeListAdapter = new RecipeListAdapter(recipeList, getActivity());
        mRecipeList.setAdapter(mRecipeListAdapter);
        //mRecipeList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecipeList.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false));
    }

    class GeTAllRecipeProcessor implements ProcessResponseInterface<Recipes> {

        @Override
        public void processResponse(Recipes response) {
            if (mRecipeListAdapter == null)
                mRecipeListAdapter = new RecipeListAdapter(response.recipes, getActivity());
            mRecipeList.setAdapter(mRecipeListAdapter);
            mRecipeList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

}
