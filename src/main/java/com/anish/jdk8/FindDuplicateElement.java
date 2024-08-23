package com.anish.jdk8;

import java.util.Arrays;
import java.util.List;


// How to find duplicate elements in a given integers list in java using Stream functions?
public class FindDuplicateElement {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3,33, 2, 4, 5, 5,2, 1);
        // Find duplicate elements using Stream API
        List<Integer> duplicates = numbers.stream()
                .filter(n -> numbers.indexOf(n) != numbers.lastIndexOf(n))
                .distinct().toList();
        System.out.println("Duplicate elements: " + duplicates);
    }
}
