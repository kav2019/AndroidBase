package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String pref = "prefs.xml"; // Имя настроек
    public static final String pref_name = "theme"; // Имя параметра в настройках


    private TextView display;
    private StringBuilder numbers = new StringBuilder();
    private boolean action = false;
    private int actPos = 0;
    private static final String KEY_NUMBERS = "numbers"; //Ключ для списка
    private static final String KEY_DISPLAY = "display";//Ключ для результата вычесления
    private String result_display; //сохраняем результат вычисления

    private SwitchCompat changeTheme;
    private Button buttonClear; // Кнопка очистить
    private Button buttonDel; // Кнопка удалить символ
    private Button buttonPercent; // Кнопка вычисления процента
    private Button buttonDec; // Кнопка деления
    private Button button7; // Кнопка 7
    private Button button8; // Кнопка 8
    private Button button9; // Кнопка 9
    private Button buttonX; // Кнопка умножения
    private Button button4; // Кнопка 4
    private Button button5; // Кнопка 5
    private Button button6; // Кнопка 6
    private Button buttonMinus; // Кнопка вычетания
    private Button button1; // Кнопка 1
    private Button button2; // Кнопка 2
    private Button button3; // Кнопка 3
    private Button buttonPlus; // Кнопка сложения
    private Button button0; // Кнопка 0
    private Button buttonPt; // Кнопка точки
    private Button buttonEquals; // Кнопка равно


    // Слушатель поле на цифры
    private final View.OnClickListener numericKeybord = (view) -> {
        switch (view.getId()){
            case R.id.button0: {
                numbers.append("0");
                display.setText(numbers);
                break;
            }
            case R.id.button1: {
                numbers.append("1");
                display.setText(numbers);
                break;
            }
            case R.id.button2: {
                numbers.append("2");
                display.setText(numbers);
                break;
            }
            case R.id.button3: {
                numbers.append("3");
                display.setText(numbers);
                break;
            }
            case R.id.button4: {
                numbers.append("4");
                display.setText(numbers);
                break;
            }
            case R.id.button5: {
                numbers.append("5");
                display.setText(numbers);
                break;
            }
            case R.id.button6: {
                numbers.append("6");
                display.setText(numbers);
                break;
            }
            case R.id.button7: {
                numbers.append("7");
                display.setText(numbers);
                break;
            }
            case R.id.button8: {
                numbers.append("8");
                display.setText(numbers);
                break;
            }
            case R.id.button9: {
                numbers.append("9");
                display.setText(numbers);
                break;
            }
            case R.id.buttonPt: {
                numbers.append(".");
                display.setText(numbers);
                break;
            }
        }
    };
    //Очистка всего поля
    private final View.OnClickListener btClearListener = (view -> {
        if (numbers.length() != 0){
            numbers = new StringBuilder();
            action = false;
            actPos = 0;
        }
        display.setText(numbers);
    });
    // Удаление символа
    private final View.OnClickListener btDelSimbolListener = (view -> {
        if (numbers.length() != 0){
            numbers.deleteCharAt(numbers.length()-1);
        }
        display.setText(numbers);
    });
    //Процент числа
    private final View.OnClickListener btPercentListener = (view -> {
        String num = "";
        double percent = 0.0;
        if (numbers.length() != 0){
            num = numbers.toString();
            percent = Double.parseDouble(num) / 100;
        }
        display.setText(Double.toString(percent));
    });
    //Добавляем деление, указатель действия - 1
    private final View.OnClickListener btDivideListener = (view -> {
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("*") || posSimbol.equals("-") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("/");
            actPos = 1;
        }
        display.setText(numbers);
    });
    //Добавляем умножение, указатель действия - 2
    private final View.OnClickListener btIncrsListener = (view -> {
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("-") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("*");
            actPos = 2;
        }
        display.setText(numbers);
    });
    //Добавляем минус, указатель действия - 3
    private final View.OnClickListener btMinusListener = (view -> {
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("-");
            actPos = 3;
        }
        display.setText(numbers);
    });
    //Добавляем плюс, указатель действия - 4
    final private View.OnClickListener btPlusListener = (view -> {
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("-")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("+");
            actPos = 4;
        }
        display.setText(numbers);
    });
    //Выполнение операций
    private final View.OnClickListener btEquallyListener = (view -> {
        if (numbers.length() == 0){
            return;
        }
        String num = numbers.toString();
        if (actPos == 1){ //деление
            int posSign = numbers.indexOf("/");
            String firstNimb = num.substring(0, posSign);
            String secondNimb = num.substring(posSign + 1);
            Double firstNimber = Double.parseDouble(firstNimb);
            Double secondNimber = Double.parseDouble(secondNimb);
            if (Integer.parseInt(secondNimb) == 0){
                result_display = "На ноль делить нельзя!";
                display.setText(result_display);
                numbers = new StringBuilder();
                return;
            }
            Double result = firstNimber / secondNimber;
            result_display = result.toString();
            display.setText(result_display);
            numbers = new StringBuilder();
        }else if (actPos == 2) { //умножение
            int posSign = numbers.indexOf("*");
            String firstNimb = num.substring(0, posSign);
            String secondNimb = num.substring(posSign + 1);
            Double firstNimber = Double.parseDouble(firstNimb);
            Double secondNimber = Double.parseDouble(secondNimb);
            Double result = firstNimber * secondNimber;
            result_display = result.toString();
            display.setText(result_display);
            numbers = new StringBuilder();
        }else if (actPos == 3) { //вычетание
            int posSign = numbers.indexOf("-");
            String firstNimb = num.substring(0, posSign);
            String secondNimb = num.substring(posSign + 1);
            Double firstNimber = Double.parseDouble(firstNimb);
            Double secondNimber = Double.parseDouble(secondNimb);
            Double result = firstNimber - secondNimber;
            result_display = result.toString();
            display.setText(result_display);
            numbers = new StringBuilder();
        }else if (actPos == 4) { //сложение
            int posSign = numbers.indexOf("+");
            String firstNimb = num.substring(0, posSign);
            String secondNimb = num.substring(posSign + 1);
            Double firstNimber = Double.parseDouble(firstNimb);
            Double secondNimber = Double.parseDouble(secondNimb);
            Double result = firstNimber + secondNimber;
            result_display = result.toString();
            display.setText(result_display);
            numbers = new StringBuilder();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isNighTheme = getSharedPreferences(pref, MODE_PRIVATE).getBoolean(pref_name, false); //Устанавливаем тему по умолчанию
        if (isNighTheme){
            setTheme(R.style.myStyle);
        }else {
            setTheme(R.style.myStyleDay);
        }

        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        initialization(); // Инициализация кнопок и установка слушателей

        changeTheme.setOnCheckedChangeListener((buttonView, isCheced) -> {
            SharedPreferences sharedPreferences = getSharedPreferences(pref, MODE_PRIVATE);
            if(sharedPreferences.getBoolean(pref_name, false) != isCheced){
                sharedPreferences.edit().putBoolean(pref_name, isCheced).apply();
                recreate();
            }else {
                sharedPreferences.edit().putBoolean(pref_name, !isCheced).apply();
                recreate();
            }
        });
    }



    @Override
    protected void onSaveInstanceState(Bundle state){ //Сохранение данных в бандла
        super.onSaveInstanceState(state);
        state.putString(KEY_NUMBERS, numbers.toString());
        state.putString(KEY_DISPLAY, result_display);
    }


    @Override
    protected void onRestoreInstanceState(Bundle state){ //Востановление данных из бандла
        super.onSaveInstanceState(state);
        numbers.append(state.getString(KEY_NUMBERS));
        result_display = state.getString(KEY_DISPLAY);
        display = findViewById(R.id.display);
        if (numbers.length() > 0){
            display.setText(numbers.toString());
        }else {
            display.setText(result_display);
        }

    }

    // Инициализация кнопок и установка слушателей
    private void initialization(){
        //инициализируем поля
        changeTheme = findViewById(R.id.themeKeybord);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDel = findViewById(R.id.buttonDel);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonDec = findViewById(R.id.buttonDec);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonX = findViewById(R.id.buttonX);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        buttonMinus = findViewById(R.id.buttonMinus);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonPlus = findViewById(R.id.buttonPlus);
        button0 = findViewById(R.id.button0);
        buttonPt = findViewById(R.id.buttonPt);
        buttonEquals = findViewById(R.id.buttonEquals);
        //Слущатели цифровых кнопок
        button0.setOnClickListener(numericKeybord);
        button1.setOnClickListener(numericKeybord);
        button2.setOnClickListener(numericKeybord);
        button3.setOnClickListener(numericKeybord);
        button4.setOnClickListener(numericKeybord);
        button5.setOnClickListener(numericKeybord);
        button6.setOnClickListener(numericKeybord);
        button7.setOnClickListener(numericKeybord);
        button8.setOnClickListener(numericKeybord);
        button9.setOnClickListener(numericKeybord);
        buttonPt.setOnClickListener(numericKeybord);
        //Слущатели функционала
        buttonClear.setOnClickListener(btClearListener);
        buttonDel.setOnClickListener(btDelSimbolListener);
        buttonPercent.setOnClickListener(btPercentListener);
        buttonDec.setOnClickListener(btDivideListener);
        buttonX.setOnClickListener(btIncrsListener);
        buttonMinus.setOnClickListener(btMinusListener);
        buttonPlus.setOnClickListener(btPlusListener);
        buttonEquals.setOnClickListener(btEquallyListener);
    }



//    public void clear(View view){ //Очистка всего поля
//        if (numbers.length() != 0){
//            numbers = new StringBuilder();
//            action = false;
//            actPos = 0;
//        }
//        display.setText(numbers);
//    }



//    public void delSimbol(View view){ // Удаление символа
//        if (numbers.length() != 0){
//            numbers.deleteCharAt(numbers.length()-1);
//        }
//        display.setText(numbers);
//    }



//    public void percent(View view){ //Процент числа
//        String num = "";
//        double percent = 0.0;
//        if (numbers.length() != 0){
//            num = numbers.toString();
//            percent = Double.parseDouble(num) / 100;
//        }
//        display.setText(Double.toString(percent));
//    }



//    public void divide(View view){ //Добавляем деление, указатель действия - 1
//        if (numbers.length() != 0){
//            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
//            if (posSimbol.equals("*") || posSimbol.equals("-") || posSimbol.equals("+")){
//                numbers.deleteCharAt(numbers.length() - 1);
//            }
//            numbers.append("/");
//            actPos = 1;
//        }
//        display.setText(numbers);
//    }



//    public void incrs(View view){ //Добавляем умножение, указатель действия - 2
//        if (numbers.length() != 0){
//            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
//            if (posSimbol.equals("/") || posSimbol.equals("-") || posSimbol.equals("+")){
//                numbers.deleteCharAt(numbers.length() - 1);
//            }
//            numbers.append("*");
//            actPos = 2;
//        }
//        display.setText(numbers);
//    }




//    public void minus(View view){ //Добавляем минус, указатель действия - 3
//        if (numbers.length() != 0){
//            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
//            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("+")){
//                numbers.deleteCharAt(numbers.length() - 1);
//            }
//            numbers.append("-");
//            actPos = 3;
//        }
//        display.setText(numbers);
//    }




//    public void plus(View view){ //Добавляем плюс, указатель действия - 4
//        if (numbers.length() != 0){
//            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
//            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("-")){
//                numbers.deleteCharAt(numbers.length() - 1);
//            }
//            numbers.append("+");
//            actPos = 4;
//        }
//        display.setText(numbers);
//    }


//    public void seven(View view){ //Добавляем 7
//        numbers.append("7");
//        display.setText(numbers);
//    }
//
//    public void eight(View view){ //Добавляем 8
//        numbers.append("8");
//        display.setText(numbers);
//    }
//
//    public void nine(View view){ //Добавляем 9
//        numbers.append("9");
//        display.setText(numbers);
//    }
//
//    public void four(View view){ //Добавляем 4
//        numbers.append("4");
//        display.setText(numbers);
//    }
//
//    public void five(View view){ //Добавляем 5
//        numbers.append("5");
//        display.setText(numbers);
//    }
//
//    public void six(View view){ //Добавляем 6
//        numbers.append("6");
//        display.setText(numbers);
//    }
//
//    public void one(View view){ //Добавляем 1
//        numbers.append("1");
//        display.setText(numbers);
//    }
//
//    public void two(View view){ //Добавляем 2
//        numbers.append("2");
//        display.setText(numbers);
//    }
//
//    public void three(View view){ //Добавляем 3
//        numbers.append("3");
//        display.setText(numbers);
//    }
//
//    public void zero(View view){ //Добавляем 0
//        numbers.append("0");
//        display.setText(numbers);
//    }
//
//    public void point(View view){ //Добавляем точку
//        numbers.append(".");
//        display.setText(numbers);
//    }




//    public void equally(View view){ //Выполнение операций
//        if (numbers.length() == 0){
//            return;
//        }
//        String num = numbers.toString();
//        if (actPos == 1){ //деление
//            int posSign = numbers.indexOf("/");
//            String firstNimb = num.substring(0, posSign);
//            String secondNimb = num.substring(posSign + 1);
//            Double firstNimber = Double.parseDouble(firstNimb);
//            Double secondNimber = Double.parseDouble(secondNimb);
//            if (Integer.parseInt(secondNimb) == 0){
//                result_display = "На ноль делить нельзя!";
//                display.setText(result_display);
//                numbers = new StringBuilder();
//                return;
//            }
//            Double result = firstNimber / secondNimber;
//            result_display = result.toString();
//            display.setText(result_display);
//            numbers = new StringBuilder();
//        }else if (actPos == 2) { //умножение
//            int posSign = numbers.indexOf("*");
//            String firstNimb = num.substring(0, posSign);
//            String secondNimb = num.substring(posSign + 1);
//            Double firstNimber = Double.parseDouble(firstNimb);
//            Double secondNimber = Double.parseDouble(secondNimb);
//            Double result = firstNimber * secondNimber;
//            result_display = result.toString();
//            display.setText(result_display);
//            numbers = new StringBuilder();
//        }else if (actPos == 3) { //вычетание
//            int posSign = numbers.indexOf("-");
//            String firstNimb = num.substring(0, posSign);
//            String secondNimb = num.substring(posSign + 1);
//            Double firstNimber = Double.parseDouble(firstNimb);
//            Double secondNimber = Double.parseDouble(secondNimb);
//            Double result = firstNimber - secondNimber;
//            result_display = result.toString();
//            display.setText(result_display);
//            numbers = new StringBuilder();
//        }else if (actPos == 4) { //сложение
//            int posSign = numbers.indexOf("+");
//            String firstNimb = num.substring(0, posSign);
//            String secondNimb = num.substring(posSign + 1);
//            Double firstNimber = Double.parseDouble(firstNimb);
//            Double secondNimber = Double.parseDouble(secondNimb);
//            Double result = firstNimber + secondNimber;
//            result_display = result.toString();
//            display.setText(result_display);
//            numbers = new StringBuilder();
//        }
//    }



}