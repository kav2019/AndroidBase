package com.example.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesListFragment extends Fragment {
    private int mCurrentNotesIdx = -1;
    public static final String KEY_BUNDL = "key.save.index";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotesListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NotesListFragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_notes_list, container, false);

        String[] notes = getResources().getStringArray(R.array.notes); //Получаем названия заметок из списка заметок

        int indx = 0;
        for (String note : notes){ //Проходимся по массиву который получили и выводим на экран
            TextView tv = new TextView(getContext()); //Создаем поле
            tv.setText(note); // Выводим на экран то что получили из массива
            tv.setTextSize(30); //Устанавливаем размер текста
            final int textInx = indx;
            tv.setOnClickListener((view1) -> { //устанавливаем слушателя на создаваемую надпись
                setCurrentNoteIdx(textInx);// устанавливаем число которое выбрали и потом сохраним
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { // Проверяем если портретный то переходим в одну активити
                    goToActivity(textInx);
                }else {
                    showTextToRight(textInx);
                }
            });
            view.addView(tv); //Добавляем в контейнер LinerLayout
            indx++;
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null){
            mCurrentNotesIdx = bundle.getInt(KEY_BUNDL, -1);
            if (mCurrentNotesIdx != -1 && getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE){
                showTextToRight(mCurrentNotesIdx);
            }
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_BUNDL, mCurrentNotesIdx);
    }

    private void setCurrentNoteIdx(int textInx) {
        mCurrentNotesIdx = textInx;
    }

    private void goToActivity(int textInx){ //Переход в активити при портретном режиме
        Intent intent = new Intent(getActivity(), TextNoteActivity.class);
        intent.putExtra(TextNoteActivity.KEY_NOTES_INDEX, textInx);
        startActivity(intent);
    }

    private void showTextToRight(int textInx){//Показываение текста заметки при ландшафтной орентации - справа
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager(); // Создаем менеджер фрагментов
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // Создаем транзакцию
        transaction.replace(R.id.text_node_fragment_land, TextNodeFragment.newInstance(textInx));  // Меняем фрагмент
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Плавная смена фрагмента
        transaction.commit();;
    }
}