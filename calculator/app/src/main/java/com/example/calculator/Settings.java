package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {


    private SwitchCompat changeTheme;
    private MaterialButton buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        boolean isNighTheme = getSharedPreferences(MainActivity.pref, MODE_PRIVATE).getBoolean(MainActivity.pref_name, false);
        if (isNighTheme){
            setTheme(R.style.myStyle);
        }else {
            setTheme(R.style.myStyleDay);
        }
        setContentView(R.layout.activity_settings);

        changeTheme = findViewById(R.id.themeSettings);
        buttonBack = findViewById(R.id.buttonBack);

        changeTheme.setOnCheckedChangeListener((buttonView, isCheced) -> { // слушатель на выключатель
            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.pref, MODE_PRIVATE);
            if(sharedPreferences.getBoolean(MainActivity.pref_name, false) != isCheced){
                sharedPreferences.edit().putBoolean(MainActivity.pref_name, isCheced).apply();
                recreate();
            }
        });

        buttonBack.setOnClickListener((view )-> {  //возвращаем intent с результатом
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.KEY_INTENT, isNighTheme);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

    }
}