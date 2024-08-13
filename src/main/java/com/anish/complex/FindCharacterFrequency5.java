package com.anish.complex;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindCharacterFrequency5 {

    static String findCharacterFrequency(String inputString){
        Map<Character, Long> entriesMap = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(entriesMap);
        long maxCount = entriesMap.values().stream().max(Long::compare).orElse(0L);
        System.out.println(maxCount);
        Set<Map.Entry<Character, Long>> entries= entriesMap.entrySet();
        return entries.stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {

        System.out.println(findCharacterFrequency("aniisssha"));
    }
}
