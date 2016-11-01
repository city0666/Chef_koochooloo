package com.xtronlabs.koochooloo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.activity.MainActivity;
import com.xtronlabs.koochooloo.activity.RecipeStepsActivity;
import com.xtronlabs.koochooloo.adapter.RecipeStepsAdapter;
import com.xtronlabs.koochooloo.util.network.response_models.ResponseModelsNew.RecipeStepNew;
import com.xtronlabs.koochooloo.util.sound.MusicManager;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sreerajk.s on 31/10/16.
 */
public class FragmentRecipeSteps extends BaseFragment {

    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.lblSelectedRecipe)
    KoochoolooLabel mLblSelectedRecipe;
    @BindView(R.id.imgBtnSound)
    ImageButton mImgBtnSound;
    @BindView(R.id.imgBtnBack)
    ImageButton mImgBtnBack;
    @BindView(R.id.imgBtnForward)
    ImageButton mImgBtnForward;
    @BindView(R.id.lstRecipeSteps)
    RecyclerView mLstRecipeSteps;
    private ArrayList<RecipeStepNew> mRecipeSteps;

    public static final String TAG = FragmentRecipeSteps.class.getName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) return;

        mRecipeSteps = args.getParcelableArrayList(RecipeStepsActivity.RECIPE_STEPS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        ButterKnife.bind(this, view);
        if (mRecipeSteps == null) return view;
        RecipeStepsAdapter adapter = new RecipeStepsAdapter(mRecipeSteps,getActivity());
        mLstRecipeSteps.setAdapter(adapter);
        mLstRecipeSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static FragmentRecipeSteps newInstance(ArrayList<RecipeStepNew> recipeSteps) {
        FragmentRecipeSteps instance = new FragmentRecipeSteps();
        Bundle args = new Bundle();
        args.putParcelableArrayList(RecipeStepsActivity.RECIPE_STEPS, recipeSteps);
        instance.setArguments(args);
        return instance;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBtnBack, R.id.imgBtnForward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:{
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
                break;
            case R.id.imgBtnSound:{
                if (MusicManager.isPaused()) {
                    MusicManager.start(getActivity(), R.raw.theme_song);
                    mImgBtnSound.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_un_mute));
                } else {
                    MusicManager.pause();
                    mImgBtnSound.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_mute));
                }
            }
                break;
            case R.id.imgBtnBack:
                getActivity().onBackPressed();
                break;
            case R.id.imgBtnForward:
                break;
        }
    }
}
