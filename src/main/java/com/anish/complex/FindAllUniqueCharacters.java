package com.anish.complex;

import java.util.LinkedHashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FindAllUniqueCharacters {

    // print the unique character
    static void findAllUniqueCharacters(String inputString){
        System.out.println(String.valueOf(inputString.chars()
                .mapToObj(c->(char)c).skip(0)
                .collect(Collectors.toCollection(LinkedHashSet::new))));
    }

    static void findAllUniqueCharacters2(String inputString){
        System.out.println(inputString.chars()
                .mapToObj(c -> (char) c).skip(0)
                .collect((Collector<? super Character, Object, LinkedHashSet<Object>>)
                        Collectors.toCollection(LinkedHashSet::new)));
    }

    static void main(String[] args) {
        System.out.println("FindUniqueCharacters");
        findAllUniqueCharacters("anish is anish good boy");
    }
}
