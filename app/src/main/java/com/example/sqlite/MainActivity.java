package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteMaDataBase maSQLdb = new SQLiteMaDataBase(this);
        maSQLdb.insertionCLIENTS("WOLF", "Lisa", 19);
        maSQLdb.insertionCLIENTS("DEPP", "JOHNY", 50);
        maSQLdb.insertionCLIENTS("JOLIE", "ANGELINA", 45);
        maSQLdb.insertionCLIENTS("CRANSTON", "BRYAN", 60);
        maSQLdb.insertionCLIENTS("CRUISE", "TOM", 45);

        maSQLdb.close(); //permet de fermer la base de données

    }
}