package com.xtronlabs.koochooloo;

import android.app.Application;

/**
 * Created by user on 18/10/16.
 */

public class KoochoolooApp extends Application {

    private static MDService mBackgroundMusicService;

    public static void setBackgroundMusicService(MDService service){
        mBackgroundMusicService = service;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }




}
