package com.anish.complex;

import java.util.*;
import java.util.stream.*;

public class ArraysStreamsCheatSheet {

    // 1. Kth Largest Element
    public static void kthLargest(int[] arr, int k) {
        int result = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(k - 1)
                .findFirst()
                .orElseThrow();
        System.out.println(k + "th Largest: " + result);
    }

    // 2. Remove Duplicates
    public static void removeDuplicates(int[] arr) {
        int[] unique = Arrays.stream(arr).distinct().toArray();
        System.out.println("Unique: " + Arrays.toString(unique));
    }

    // 3. Frequency of Each Element
    public static void frequencyCount(int[] arr) {
        Map<Integer, Long> freq = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println("Frequency: " + freq);
    }

    // 4. Find Duplicates Only
    public static void findDuplicates(int[] arr) {
        Set<Integer> duplicates = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Duplicates: " + duplicates);
    }

    // 5. Sum of Even Numbers
    public static void sumEven(int[] arr) {
        int sum = Arrays.stream(arr).filter(n -> n % 2 == 0).sum();
        System.out.println("Sum of Even: " + sum);
    }

    // 6. Longest String
    public static void longestString(String[] words) {
        String longest = Arrays.stream(words)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest String: " + longest);
    }

    // 7. First Non-Repeating Element
    public static void firstNonRepeating(int[] arr) {
        int result = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
        System.out.println("First Non-Repeating: " + result);
    }

    // 8. Rotate Array k times
    public static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        int[] rotated = IntStream.range(0, n)
                .map(i -> arr[(i + n - k % n) % n])
                .toArray();
        System.out.println("Rotated: " + Arrays.toString(rotated));
    }

    // 9. Common Elements in Two Arrays
    public static void commonElements(int[] arr1, int[] arr2) {
        Set<Integer> common = Arrays.stream(arr1)
                .boxed()
                .filter(num -> Arrays.stream(arr2).anyMatch(x -> x == num))
                .collect(Collectors.toSet());
        System.out.println("Common Elements: " + common);
    }

    // 10. Merge & Sort Two Arrays
    public static void mergeAndSort(int[] arr1, int[] arr2) {
        int[] merged = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .distinct()
                .sorted()
                .toArray();
        System.out.println("Merged & Sorted: " + Arrays.toString(merged));
    }

    // 11. Sliding Window Max Sum
    public static void slidingWindowMaxSum(int[] arr, int k) {
        int maxSum = IntStream.rangeClosed(0, arr.length - k)
                .map(i -> Arrays.stream(arr, i, i + k).sum())
                .max()
                .orElse(Integer.MIN_VALUE);
        System.out.println("Max sum of " + k + " consecutive: " + maxSum);
    }

    // 12. Missing Number in 1..N
    public static void missingNumber(int[] arr, int n) {
        int missing = IntStream.rangeClosed(1, n).sum() - Arrays.stream(arr).sum();
        System.out.println("Missing Number: " + missing);
    }

    // 13. Pair Sum Problem
    public static void pairSum(int[] arr, int target) {
        List<List<Integer>> pairs = IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, arr.length)
                        .filter(j -> arr[i] + arr[j] == target)
                        .mapToObj(j -> List.of(arr[i], arr[j])))
                .toList();
        System.out.println("Pairs with sum " + target + ": " + pairs);
    }

    // 14. Top N Frequent Elements
    public static void topNFrequent(int[] arr, int n) {
        List<Integer> topN = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Top " + n + " frequent: " + topN);
    }

    // 15. Parallel Stream Processing
    public static void parallelProcessing() {
        int[] bigArray = new Random().ints(1_000_0, 1, 100).toArray();
        long evenCount = Arrays.stream(bigArray).parallel().filter(n -> n % 2 == 0).count();
        System.out.println("Even Numbers Count (Parallel): " + evenCount);
    }

    // 16. Group Numbers by Even/Odd
    public static void groupEvenOdd(int[] arr) {
        Map<String, List<Integer>> grouped = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd"));
        System.out.println("Grouped: " + grouped);
    }

    // 17. Average of Squares
    public static void averageOfSquares(int[] arr) {
        double avg = Arrays.stream(arr).boxed().collect(Collectors.averagingInt(n -> n * n));
        System.out.println("Average of Squares: " + avg);
    }

    // 18. Max Subarray Sum (Kadane’s using Streams)
    public static void maxSubarraySum(int[] arr) {
        int maxSum = IntStream.range(0, arr.length)
                .mapToObj(i -> IntStream.rangeClosed(i, arr.length - 1)
                        .map(j -> Arrays.stream(arr, i, j + 1).sum())
                        .max().orElse(Integer.MIN_VALUE))
                .max(Integer::compareTo)
                .orElse(Integer.MIN_VALUE);
        System.out.println("Max Subarray Sum: " + maxSum);
    }

    // 19. Find All Subarrays with Given Sum
    public static void subarraysWithSum(int[] arr, int target) {
        List<List<Integer>> subarrays = IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i + 1, arr.length)
                        .mapToObj(j -> Arrays.copyOfRange(arr, i, j)))
                .filter(sub -> Arrays.stream(sub).sum() == target)
                .map(sub -> Arrays.stream(sub).boxed().toList())
                .toList();
        System.out.println("Subarrays with sum " + target + ": " + subarrays);
    }

    // Main to demo all
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 3, 6, 5};
        String[] words = {"Java", "Stream", "Programming", "Interview"};

        removeDuplicates(arr);
        frequencyCount(arr);
        findDuplicates(arr);
        sumEven(new int[]{3, 6, 8, 5, 10});
        longestString(words);
        firstNonRepeating(new int[]{4, 5, 1, 2, 1, 4, 5, 7});
        rotateArray(new int[]{1, 2, 3, 4, 5}, 2);
        commonElements(arr1, arr2);
        mergeAndSort(arr1, arr2);
        kthLargest(new int[]{7, 10, 4, 3, 20, 15}, 3);
        slidingWindowMaxSum(new int[]{2, 1, 5, 1, 3, 2}, 3);
        missingNumber(new int[]{1, 2, 4, 5, 6}, 6);
        pairSum(new int[]{2, 7, 11, 15}, 9);
        topNFrequent(new int[]{1,1,1,2,2,3,3,3,3,4,5}, 2);
        parallelProcessing();
        groupEvenOdd(new int[]{1,2,3,4,5,6,7});
        averageOfSquares(new int[]{2, 3, 4});
        maxSubarraySum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        subarraysWithSum(new int[]{1, 2, 3, -2, 5}, 5);
    }
}