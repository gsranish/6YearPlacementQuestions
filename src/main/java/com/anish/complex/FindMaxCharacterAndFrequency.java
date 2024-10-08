package com.anish.complex;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMaxCharacterAndFrequency {

    static void findMaxCharacterAndFrequency(String inputString){
        Set<Map.Entry<Character, Long>> entries = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet();
        // int maxCount = entries.stream().filter(entry -> entry.getValue() == );
        // System.out.println(maxCount);
        Optional<Character> maxChar =  entries.stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        maxChar.ifPresent(c -> System.out.println("Max character : " + c + ", count: "
                + inputString.chars().filter(ch -> ch == c).count()));
    }

    public static void main(String[] args) {

        findMaxCharacterAndFrequency("aaniiisssha");
    }
}
