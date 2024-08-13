package com.anish.complex;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckUniqueCharIntoString {

    // print the unique character,skip the white spaces from given string
    static void findCharacterFrequency(String inputString){
        Set<Character> collect = inputString.chars()
                .mapToObj(c -> (char) c).sorted().skip(1)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(collect);

    }
    public static void main(String[] args) {

        findCharacterFrequency("banish ani golu");
    }

    
}
