package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);


        Intent intent = getIntent();
        if (intent != null) {

            String id = intent.getStringExtra("id");
            String nom = intent.getStringExtra("nom");
            String prenom = intent.getStringExtra("prenom");
            String age = intent.getStringExtra("age");


            id textViewId = findViewById(R.id.id);
            nom textViewNom = findViewById(R.id.nom);
            prenom textViewPrenom = findViewById(R.id.prenom);
            age textViewAge = findViewById(R.id.age);

            textViewId.setText("ID: " + id);
            textViewNom.setText("Nom: " + nom);
            textViewPrenom.setText("Prénom: " + prenom);
            textViewAge.setText("Âge: " + age);
        }
    }
}