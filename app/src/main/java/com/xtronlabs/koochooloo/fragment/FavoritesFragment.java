package com.xtronlabs.koochooloo.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.adapter.CountryListAdapter;
import com.xtronlabs.koochooloo.util.network.response_models.Countries;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FavoritesFragment extends Fragment implements ProcessResponseInterface<Countries>,
        CountryListAdapter.IShowCountry {


    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.imgBtnSearch)
    ImageButton mImgBtnSearch;
    @BindView(R.id.favList)
    RecyclerView mFavList;
    @BindView(R.id.imgBtnSettings)
    ImageButton mImgBtnSettings;
    @BindView(R.id.txtSearch)
    EditText mTxtSearch;
    @BindView(R.id.countryList)
    RecyclerView mCountryList;
    @BindView(R.id.imgBtnCustomListClose)
    ImageButton mImgBtnCustomListClose;
    @BindView(R.id.customListHolder)
    RelativeLayout mCustomListHolder;
    @BindView(R.id.lblPopUpBubble)
    KoochoolooLabel mLblPopUpBubble;
    @BindView(R.id.imgPopUpKoochooloo)
    ImageView mImgPopUpKoochooloo;
    @BindView(R.id.imgPopUpClose)
    ImageButton mImgPopUpClose;
    @BindView(R.id.popUpHolder)
    RelativeLayout mPopUpHolder;
    @BindView(R.id.imgBtnFb)
    ImageButton mImgBtnFb;
    @BindView(R.id.imgBtnTwitter)
    ImageButton mImgBtnTwitter;
    @BindView(R.id.imgBtnGoogle)
    ImageButton mImgBtnGoogle;
    @BindView(R.id.ltSocialMediaContainer)
    LinearLayout mLtSocialMediaContainer;
    @BindView(R.id.iconBg)
    View mIconBg;
    @BindView(R.id.lblSettingsAllergens)
    KoochoolooLabel mLblSettingsAllergens;
    @BindView(R.id.lblSettingsTutorial)
    KoochoolooLabel mLblSettingsTutorial;
    @BindView(R.id.lblSettingsSupport)
    KoochoolooLabel mLblSettingsSupport;
    @BindView(R.id.lblSettingsAbout)
    KoochoolooLabel mLblSettingsAbout;
    @BindView(R.id.ltSettingsContainer)
    RelativeLayout mLtSettingsContainer;
    @BindView(R.id.space)
    Space mSpace;
    @BindView(R.id.btnFavBack)
    ImageButton mBtnFavBack;
    @BindView(R.id.contentLoadingProgress)
    ContentLoadingProgressBar mContentLoadingProgress;
    private boolean isDrawerOpen;
    private boolean isPopUpOpen;
    private boolean isSettingsOpen;

    private int screenHeight;

    private Country mCountry;

    public FavoritesFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
    }

    private void toggleDrawerAndSearch() {
        if (isDrawerOpen) animateDrawerClose();
        else animateDrawerOpen();
    }

    private void animateDrawerOpen() {
        if (mCustomListHolder == null) return;
        if (mImgBtnGlobe != null) mImgBtnGlobe.setVisibility(View.INVISIBLE);
        mCustomListHolder.setVisibility(View.VISIBLE);
        mTxtSearch.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mCustomListHolder, "TranslationX", -mCustomListHolder.getWidth(), 0)
                .setDuration(600)
                .start();
        isDrawerOpen = true;

        ObjectAnimator.ofFloat(mTxtSearch, "TranslationX", -mCustomListHolder.getWidth(), 0)
                .setDuration(600)
                .start();

    }

    private void animateDrawerClose() {

        if (mImgBtnGlobe != null) mImgBtnGlobe.setVisibility(View.VISIBLE);

        ObjectAnimator.ofFloat(mCustomListHolder, "TranslationX",
                0, -mCustomListHolder.getWidth())
                .setDuration(400)
                .start();
        isDrawerOpen = false;
        ObjectAnimator.ofFloat(mTxtSearch, "TranslationX", 0, -mCustomListHolder.getWidth())
                .setDuration(600)
                .start();
    }

    //needs clean up here(code repetition)
    private void animatePopUpOpen() {
        if (mPopUpHolder == null) return;
        animateDrawerClose();
        ViewGroup parent = (ViewGroup) mImgBtnGlobe.getParent();
        int index = parent.indexOfChild(mPopUpHolder);
        parent.bringChildToFront(parent.getChildAt(index));
        mPopUpHolder.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mPopUpHolder, "TranslationX", -mPopUpHolder.getWidth(), 0)
                .setDuration(600)
                .start();
        isPopUpOpen = true;

    }

    private void animatePopUpClose() {
        ObjectAnimator.ofFloat(mPopUpHolder, "TranslationX",
                0, -mPopUpHolder.getWidth())
                .setDuration(400)
                .start();
        isPopUpOpen = false;
    }

    private void animateSocialMediaIn() {
        if (mLtSocialMediaContainer == null) return;
        mLtSocialMediaContainer.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mLtSocialMediaContainer, "TranslationX", -mLtSocialMediaContainer.getWidth(), mImgBtnSettings.getWidth())
                .setDuration(600)
                .start();
    }

    private void animateSocialMediaOut() {
        if (mLtSocialMediaContainer == null) return;
        mLtSocialMediaContainer.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mLtSocialMediaContainer, "TranslationX", mImgBtnSettings.getWidth(), -mLtSocialMediaContainer.getWidth())
                .setDuration(600)
                .start();

    }

    private void animateSettingsIn() {
        if (mLtSettingsContainer == null) return;
        mLtSettingsContainer.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mLtSettingsContainer, "TranslationY", screenHeight, 0 - mImgBtnSettings.getHeight() / 2)
                .setDuration(600)
                .start();
    }

    private void animateSettingsOut() {
        if (mLtSettingsContainer == null) return;
        mLtSettingsContainer.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mLtSettingsContainer, "TranslationY", 0, screenHeight)
                .setDuration(600)
                .start();
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSearch, R.id.imgBtnSettings, R.id.imgBtnCustomListClose,
            R.id.imgPopUpClose, R.id.imgBtnFb, R.id.imgBtnTwitter, R.id.imgBtnGoogle,
            R.id.lblSettingsAllergens, R.id.lblSettingsTutorial, R.id.lblSettingsSupport, R.id.btnFavBack,
            R.id.lblSettingsAbout})
    public void onClick(View view) {

        if (isPopUpOpen) animatePopUpClose();
        if (isDrawerOpen) animateDrawerClose();

        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                toggleDrawerAndSearch();
                break;
            case R.id.imgBtnSearch:
                toggleDrawerAndSearch();
                break;
            case R.id.imgBtnSettings:
                toggleSettings();
                break;
            case R.id.imgBtnCustomListClose:
                animateDrawerClose();
                break;
            case R.id.imgPopUpClose:
                animatePopUpClose();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //showRecipeActivity();
                    }
                }, 600);
                break;
            case R.id.imgBtnFb:
                break;
            case R.id.imgBtnTwitter:
                break;
            case R.id.imgBtnGoogle:
                break;
            case R.id.lblSettingsAllergens:
                break;
            case R.id.lblSettingsTutorial:
                break;
            case R.id.lblSettingsSupport:
                break;
            case R.id.lblSettingsAbout:
                break;
            case R.id.btnFavBack:
                getActivity().onBackPressed();
                break;
        }
    }

    private void toggleSettings() {
        if (!isSettingsOpen) {
            animateSettingsIn();
            animateSocialMediaIn();
        } else {
            animateSettingsOut();
            animateSocialMediaOut();
        }
        isSettingsOpen = !isSettingsOpen;
    }

    @Override
    public void showCountry(Country country) {
        if (country == null) return;
        mCountry = country;
        if (!isDrawerOpen)
            animateDrawerClose();

        if (!isPopUpOpen)
            animatePopUpOpen();
    }

    @Override
    public void processResponse(Countries response) {

    }


    @OnClick()
    public void onClick() {
    }
}
