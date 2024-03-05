package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private StringBuffer monBuffer; //Je declare mon objet de type StringBuffer

    private EditText SearchBar;
    private Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SQLiteMaDataBase maSQLdb = new SQLiteMaDataBase(this);
        /*
        maSQLdb.insertionCLIENTS("HANKS", "TOM", 19);
        maSQLdb.insertionCLIENTS("DEPP", "JOHNY", 50);
        maSQLdb.insertionCLIENTS("JOLIE", "ANGELINA", 45);
        maSQLdb.insertionCLIENTS("CRANSTON", "BRYAN", 60);
        maSQLdb.insertionCLIENTS("CRUISE", "TOM", 45);
        maSQLdb.insertionCLIENTS("PITT", "BRAD", 45);

        maSQLdb.close(); //permet de fermer la base de données*/

        Cursor monCurseur2 = maSQLdb.lireTable(45);

        if (monCurseur2.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Il n'ya pas de données dans la table", Toast.LENGTH_LONG).show();
        }else {
            monBuffer = new StringBuffer(); // je cree un objet de type Buffer pour stocker mes DATA
            monCurseur2.moveToFirst();
            while (!monCurseur2.isAfterLast()) {
                monBuffer.append("Id: " + monCurseur2.getInt(0) + "\n\r\r");
                monBuffer.append("NOM: " + monCurseur2.getString(1) + "\n\r\r");
                monBuffer.append("PRENOM: " + monCurseur2.getString(2) + "\n\r\r");
                monBuffer.append("AGE: " + monCurseur2.getInt(3) + "\n\r\r");
                monBuffer.append("____________\n");
                monCurseur2.moveToNext();

            }
            monCurseur2.close();
            infoMaBase("Table: " + maSQLdb.NOM_TABLE, monBuffer);
            Log.i("monBuffer", "monBuffer : " + monBuffer);


        }




    }

    public void infoMaBase(String titre, StringBuffer message) {
        AlertDialog aD = new AlertDialog.Builder(this).create();
        aD.setTitle(titre);
        aD.setMessage(message);
        aD.setButton(AlertDialog.BUTTON_POSITIVE, "Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onRestart();
            }
        });
        aD.setButton(AlertDialog.BUTTON_NEUTRAL, "QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        aD.show();
    }
}