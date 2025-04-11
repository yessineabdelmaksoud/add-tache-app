package com.example.tp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    EditText etNom;
    Button btnEnregistrer;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        etNom = findViewById(R.id.etNom);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        tvMessage = findViewById(R.id.tvMessage);

        SharedPreferences prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        prefs.edit().remove("nom_utilisateur").apply();
        String nomEnregistre = prefs.getString("nom_utilisateur", null);

        if (nomEnregistre != null) {
            // L'utilisateur a déjà enregistré son nom
            tvMessage.setText("Bienvenue " + nomEnregistre + " !");
            tvMessage.setVisibility(TextView.VISIBLE);
            etNom.setVisibility(EditText.GONE);
            btnEnregistrer.setVisibility(Button.GONE);

            // Aller automatiquement à MainActivity après un petit délai
            new android.os.Handler().postDelayed(() -> {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }, 2000);
        } else {
            // Saisie et enregistrement du nom
            btnEnregistrer.setOnClickListener(v -> {
                String nom = etNom.getText().toString().trim();
                if (!nom.isEmpty()) {
                    prefs.edit().putString("nom_utilisateur", nom).apply();
                    Toast.makeText(this, "Nom enregistré !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Veuillez entrer votre nom", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

