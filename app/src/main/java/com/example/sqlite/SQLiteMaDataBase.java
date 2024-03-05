package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteMaDataBase extends SQLiteOpenHelper {

    public static final String BASE_NOM = "MaBase.db";
    public static final int BASE_VERSION = 3;
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

    public void insertionCLIENTS(String NOM, String PRENOM, Integer AGE) {
        //pas ID il est en autoIncrement

        NOM = NOM.replace("'", " ");
        PRENOM = PRENOM.replace("'", " ");

        //On ne peut pas avoir de simples cote dans une chaine de caracteres sql,
        //Vu que l'on s'en sert dans la requete insert on remplace donc les eventuelles simple ' par un espace

        String strSql = "INSERT INTO " + NOM_TABLE + "(" + COL1 + "," + COL2 + "," + COL3 + ")"
                + "values ('" + NOM + "','" + PRENOM + "'," + AGE + ");";
        Log.d("DataBase", "StrSql insert: " + strSql);
        getWritableDatabase().execSQL(strSql);
        Log.d("DataBase", "insertionClients, insertion OK");
    }
}
