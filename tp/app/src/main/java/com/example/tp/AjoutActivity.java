package com.example.tp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AjoutActivity extends AppCompatActivity {

    EditText editTextTache;
    Button btnAjouter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        editTextTache = findViewById(R.id.editTextTache);
        btnAjouter = findViewById(R.id.btnAjouter);
        dbHelper = new DatabaseHelper(this);

        btnAjouter.setOnClickListener(v -> {
            String tache = editTextTache.getText().toString().trim();
            if (!tache.isEmpty()) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("tache", tache);
                db.insert("taches", null, values);
                db.close();
                Toast.makeText(this, "Tâche ajoutée !", Toast.LENGTH_SHORT).show();
                editTextTache.setText("");
            } else {
                Toast.makeText(this, "Veuillez écrire une tâche", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
