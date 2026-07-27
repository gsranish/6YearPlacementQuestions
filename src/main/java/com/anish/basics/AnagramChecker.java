package com.anish.basics;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class AnagramChecker {
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : str1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        for (char c : str2.toCharArray()) {
            Integer count = charCountMap.getOrDefault(c, 0);
            if (count == 0) {
                return false;
            }
            charCountMap.put(c, count - 1);
        }
        return true;
    }

    // Alternative Logic: Using Sorting Approach
    public static boolean isAnagramSorting(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    static void main() {
        String str1 = "listen";
        String str2 = "silent";
        // Original logic
        Boolean isAnagram = isAnagram(str1, str2);
        System.out.println(str1 + " and " + str2 + " are anagrams (HashMap method): " + isAnagram);
        // Alternative logic
        Boolean isAnagramSort = isAnagramSorting(str1, str2);
        System.out.println(str1 + " and " + str2 + " are anagrams (Sorting method): " + isAnagramSort);
    }
}
