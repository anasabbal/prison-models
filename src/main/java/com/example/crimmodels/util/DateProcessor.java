package com.example.crimmodels.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateProcessor {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static LocalDateTime toDate(final  String date){
        return LocalDateTime.parse(date);
    }
    public static String toString(final LocalDateTime date){
        return date.format(formatter);
    }
}
