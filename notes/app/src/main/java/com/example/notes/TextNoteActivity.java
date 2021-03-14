package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class TextNoteActivity extends AppCompatActivity {

    public static String KEY_NOTES_INDEX = "key_notes_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_note);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){ // если альбомная орентация, выходим из активити для портретного режиа
            finish();
            return;
        }

        if (savedInstanceState == null){
            int nodeIndx = getIntent().getIntExtra(KEY_NOTES_INDEX, -1);

            FragmentManager fragmentManager = getSupportFragmentManager(); // Создаем менеджер фрагментов
            FragmentTransaction transaction = fragmentManager.beginTransaction(); // Создаем транзакцию
            transaction.replace(R.id.text_note, TextNodeFragment.newInstance(nodeIndx));  // Меняем фрагмент
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Плавная смена фрагмента
            transaction.commit();  // Закрываем транзакцию
        }
    }
}