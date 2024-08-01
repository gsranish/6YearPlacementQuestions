package com.anish.basics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyChar {
    
    private static void getFrequencyChar(String input){
        Map<Character, Long> inputChar = input.replace(" ","").chars()
                .mapToObj(c-> (char)(c))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(inputChar);
    }
    
    private static void getFrequencyString(List<String> inputs){
        Map<String, Long> stationeryCountMap = inputs.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    public static void main(String[] args) {
        String inputString = "Java Concept Of The Day";
        getFrequencyChar(inputString);
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
        getFrequencyString(stationeryList);
    }
}
