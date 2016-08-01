package com.xtronlabs.koochooloo.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mousebird.maply.GlobeController;
import com.mousebird.maply.MBTiles;
import com.mousebird.maply.MBTilesImageSource;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.Point3d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.SelectedObject;
import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.adapter.CountryListAdapter;
import com.xtronlabs.koochooloo.util.M;
import com.xtronlabs.koochooloo.util.network.NetworkManager;
import com.xtronlabs.koochooloo.util.network.request.GetCountriesRequest;
import com.xtronlabs.koochooloo.util.network.response_models.Country;
import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements ProcessResponseInterface<Country[]> {

    private static final String MB_TILES_DIR = "mbtiles";
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

    /*private ViewGroup mCustomListHolder;
    private RecyclerView mCustomListScrollView;
    private ImageButton mBtnCustomListClose;
    */

    private GlobeController mGlobeController;
    private GlobeController.GestureDelegate mGestureDelegete =
            new GlobeController.GestureDelegate() {
                @Override
                public void userDidSelect(GlobeController globeController, SelectedObject[] selectedObjects,
                                          Point2d point2d, Point2d point2d1) {

                }

                @Override
                public void userDidTap(GlobeController globeController, Point2d point2d, Point2d point2d1) {

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
    private boolean isDrawerOpen = false;
    private AlertDialog mNoNetworkDialog;


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

        ViewGroup parent = (ViewGroup) mImgBtnGlobe.getParent();
        int index = parent.indexOfChild(mCustomListHolder);
        parent.bringChildToFront(parent.getChildAt(index));
        index = parent.indexOfChild(mTxtSearch);
        parent.bringChildToFront(parent.getChildAt(index));
        index = parent.indexOfChild(mImgBtnGlobe);
        parent.bringChildToFront(parent.getChildAt(index));
        //mGlobeController.animatePositionGeo(-3.6704803, 40.5023056, 5, 1.0);
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


        //new GetCountriesRequest(getActivity(), this);
        checkNetworkAndCallForCountryList();
        M.log("CALL", "Get country request send");
    }

    private void setupGlobe(GlobeController globeController) throws Exception {
        globeController.addLayer(setUpImageLayer(globeController));
    }

    private QuadImageTileLayer setUpImageLayer(MaplyBaseController baseController) throws Exception {
        File mbTiles = getMbTileFile("mbtiles/geography-class_medres.mbtiles",
                "geography-class_medres.mbtiles");

        MBTiles mbTilesFile = new MBTiles(mbTiles);
        MBTilesImageSource tilesImageSource = new MBTilesImageSource(mbTilesFile);
        QuadImageTileLayer imageTileLayer = new QuadImageTileLayer(baseController,
                tilesImageSource.coordSys, tilesImageSource);
        imageTileLayer.setCoverPoles(true);
        imageTileLayer.setHandleEdges(true);
        return imageTileLayer;
    }

    private File getMbTileFile(String assetPath, String assetFileName) throws IOException {
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
            , R.id.imgBtnCustomListClose})
    public void onClick(View view) {
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
        }
    }

    private void manageSound() {

    }

    private void handleLeftNavigation() {

    }

    private void handleRightNavigation() {

    }

    private void toggleDrawerAndSearch() {
        if (isDrawerOpen) animateDrawerClose();
        else animateDrawerOpen();
    }

    private void animateDrawerOpen() {
        if (mCustomListHolder == null) return;
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
        ObjectAnimator.ofFloat(mCustomListHolder, "TranslationX",
                0, -mCustomListHolder.getWidth())
                .setDuration(400)
                .start();
        isDrawerOpen = false;
        ObjectAnimator.ofFloat(mTxtSearch, "TranslationX", 0, -mCustomListHolder.getWidth())
                .setDuration(600)
                .start();
    }

    @Override
    public void processResponse(Country[] response) {
        M.log("CALL", "Response from get countries request : response status = "
                + response == null ? "NULL" : "NOT NULL");
        /*if (response == null) ;
        if (response.length <= 0) {
            response = new Country[10];
            for (int i = 0; i < 10; i++) {
                response[i].name = "Country " + i;
            }
        }*/

        response = new Country[10];
        for (int i = 0; i < 10; i++) {
            Country c = new Country();
            c.name = "Country " + i;
            response[i] = c;
        }


        M.log("CALL", "Response from get countries request : response status = " + response.length);
        CountryListAdapter adapter = new CountryListAdapter(response, getActivity());
        mCountriesList.setAdapter(adapter);
        mCountriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void checkNetworkAndCallForCountryList() {
        if (NetworkManager.isNetworkAvsilable(getActivity()))
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
                                if (NetworkManager.isNetworkAvsilable(getActivity())) {
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

}
