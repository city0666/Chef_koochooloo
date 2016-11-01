package com.xtronlabs.koochooloo.util.sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.util.Log;

import com.xtronlabs.koochooloo.R;

import java.util.Collection;
import java.util.HashMap;

public class MusicManager {

    private static final String TAG = "MusicManager";
    public static final int MUSIC_PREVIOUS = -1;
    public static final int MUSIC_MENU = 0;
    public static final int MUSIC_GAME = 1;
    public static final int MUSIC_END_GAME = 2;
    private static final int PREF_DEFAULT_MUSIC_VOLUME_ITEM = 8;

    private static boolean isPaused;


    private static HashMap players = new HashMap();
    private static int currentMusic = -1;
    private static int previousMusic = -1;

    public static float getMusicVolume(Context context) {
        String[] volumes = context.getResources().getStringArray(R.array.volume_values);
        String volumeString = PreferenceManager.getDefaultSharedPreferences(context).getString(
                context.getString(R.string.key_pref_music_volume), volumes[PREF_DEFAULT_MUSIC_VOLUME_ITEM]);
        return new Float(volumeString).floatValue();
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static void start(Context context, int music) {
        start(context, music, false);
    }

    public static void start(Context context, int music, boolean force) {
        if (!force && currentMusic > -1) {
    // already playing some music and not forced to change
            return;
        }
        if (music == MUSIC_PREVIOUS) {
            Log.d(TAG, "Using previous music [" + previousMusic + "]");
            music = previousMusic;
        }
        if (currentMusic == music) {
    // already playing this music
            return;
        }
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
    // playing some other music, pause it and change
            pause();
        }
        currentMusic = music;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
        MediaPlayer mp = (MediaPlayer) players.get(music);
        if (mp != null) {
            if (!mp.isPlaying()) {
                mp.start();
            }
        } else {
            /*if (music == MUSIC_MENU) {
                mp = MediaPlayer.create(context, R.raw.theme_song);
            } else if (music == MUSIC_GAME) {
                mp = MediaPlayer.create(context, R.raw.theme_song);
            } else if (music == MUSIC_END_GAME) {
                mp = MediaPlayer.create(context, R.raw.theme_song);
            } else {
                Log.e(TAG, "unsupported music number - " + music);
                return;
            }*/
            mp = MediaPlayer.create(context, R.raw.theme_song);
            players.put(music, mp);
            float volume = getMusicVolume(context);
            Log.d(TAG, "Setting music volume to " + volume);
            mp.setVolume(volume, volume);
            if (mp == null) {
                Log.e(TAG, "player was not created successfully");
            } else {
                try {
                    mp.setLooping(true);
                    mp.start();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        }
        isPaused = false;
    }

    public static void pause() {
        Collection mps = players.values();
        isPaused = true;
        for (Object p : mps) {
            if (((MediaPlayer) p).isPlaying()) {
                ((MediaPlayer) p).pause();
            }
        }
// previousMusic should always be something valid
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
        }
        currentMusic = -1;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
    }

    public static void updateVolumeFromPrefs(Context context) {
        try {
            float volume = getMusicVolume(context);
            Log.d(TAG, "Setting music volume to " + volume);
            Collection mps = players.values();
            for (Object p : mps) {
                ((MediaPlayer) p).setVolume(volume, volume);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static void release() {
        Log.d(TAG, "Releasing media players");
        Collection mps = players.values();
        for (Object mp : mps) {
            try {
                if (mp != null) {
                    if (((MediaPlayer) mp).isPlaying()) {
                        ((MediaPlayer) mp).stop();
                    }
                    ((MediaPlayer) mp).release();
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        mps.clear();
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
        }
        currentMusic = -1;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
    }
}
