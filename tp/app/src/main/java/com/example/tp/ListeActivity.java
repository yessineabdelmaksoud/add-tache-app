package com.example.tp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    ListView listViewTaches;
    DatabaseHelper dbHelper;
    ArrayAdapter<String> adapter;
    ArrayList<String> listeTaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        listViewTaches = findViewById(R.id.listViewTaches);
        dbHelper = new DatabaseHelper(this);
        listeTaches = new ArrayList<>();

        chargerTaches();
    }

    private void chargerTaches() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM taches", null);

        if (cursor.moveToFirst()) {
            do {
                String tache = cursor.getString(cursor.getColumnIndexOrThrow("tache"));
                listeTaches.add(tache);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeTaches);
        listViewTaches.setAdapter(adapter);
    }
}
