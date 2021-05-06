package com.example.stringresource;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    Locale locale;
    Configuration config;
    String languageToLoad;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sharedPreferences = getSharedPreferences("language", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("check", false)){
            languageToLoad  = "en";
            toggleButton.setChecked(true);
        } else {
            languageToLoad = "vi";
            toggleButton.setChecked(false);
        }
        locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked){
                editor.putBoolean("check", false);
            } else {
                editor.putBoolean("check", true);
            }
            editor.apply();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void init() {
        toggleButton = findViewById(R.id.togglebtn);
    }

}