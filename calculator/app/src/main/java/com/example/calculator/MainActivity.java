package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private StringBuilder numbers = new StringBuilder();
    private boolean action = false;
    private int actPos = 0;
    private static final String KEY_NUMBERS = "numbers"; //Ключ для списка
    private static final String KEY_DISPLAY = "display";//Ключ для результата вычесления
    private String result_display; //сохраняем результат вычисления


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
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


    public void clear(View view){ //Очистка всего поля
        if (numbers.length() != 0){
            numbers = new StringBuilder();
            action = false;
            actPos = 0;
        }
        display.setText(numbers);
    }

    public void delSimbol(View view){ // Удаление символа
        if (numbers.length() != 0){
            numbers.deleteCharAt(numbers.length()-1);
        }
        display.setText(numbers);
    }

    public void percent(View view){ //Процент числа
        String num = "";
        double percent = 0.0;
        if (numbers.length() != 0){
            num = numbers.toString();
            percent = Double.parseDouble(num) / 100;
        }
        display.setText(Double.toString(percent));
    }

    public void divide(View view){ //Добавляем деление, указатель действия - 1
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("*") || posSimbol.equals("-") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("/");
            actPos = 1;
        }
        display.setText(numbers);
    }

    public void incrs(View view){ //Добавляем умножение, указатель действия - 2
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("-") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("*");
            actPos = 2;
        }
        display.setText(numbers);
    }

    public void minus(View view){ //Добавляем минус, указатель действия - 3
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("+")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("-");
            actPos = 3;
        }
        display.setText(numbers);
    }


    public void plus(View view){ //Добавляем плюс, указатель действия - 4
        if (numbers.length() != 0){
            String posSimbol = String.valueOf(numbers.charAt(numbers.length() - 1));
            if (posSimbol.equals("/") || posSimbol.equals("*") || posSimbol.equals("-")){
                numbers.deleteCharAt(numbers.length() - 1);
            }
            numbers.append("+");
            actPos = 4;
        }
        display.setText(numbers);
    }


    public void seven(View view){ //Добавляем 7
        numbers.append("7");
        display.setText(numbers);
    }

    public void eight(View view){ //Добавляем 8
        numbers.append("8");
        display.setText(numbers);
    }

    public void nine(View view){ //Добавляем 9
        numbers.append("9");
        display.setText(numbers);
    }

    public void four(View view){ //Добавляем 4
        numbers.append("4");
        display.setText(numbers);
    }

    public void five(View view){ //Добавляем 5
        numbers.append("5");
        display.setText(numbers);
    }

    public void six(View view){ //Добавляем 6
        numbers.append("6");
        display.setText(numbers);
    }

    public void one(View view){ //Добавляем 1
        numbers.append("1");
        display.setText(numbers);
    }

    public void two(View view){ //Добавляем 2
        numbers.append("2");
        display.setText(numbers);
    }

    public void three(View view){ //Добавляем 3
        numbers.append("3");
        display.setText(numbers);
    }

    public void zero(View view){ //Добавляем 0
        numbers.append("0");
        display.setText(numbers);
    }

    public void point(View view){ //Добавляем точку
        numbers.append(".");
        display.setText(numbers);
    }


    public void equally(View view){ //Выполнение операций
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
    }
}