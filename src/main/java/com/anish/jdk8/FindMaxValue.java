package com.anish.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMaxValue {

    public static void main(String[] args) {

        List<Integer> inputList = Arrays.asList(10,15,8,49,25,98,32);
        System.out.println(inputList.stream().max(Integer::compare).get());
        inputList.stream().sorted(Comparator.reverseOrder()).limit(1).forEach(System.out::println);

    }
}
