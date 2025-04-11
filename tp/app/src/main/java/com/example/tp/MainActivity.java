package com.example.tp;

import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Charger le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // Gérer les clics sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add_task) {
            // Aller vers AjoutActivity
            Intent intent = new Intent(this, AjoutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_view_tasks) {
            // Aller vers ListeActivity
            Intent intent = new Intent(this, ListeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_reset_user) {
            // Réinitialiser les SharedPreferences
            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            preferences.edit().clear().apply();

            // Retourner à l’écran de bienvenue
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
