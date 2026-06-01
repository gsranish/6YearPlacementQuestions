package com.anish.jdk8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwiceAppearInList {

    /**
     * Checks if the input array contains any duplicate values.
     * @param nums The input array of integers (can be null or empty)
     * @return true if the array contains duplicates, false otherwise
     * @throws IllegalArgumentException if the input array is null
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        
        // If array has 0 or 1 elements, it can't have duplicates
        if (nums.length <= 1) {
            return false;
        }
        
        // Convert array to set to check for duplicates
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true; // Found a duplicate
            }
        }
        return false; // No duplicates found
    }

    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> setData = new HashSet<>();
        return Arrays.stream(nums)
                .anyMatch(num -> !setData.add(num));

    }

    public static void main() {
        // Test case 1: Array with duplicates
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Array [1, 2, 3, 1] contains duplicates: " + containsDuplicate1(nums1));
        
        // Test case 2: Array without duplicates
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Array [1, 2, 3, 4] contains duplicates: " + containsDuplicate(nums2));
        
        // Test case 3: Empty array
        int[] nums3 = {};
        System.out.println("Empty array can't duplicates: " + containsDuplicate(nums3));
        
        // Test case 4: Null array (should handle gracefully)
        try {
            System.out.println("Null array contains duplicates: " + containsDuplicate(null));
        } catch (NullPointerException e) {
            System.out.println("Error: Input array cannot be null");
        }
    }
}
