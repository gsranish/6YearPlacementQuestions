package com.anish.basics;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDup {
    
    private static void removeDuplicate(List<String> stringList){
        List<String> distinctStrings = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctStrings);
        for(String input : distinctStrings)
            System.out.println(input);
    }

    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        System.out.println(LocalDate.now());
        removeDuplicate(listOfStrings);
    }
    
    
}
