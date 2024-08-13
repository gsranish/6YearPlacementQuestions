package com.anish.basics;

import java.sql.Date;
import java.time.LocalDate;

public class DateConvert {

    static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new Date(dateToConvert.getTime()).toLocalDate();
    }

    public static void main(String[] args)
    {
        Date date = new Date(2024,2,2);
        System.out.println(convertToLocalDateViaSqlDate(date));
    }
}
