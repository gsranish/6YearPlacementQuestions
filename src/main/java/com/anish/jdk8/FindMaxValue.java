package com.anish.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMaxValue {

    /**
     * Example of how to use Stream API to find maximum element in a collection.
     * This example shows two ways to find maximum element.
     * 1. Using the max() method
     * 2. Using sorting and limit() method
     */
    public static void main() {

        List<Integer> inputList = Arrays.asList(10,15,8,49,25,98,32);
        System.out.println(inputList.stream().max(Integer::compare).get());
        inputList.stream().sorted(Comparator.reverseOrder()).limit(1).forEach(System.out::println);

    }
}
