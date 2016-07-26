package com.xtronlabs.koochooloo.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mousebird.maply.GlobeController;
import com.mousebird.maply.MBTiles;
import com.mousebird.maply.MBTilesImageSource;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.Point3d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.SelectedObject;
import com.xtronlabs.koochooloo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment {

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

    private ViewGroup mCustomListHolder;
    private ScrollView mCustomListScrollView;
    private LinearLayout mCustomListItemsHolder;
    private ImageButton mBtnCustomListClose;


    private GlobeController mGlobeController;
    private GlobeController.GestureDelegate mGestureDelegete = new GlobeController.GestureDelegate() {
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
            lp.setMargins(margin, margin, margin, margin);

            globe.setLayoutParams(lp);
            holder.addView(globe);

        }
        View v = getActivity().getLayoutInflater().inflate(R.layout.include_custom_list, null, false);
        if (v != null) {
            mCustomListHolder = (ViewGroup) v.findViewById(R.id.customListHolder);
            mCustomListScrollView = (ScrollView) v.findViewById(R.id.scrollView);
            mCustomListItemsHolder = (LinearLayout) v.findViewById(R.id.ltCustonListItemsHolder);
            mBtnCustomListClose = (ImageButton) v.findViewById(R.id.imgBtnCustomListClose);

            if (mBtnCustomListClose != null) {
                mBtnCustomListClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animateDrawerClose();
                    }
                });
            }
            v.setVisibility(View.GONE);
            mGlobeHolder.addView(v);
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


    @OnClick({R.id.imgBtnGlobe, R.id.imgBtnSound, R.id.imgBottomLeft, R.id.imgBottomRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnGlobe:
                animateDrawerOpen();
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
        }
    }

    private void manageSound() {

    }

    private void handleLeftNavigation() {

    }

    private void handleRightNavigation() {

    }

    private void animateDrawerOpen() {
        if (mCustomListHolder == null) return;
        mCustomListHolder.setVisibility(View.VISIBLE);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(mCustomListHolder, "TranslationX",
                -mCustomListHolder.getWidth(), 0)
                .setDuration(600);
        translateX.start();
    }

    private void animateDrawerClose() {
        ObjectAnimator translateX = ObjectAnimator.ofFloat(mCustomListHolder, "TranslationX",
                0, -mCustomListHolder.getWidth())
                .setDuration(400);
        translateX.start();
    }
}
