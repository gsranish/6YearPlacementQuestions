package com.anish.basics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateSecondDigitFinder {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(121, 123, 121,420,343,786,923,285,8234556);
        Set<Integer> uniqueDuplicates = findDuplicatesWithSecondDigitUnique(numbers);
        List<Integer> duplicates = findDuplicatesWithSecondDigit(numbers);
        System.out.println(" duplicates : "+ duplicates); // Output: [121, 123]
        System.out.println(" Unique Duplicates : " + uniqueDuplicates);
    }

    public static Set<Integer> findDuplicatesWithSecondDigitUnique(List<Integer> list) {
        // Extract the second digit and group by it
        Map<Character, List<Integer>> groupedBySecondDigit = list.stream()
                .filter(num -> Integer.toString(num).length() > 1)
                .collect(Collectors.groupingBy(num -> Integer.toString(num).charAt(1)));

        // Find groups with more than one element and collect them into a set
        return groupedBySecondDigit.values().stream()
                .filter(group -> group.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    public static List<Integer> findDuplicatesWithSecondDigit(List<Integer> list) {
        // Extract the second digit and group by it
        Map<Character, List<Integer>> groupedBySecondDigit = list.stream()
                .filter(num -> Integer.toString(num).length() > 1)
                .collect(Collectors.groupingBy(num -> Integer.toString(num).charAt(1)));
        // Find groups with more than one element and collect them into a set
        return groupedBySecondDigit.values().stream()
                .filter(group -> group.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}