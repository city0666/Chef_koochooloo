package com.xtronlabs.koochooloo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtronlabs.koochooloo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecipeDetailsFragment extends Fragment {


    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.lblSelectedRecipe)
    TextView mLblSelectedRecipe;
    @BindView(R.id.imgBtnSound)
    ImageButton mImgBtnSound;
    @BindView(R.id.ingredientsList)
    RecyclerView mIngredientsList;
    @BindView(R.id.imgRecipeImage)
    ImageView mImgRecipeImage;
    @BindView(R.id.imgBtnRecipeImageLeft)
    ImageButton mImgBtnRecipeImageLeft;
    @BindView(R.id.imgBtnRecipeImageRight)
    ImageButton mImgBtnRecipeImageRight;
    @BindView(R.id.imageHolder)
    LinearLayout mImageHolder;
    @BindView(R.id.ingredientToolsList)
    RecyclerView mIngredientToolsList;
    @BindView(R.id.toolsHolder)
    FrameLayout mToolsHolder;
    @BindView(R.id.imgBtnSettings)
    ImageButton mImgBottomLeft;
    @BindView(R.id.imgBtnFavorites)
    ImageButton mImgBottomRight;

    public RecipeDetailsFragment() {
    }


    public static RecipeDetailsFragment newInstance() {
        return new RecipeDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBtnRecipeImageLeft,
            R.id.imgBtnRecipeImageRight, R.id.imgBtnSettings, R.id.imgBtnFavorites})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                break;
            case R.id.imgBtnSound:
                break;
            case R.id.imgBtnRecipeImageLeft:
                break;
            case R.id.imgBtnRecipeImageRight:
                break;
            case R.id.imgBtnSettings:
                break;
            case R.id.imgBtnFavorites:
                break;
        }
    }
}
