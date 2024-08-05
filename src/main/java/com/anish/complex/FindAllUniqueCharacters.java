package com.anish.complex;

import java.util.stream.Collectors;

public class FindAllUniqueCharacters {

    // print the unique character
    static void findCharacterFrequency(String inputString){
        System.out.println(inputString.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.toSet()));
    }

    public static void main(String[] args) {
        System.out.println("FindUniqueCharacters");
        findCharacterFrequency("anish is anish good boy");
    }
}
