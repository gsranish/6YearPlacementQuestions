package com.anish.complex;

import java.util.function.Function;
import java.util.stream.Collectors;

public class FindCharacterFrequency3 {

    // print the character and their count
    static void findCharacterFrequency(String inputString){
        System.out.println(inputString.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    public static void main(String[] args) {
        findCharacterFrequency("aniissha");
    }
}
