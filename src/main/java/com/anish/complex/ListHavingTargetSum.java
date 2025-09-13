package com.anish.complex;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ListHavingTargetSum {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        int targetSum =11;
        List<List<Integer>> uniquePairs = numbers.stream()
                .flatMap(i -> numbers.stream()
                        .filter(j -> i <= j && i + j == targetSum)
                        .map(j -> Arrays.asList(i, j)))
                .toList();
        // In another way
        List<List<Integer>> uniquePairs2 = IntStream.range(0, numbers.size())
                .boxed()
                .flatMap(i -> IntStream.range(i, numbers.size())
                        .filter(j -> numbers.get(i) + numbers.get(j) == targetSum)
                        .mapToObj(j -> Arrays.asList(numbers.get(i), numbers.get(j))))
                .toList();
        System.out.println(uniquePairs);
        System.out.println(uniquePairs2);
    }
}
