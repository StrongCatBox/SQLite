package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private StringBuffer monBuffer; //Je declare mon objet de type StringBuffer

   // private EditText SearchBar;
   // private Button search;

    private ListView listViewPeople;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewPeople = findViewById(R.id.listViewPeople);


        ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> map;


        SQLiteMaDataBase maSQLdb = new SQLiteMaDataBase(this);

        Cursor cursor = maSQLdb.lireTable();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            map = new HashMap<String,String>();

            map.put("id" , String.valueOf(cursor.getInt(0)));
            map.put("NOM" , cursor.getString(1) );
            map.put("PRENOM", cursor.getString(2));
            map.put("AGE", String.valueOf(cursor.getInt(3)));


            listItem.add(map);


            cursor.moveToNext();
        }

        Log.d("base", "listItem: " + listItem);





        SimpleAdapter monAdpter = new SimpleAdapter(this.getBaseContext(),listItem,R.layout.affichage_tableau,
                new String[] {"id","NOM","PRENOM","AGE"}, new int[] {R.id.id,R.id.nom,R.id.prenom,R.id.age});

        listViewPeople.setAdapter(monAdpter);


        listViewPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> item = listItem.get(position);

                Intent intent = new Intent(MainActivity.this, PersonDetailActivity.class);

                intent.putExtra("id", item.get("id"));
                intent.putExtra("nom", item.get("NOM"));
                intent.putExtra("prenom", item.get("PRENOM"));
                intent.putExtra("age", item.get("AGE"));

                startActivity(intent);

            }
        });













       // search = findViewById(R.id.search);
      //  SearchBar = findViewById(R.id.SearchBar);

/*
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchTerm = SearchBar.getText().toString().trim();
                Cursor cursor;
                if (searchTerm.isEmpty()) cursor = maSQLdb.lireTable();
                else {
                    // Vérifiez si l'utilisateur a entré un nombre ou un prénom
                    try {
                        // Si l'utilisateur a entré un nombre, recherche par âge
                        int age = Integer.parseInt(searchTerm);
                        cursor = maSQLdb.lireTable(age);
                    } catch (NumberFormatException e) {
                        Log.d("catch", "passage catch " + searchTerm);
                        // Si l'utilisateur n'a pas entré un nombre, recherche par Prénom / Nom
                        cursor = maSQLdb.lireTable(searchTerm);
                        Log.d("catch", "passage catch " + searchTerm);

                    }


            }

                if (cursor.getCount() == 0) {
                    // Si pas d'enregistrements, affichez un message
                    Toast.makeText(MainActivity.this, "Aucune correspondance trouvée.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si des enregistrements sont trouvés, traitez les données
                    monBuffer = new StringBuffer();
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        monBuffer.append("id:" + cursor.getInt(0) + "\n\r\r");
                        monBuffer.append("NOM:" + cursor.getString(1) + "\n\r\r");
                        monBuffer.append("PRENOM:" + cursor.getString(2) + "\n\r\r");
                        monBuffer.append("AGE :" + cursor.getInt(3) + "\n");
                        monBuffer.append("__________\n");
                        cursor.moveToNext();
                    }
                    cursor.close();
                    Log.d("Buffer", "monBuffer" + monBuffer);
                    infoMaBase("Résultats de la recherche", monBuffer);
                }


            }



        });   */
        /*
        maSQLdb.insertionCLIENTS("HANKS", "TOM", 19);
        maSQLdb.insertionCLIENTS("DEPP", "JOHNY", 50);
        maSQLdb.insertionCLIENTS("JOLIE", "ANGELINA", 45);
        maSQLdb.insertionCLIENTS("CRANSTON", "BRYAN", 60);
        maSQLdb.insertionCLIENTS("CRUISE", "TOM", 45);
        maSQLdb.insertionCLIENTS("PITT", "BRAD", 45);

        maSQLdb.close(); //permet de fermer la base de données*/

        /*Cursor monCurseur2 = maSQLdb.lireTable(45);

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


        }  */




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

    /*button.setOnClickListener(v) -> {
        String strEditText = editText.getText().toString().trim();

        if (strEditText.isEmpty()) monCurseur = maSQLdb.lireTable();
        else {
            try {
                Integer.valueOf(strEditText);
                monCurseur2 = maSQLdb.lireTable(Integer.valueOf(strEditText));
            }catch (Exception e) {
                monCurseur2 = maSQLdb.lireTable(strEditText);
            }
        }
    }

    if (monCurseur2.getCount() == 0) {

        monBuffer = new StringBuffer();
        monBuffer.append("la base ne contient pas cette recherche");
        infoMaBase("Table: " + maSQLdb.NOM_TABLE, monBuffer);
    }else {
        monBuffer = new StringBuffer();
        monCurseur2.moveToFirst();
        while (!monCurseur2.isAfterLAst()) {

    }*/
}