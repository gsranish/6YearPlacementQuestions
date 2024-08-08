package com.anish.basics;

import java.util.stream.IntStream;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        String cleanedStr = str.replaceAll("\\s+", "").toLowerCase();
        int length = cleanedStr.length();

        int halfLength = length / 2;

        return IntStream.range(0, halfLength)
                .noneMatch(i -> cleanedStr.charAt(i) != cleanedStr.charAt(length - i - 1));
    }

    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        boolean isPalindrome = isPalindrome(input);
        System.out.println(input+ " is a palindrome: " + isPalindrome);
    }
}
