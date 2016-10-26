package com.xtronlabs.koochooloo.content;


import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

class KoochoolooDbOpenHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "koochooloo.sqlite";
    private static final int DB_VERSION = 1;

    public KoochoolooDbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
