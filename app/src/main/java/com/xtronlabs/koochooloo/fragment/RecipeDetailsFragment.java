package com.xtronlabs.koochooloo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.activity.MainActivity;
import com.xtronlabs.koochooloo.activity.RecipeDetailsActivity;
import com.xtronlabs.koochooloo.activity.RecipeStepsActivity;
import com.xtronlabs.koochooloo.adapter.IngredientListAdapter;
import com.xtronlabs.koochooloo.util.network.request.GetRecipeDetailsForId;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeImageNew;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeNew;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeStepNew;
import com.xtronlabs.koochooloo.util.sound.MusicManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecipeDetailsFragment extends Fragment implements ProcessResponseInterface<RecipeNew> {


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
    @BindView(R.id.imgBtnBack)
    ImageButton mImgBtnBack;
    @BindView(R.id.imgBtnForward)
    ImageButton mImgBtnForward;

    private String mTitle;
    private int mId;
    private ArrayList<RecipeStepNew> mRecipeSteps;
    private List<RecipeImageNew> mImages;
    private int index = 0;

    public RecipeDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) return;

        mTitle = args.getString(RecipeDetailsActivity.TITLE);
        mId = args.getInt(RecipeDetailsActivity.ID);
        new GetRecipeDetailsForId(getActivity(), this, mId);
    }

    public static RecipeDetailsFragment newInstance(int id, String title) {
        RecipeDetailsFragment instance = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(RecipeDetailsActivity.TITLE, title);
        args.putInt(RecipeDetailsActivity.ID, id);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);
        mLblSelectedRecipe.setText(mTitle);
        return view;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBtnRecipeImageLeft, R.id.imgBtnRecipeImageRight, R.id.imgBtnBack, R.id.imgBtnForward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe: {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
            break;
            case R.id.imgBtnSound: {
                if (MusicManager.isPaused()) {
                    MusicManager.start(getActivity(), R.raw.theme_song);
                    mImgBtnSound.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_un_mute));
                } else {
                    MusicManager.pause();
                    mImgBtnSound.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_mute));
                }
            }
            break;
            case R.id.imgBtnRecipeImageLeft:
                switchImage(true);
                break;
            case R.id.imgBtnRecipeImageRight:
                switchImage(false);
                break;
            case R.id.imgBtnBack:
                getActivity().onBackPressed();
                break;
            case R.id.imgBtnForward: {
                Intent recipeStepsIntent = new Intent(getActivity(), RecipeStepsActivity.class);
                recipeStepsIntent.putParcelableArrayListExtra(RecipeStepsActivity.RECIPE_STEPS, mRecipeSteps);
                startActivity(recipeStepsIntent);
            }
            break;
        }
    }

    @Override
    public void processResponse(RecipeNew response) {

        if (response == null)
            return;
        mRecipeSteps = new ArrayList<>(response.recipe.recipeSteps);
        IngredientListAdapter adapter = new IngredientListAdapter(response.recipe.recipeIngredients, getActivity());
        mIngredientsList.setAdapter(adapter);
        mIngredientsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mImages = response.recipe.recipeImages;
        if (mImages == null) return;
        index = 0;
        switchImage(true);
    }

    private void switchImage(boolean goLeft) {
        if (goLeft) {
            if (index > 0) --index;
        } else {
            if (index < mImages.size() - 1) ++index;
        }

        if (index == 0) {
            mImgBtnRecipeImageLeft.setEnabled(false);
            mImgBtnRecipeImageLeft.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_left_disabled));
        } else {
            mImgBtnRecipeImageLeft.setEnabled(true);
            mImgBtnRecipeImageLeft.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_left_enabled));
        }

        if (index == mImages.size() - 1) {
            mImgBtnRecipeImageRight.setEnabled(false);
            mImgBtnRecipeImageRight.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_right_disabled));
        } else {
            mImgBtnRecipeImageRight.setEnabled(true);
            mImgBtnRecipeImageRight.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_right_enabled));
        }

        Glide.with(getActivity())
                .load(mImages.get(index).image)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        mImgRecipeImage.setImageDrawable(resource);
                    }
                });

    }

}
