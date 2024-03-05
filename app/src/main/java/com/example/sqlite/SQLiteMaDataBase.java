package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteMaDataBase extends SQLiteOpenHelper {

    public static final String BASE_NOM = "MaBase.db";
    public static final int BASE_VERSION = 1;
    public static final String NOM_TABLE = "T_clients";
    public static final String COL0 = "IdClient";
    public static final String COL1 = "NOM";
    public static final String COL2 = "PRENOM";
    public static final String COL3 = "AGE";

    public SQLiteMaDataBase (Context context) {
        super(context, BASE_NOM,null,BASE_VERSION);

    }


    // 2 methodes OnCreate et onUpgrade
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE " + NOM_TABLE +
                " ("
                + COL0 + " integer primary key autoincrement,"
                + COL1 + " text not null,"
                + COL2 + " text not null,"
                + COL3 + " integer not null);";
        Log.d("DataBase", "strSql:" + strSql);
        db.execSQL(strSql);
        Log.d("DataBase", "Creation de la Base de données OK" + NOM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d ("DataBase","oldVersion:" + oldVersion);
        Log.d("DataBase", "newVersion:" + newVersion);
        String strSql = "DROP TABLE IF EXISTS " + NOM_TABLE + ";";
        Log.d("DataBase", "requete sql ds Upgrade: " + strSql);
        db.execSQL(strSql);
        onCreate(db);
        Log.d("DataBase", "Methode Upgrade Call: " + NOM_TABLE);

    }
}
