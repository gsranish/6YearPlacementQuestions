package com.anish.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        final int[] left = {0};  // Using an array to modify it inside the stream

        // Use IntStream to iterate over the string's indices
        return IntStream.range(0, s.length())
                .map(right -> {
                    char currentChar = s.charAt(right);
                    if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left[0]) {
                        left[0] = charIndexMap.get(currentChar) + 1;
                    }
                    charIndexMap.put(currentChar, right);
                    return right - left[0] + 1;
                })
                .max()  // Find the maximum window length
                .orElse(0);  // In case the string is empty
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));  // Output: 3
    }
}
