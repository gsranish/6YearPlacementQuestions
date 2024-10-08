package com.anish;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CTZTest {

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024,11,20);
        LocalDate endDate = LocalDate.of(2025,12,20);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Days Between Start Date and End Date are:  " + daysBetween);
        long months = ChronoUnit.MONTHS.between(startDate,endDate);
        System.out.println("Months Between Start Date and End Date are:  "+months);
    }
}
