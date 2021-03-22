package com.example.notes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Notes {
    private String nameNotes;
    private String textNode;
    private Date dateNode;
    private String displayNote;


    public Notes(String nameNotes, String textNode){
        this.nameNotes = nameNotes;
        this.textNode = textNode;
        this.dateNode = new Date();
        this.displayNote = summText(nameNotes, this.dateNode, textNode);
    }

    public String getNameNotes(){
        return nameNotes;
    }

    public String getTextNode(){
        return textNode;
    }

    public Date getDateNode(){
        return dateNode;
    }

    public String getDisplayNote(){
        return displayNote;
    }

    private String summText(String nameNotes, Date dateNode, String textNode){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Создана 'dd.MM.yyyy hh:mm"); //Создаем форматер для форматирования даты
        String dateFormat = simpleDateFormat.format(dateNode); //форматируем дату
        String result = String.format("\t\t\t%s\n%s\n\t\t\t%s",nameNotes, dateFormat, textNode);
        return result;
    }
}
