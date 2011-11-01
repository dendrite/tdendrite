package ru.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 *
 *
 */
public class TTime {

    public static void main(String[] args){
        Locale locale = Locale.UK;
        Date date = new Date();

        String s = DateFormat.getTimeInstance(DateFormat.SHORT, locale).format(date);

        //String s = DateFormat.getTimeInstance(DateFormat.SHORT, locale).format(date);
        System.out.println( s );
    }

}
