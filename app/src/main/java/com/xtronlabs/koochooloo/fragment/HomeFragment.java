package com.xtronlabs.koochooloo.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mousebird.maply.AttrDictionary;
import com.mousebird.maply.GlobeController;
import com.mousebird.maply.MBTiles;
import com.mousebird.maply.MBTilesImageSource;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.Point3d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.SelectedObject;
import com.mousebird.maply.VectorInfo;
import com.mousebird.maply.VectorObject;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.activity.FavoritesActivity;
import com.xtronlabs.koochooloo.activity.RecipeActivity;
import com.xtronlabs.koochooloo.adapter.CountryListAdapter;
import com.xtronlabs.koochooloo.util.M;
import com.xtronlabs.koochooloo.util.network.NetworkManager;
import com.xtronlabs.koochooloo.util.network.request.GetCountriesRequest;
import com.xtronlabs.koochooloo.util.network.request.GetFactsForCountryRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Countries;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.Facts;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;
import com.xtronlabs.koochooloo.view.KoochoolooLabel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements ProcessResponseInterface<Countries>,
        CountryListAdapter.IShowCountry, TextView.OnEditorActionListener {

    private static final String MB_TILES_DIR = "mbtiles";
    private static final String JSON_DIR = "country_json_50m";
    @BindView(R.id.imgBtnGlobe)
    ImageButton mImgBtnGlobe;
    @BindView(R.id.imgBtnSound)
    ImageButton mImgBtnSound;
    @BindView(R.id.imgBottomLeft)
    ImageButton mImgBottomLeft;
    @BindView(R.id.imgBottomRight)
    ImageButton mImgBottomRight;
    @BindView(R.id.globeHolder)
    RelativeLayout mGlobeHolder;
    @BindView(R.id.lblSelectedCountry)
    TextView mLblSelectedCountry;
    @BindView(R.id.txtSearch)
    EditText mTxtSearch;
    @BindView(R.id.countryList)
    RecyclerView mCountriesList;
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

    /*private ViewGroup mCustomListHolder;
    private RecyclerView mCustomListScrollView;
    private ImageButton mBtnCustomListClose;
    */


    private CountryListAdapter mCountryListAdapter;

    private GlobeController mGlobeController;
    private GlobeController.GestureDelegate mGestureDelegete =
            new GlobeController.GestureDelegate() {
                @Override
                public void userDidSelect(GlobeController globeController, SelectedObject[] selectedObjects,
                                          Point2d point2d, Point2d point2d1) {
                    hideDrawerAndPopUp();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showRecipeActivity();
                        }
                    }, 600);
                }

                @Override
                public void userDidTap(GlobeController globeController, Point2d point2d, Point2d point2d1) {
                    hideDrawerAndPopUp();
                }

                @Override
                public void userDidLongPress(GlobeController globeController, SelectedObject[] selectedObjects,
                                             Point2d point2d, Point2d point2d1) {
                }

                @Override
                public void globeDidStartMoving(GlobeController globeController, boolean b) {
                }

                @Override
                public void globeDidStopMoving(GlobeController globeController, Point3d[] point3ds, boolean b) {
                }

                @Override
                public void globeDidMove(GlobeController globeController, Point3d[] point3ds, boolean b) {

                }
            };

    private void hideDrawerAndPopUp() {
        if (isDrawerOpen) animateDrawerClose();
        if (isPopUpOpen) animatePopUpClose();
    }

    private boolean isDrawerOpen = false;
    private boolean isPopUpOpen = false;
    private AlertDialog mNoNetworkDialog;
    private String mFact;
    private Country mCountry;
    private FactsResponseProcessor mFactsResponseProcessor = new FactsResponseProcessor();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        checkNetworkAndCallForCountryList();
        M.log("CALL", "Get country request send");
    }

    public HomeFragment() {
        //Required public empty constructor
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTxtSearch.setVisibility(View.GONE);
        GlobeController.Settings settings = new GlobeController.Settings();
        settings.useSurfaceView = false;
        int color = ContextCompat.getColor(getActivity(), android.R.color.transparent);
        settings.clearColor = color;
        mGlobeController = new GlobeController(getActivity(), settings);
        mGlobeController.gestureDelegate = mGestureDelegete;
        ViewGroup holder = (ViewGroup) view.findViewById(R.id.globeHolder);
        if (holder != null) {
            View globe = mGlobeController.getContentView();

            int margin = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);

            DisplayMetrics dm = getResources().getDisplayMetrics();
            int height = dm.heightPixels;
            int width = dm.widthPixels;
            int min = Math.min(height, width);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(min, min);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            globe.setLayoutParams(lp);
            globe.setPadding(8, 8, 8, 8);
            holder.addView(globe);
        }

        mGlobeController.setZoomLimits(1,1);
        ViewGroup parent = (ViewGroup) mImgBtnGlobe.getParent();
        int index = parent.indexOfChild(mCustomListHolder);
        parent.bringChildToFront(parent.getChildAt(index));
        index = parent.indexOfChild(mImgBtnGlobe);
        parent.bringChildToFront(parent.getChildAt(index));
        index = parent.indexOfChild(mTxtSearch);
        parent.bringChildToFront(parent.getChildAt(index));
        //mGlobeController.getGlobeView().currentMapScale(0.9f, 0.9f);
        //mGlobeController.setZoomLimits(0.5f, 0.5f);

        if (mTxtSearch != null) {
            mTxtSearch.setOnEditorActionListener(this);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGlobeController.addPostSurfaceRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    setupGlobe(mGlobeController);
                } catch (Exception e) {
                    //Ignore for now
                }
            }
        });
        mGlobeController.getGlobeView().animate();
    }

    private void setupGlobe(GlobeController globeController) throws Exception {
        globeController.addLayer(setUpImageLayer(globeController));
    }

    private QuadImageTileLayer setUpImageLayer(MaplyBaseController baseController) throws Exception {
        File mbTiles = getAssetFile("mbtiles/geography-class_medres.mbtiles",
                "geography-class_medres.mbtiles");

        MBTiles mbTilesFile = new MBTiles(mbTiles);
        MBTilesImageSource tilesImageSource = new MBTilesImageSource(mbTilesFile);
        QuadImageTileLayer imageTileLayer = new QuadImageTileLayer(baseController,
                tilesImageSource.coordSys, tilesImageSource);
        imageTileLayer.setCoverPoles(true);
        imageTileLayer.setHandleEdges(true);
        return imageTileLayer;
    }

    private File getAssetFile(String assetPath, String assetFileName) throws IOException {
        ContextWrapper wrapper = new ContextWrapper(getActivity());
        File mbTilesDirectory = wrapper.getDir(MB_TILES_DIR, Context.MODE_PRIVATE);

        InputStream inputStream = getActivity().getAssets().open(assetPath);
        File file = new File(mbTilesDirectory, assetFileName);

        if (file.exists()) return file;

        OutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];

        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();

        inputStream.close();
        return file;
    }

    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBottomLeft, R.id.imgBottomRight
            , R.id.imgBtnCustomListClose, R.id.imgPopUpClose})
    public void onClick(View view) {

        if (isPopUpOpen) animatePopUpClose();
        if (isDrawerOpen) animateDrawerClose();

        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                toggleDrawerAndSearch();
                break;
            case R.id.imgBtnSound:
                manageSound();
                break;
            case R.id.imgBottomLeft:
                handleLeftNavigation();
                break;
            case R.id.imgBottomRight:
                handleRightNavigation();
                break;
            case R.id.imgBtnCustomListClose:
                animateDrawerClose();
                break;
            case R.id.imgPopUpClose:
                animatePopUpClose();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showRecipeActivity();
                    }
                }, 600);
                break;
        }
    }

    private void showRecipeActivity() {
        int countryId = 0;
        if (mCountry != null) countryId = mCountry.id;
        Intent recipeIntent = new Intent(getActivity(), RecipeActivity.class);
        recipeIntent.putExtra(RecipeActivity.COUNTRY_ID, countryId);
        M.log("cid",mCountry.id+"");
        startActivity(recipeIntent);
    }

    private void manageSound() {

    }

    private void handleLeftNavigation() {
        startActivity(new Intent(getActivity(), FavoritesActivity.class));
    }

    private void handleRightNavigation() {

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

    @Override
    public void processResponse(Countries response) {
        M.log("CALL", "Response from get countries request : response status = "
                + response == null ? "NULL" : "NOT NULL");
        if (response == null) return;

        M.log("CALL", "Response from get countries request : response status = " + response.countries.size());
        mCountryListAdapter = new CountryListAdapter(response.countries, getActivity(), this);
        mCountriesList.setAdapter(mCountryListAdapter);
        mCountriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCountryListAdapter.notifyDataSetChanged();
    }

    private void checkNetworkAndCallForCountryList() {
        if (NetworkManager.isNetworkAvailable(getActivity()))
            new GetCountriesRequest(getActivity(), this);
        else {
            mNoNetworkDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.app_name))
                    .setMessage("Network Unavailable")
                    .setCancelable(false)
                    .setPositiveButton("RETRY", null)
                    .create();

            mNoNetworkDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Button b = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    if (b != null) {
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (NetworkManager.isNetworkAvailable(getActivity())) {
                                    if (mNoNetworkDialog != null)
                                        mNoNetworkDialog.dismiss();
                                    checkNetworkAndCallForCountryList();
                                }
                            }
                        });
                    }
                }
            });
            mNoNetworkDialog.show();
        }
    }

    @Override
    public void showCountry(Country country) {
        if (country == null) return;
        if (mCountry == null) {
            new GetFactsForCountryRequest(getActivity(), mFactsResponseProcessor, country.id);
        } else if (!mCountry.id.equals(country.id)) {
            new GetFactsForCountryRequest(getActivity(), mFactsResponseProcessor, country.id);
        }
        mCountry = country;
        if (!isDrawerOpen)
            animateDrawerClose();

        if (!isPopUpOpen)
            animatePopUpOpen();

        new LoadCountryAsync().execute(country);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            findCountryAndScrollToPosition(v.getText().toString());
            return true;
        }
        return false;
    }

    public void findCountryAndScrollToPosition(String country) {
        if (mCountryListAdapter == null) return;

        List<Country> countries = mCountryListAdapter.getmCountries();
        if (countries == null) return;
        for (int i = 0; i < countries.size(); i++) {
            Country c = countries.get(i);
            String lower = c.name.toLowerCase();
            if (lower.startsWith(country.toLowerCase())) {
                if (mCountriesList == null) return;
                mCountriesList.smoothScrollToPosition(i);
                break;
            }
        }

    }

    private class LoadCountryAsync extends AsyncTask<Country, Void, VectorObject> {


        VectorInfo vectorInfo = new VectorInfo();

        public LoadCountryAsync() {
            vectorInfo.setColor(R.color.colorAccent);
        }

        @Override
        protected VectorObject doInBackground(Country... params) {
            try {
                Country c = params[0];
                String fileName = c.code;

                InputStream is = getActivity().getAssets().open(JSON_DIR + "/" + fileName + ".geojson");
                int size = is.available();
                byte[] contents = new byte[size];
                is.read(contents);
                String json = new String(contents, "UTF-8");
                if (json != null) {
                    VectorObject vectorObject = new VectorObject();
                    vectorObject.fromGeoJSON(json);
                    //vectorObject.selectable=true;
                    return vectorObject;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(VectorObject vectorObject) {
            super.onPostExecute(vectorObject);
            AttrDictionary x = vectorObject.getAttributes();
            vectorInfo.setLineWidth(3f);
            vectorObject.selectable = true;
            mGlobeController.addVector(vectorObject, vectorInfo, MaplyBaseController.ThreadMode.ThreadAny);
        }
    }

    private class FactsResponseProcessor implements ProcessResponseInterface<Facts> {

        @Override
        public void processResponse(Facts response) {
            if (response == null) return;
            if (!isPopUpOpen) animatePopUpOpen();
            if (mPopUpHolder == null) return;
            if (mLblPopUpBubble == null) return;
            if(response.getFacts().size()<=0) return;
            mLblPopUpBubble.setText(response.getFacts().get(0).getFact());
        }
    }


}
