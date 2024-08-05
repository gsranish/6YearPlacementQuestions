package com.anish.complex;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class FindUniqueCharacter {

    // print the unique character
    static void findUniqueCharacter(String inputString){
        Character result  = inputString.chars()
                // First convert to Character object and then to lowercase  
                .mapToObj(s -> Character.toLowerCase((char) s))
                //Store the chars in map with count 
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) 
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println("FindUniqueCharacter");
        findUniqueCharacter("anish is anish good boy");
    }
}
