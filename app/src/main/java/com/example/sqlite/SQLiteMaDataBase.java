package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteMaDataBase extends SQLiteOpenHelper {

    public static final String BASE_NOM = "MaBase.db";
    public static final int BASE_VERSION = 1;

    public SQLiteMaDataBase (Context context) {
        super(context, BASE_NOM,null,BASE_VERSION);

    }

}
