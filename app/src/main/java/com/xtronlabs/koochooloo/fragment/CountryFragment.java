package com.xtronlabs.koochooloo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.activity.CountryDetailsActivity;
import com.xtronlabs.koochooloo.activity.MainActivity;
import com.xtronlabs.koochooloo.activity.RecipeActivity;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CountryFragment extends Fragment {

    public static final String TAG = CountryFragment.class.getName();
    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.lblCountryDetailsCountryName)
    KoochoolooLabel mLblCountryDetailsCountryName;
    @BindView(R.id.imgBtnCountryDetailsOk)
    ImageButton mImgBtnCountryDetailsOk;
    @BindView(R.id.imgCountryDetailsCountryImage)
    ImageView mImgCountryDetailsCountryImage;
    @BindView(R.id.imgBtnCountryDetailsBack)
    ImageButton mImgBtnCountryDetailsBack;
    @BindView(R.id.imgBtnCountryDetailsForward)
    ImageButton mImgBtnCountryDetailsForward;
    @BindView(R.id.lblCountryDetailsCountryDetails)
    KoochoolooLabel mLblCountryDetailsCountryDetails;


    private int mCountryId;
    private String mCountryName;
    private String mCountryFlag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) return;
        mCountryId = args.getInt(RecipeActivity.COUNTRY_ID);
        mCountryName = args.getString(CountryDetailsActivity.COUNTRY_NAME);
        mCountryFlag = args.getString(CountryDetailsActivity.COUNTRY_FLAG_LINK);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_details, container, false);
        ButterKnife.bind(this, view);
        mLblCountryDetailsCountryName.setText(mCountryName);
        Glide.with(getActivity())
                .load(mCountryFlag)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        mImgCountryDetailsCountryImage.setImageDrawable(resource);
                    }
                });
        return view;
    }


    public static CountryFragment newInstance(int countryId, String countryFlagLink, String countryName) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putInt(RecipeActivity.COUNTRY_ID, countryId);
        args.putString(CountryDetailsActivity.COUNTRY_FLAG_LINK, countryFlagLink);
        args.putString(CountryDetailsActivity.COUNTRY_NAME, countryName);
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnCountryDetailsOk, R.id.imgBtnCountryDetailsBack, R.id.imgBtnCountryDetailsForward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case R.id.imgBtnCountryDetailsOk: {
                startRecipeActivity();
            }
            break;
            case R.id.imgBtnCountryDetailsBack:
                getActivity().onBackPressed();
                break;
            case R.id.imgBtnCountryDetailsForward:
                startRecipeActivity();
                break;
        }
    }

    private void startRecipeActivity() {
        Intent recipeIntent = new Intent(getActivity(), RecipeActivity.class);
        recipeIntent.putExtra(RecipeActivity.COUNTRY_ID, mCountryId);
        startActivity(recipeIntent);
    }
}
