package com.anish.basics;

import java.util.stream.IntStream;

import static java.lang.System.out;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        String cleanedStr = str.replaceAll("\\s+", "").toLowerCase();
        int length = cleanedStr.length();
        return IntStream.range(0, length/2)
                .noneMatch(i -> cleanedStr.charAt(i) != cleanedStr.charAt(length - i - 1));
    }
    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        boolean isPalindrome = isPalindrome(input);
        out.println(input + " is a palindrome: " + isPalindrome);
    }
}
