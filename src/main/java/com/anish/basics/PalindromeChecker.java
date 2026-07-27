package com.anish.basics;

import java.util.stream.IntStream;

import static java.lang.System.out;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        String cleanedStr = str.replaceAll("\\s+", "").toLowerCase();
        out.printf("Cleaned String: '%s'%n", cleanedStr);
        int length = cleanedStr.length();
        return IntStream.range(0, length/2)
                .noneMatch(i -> cleanedStr.charAt(i) != cleanedStr.charAt(length - i - 1));
    }
    static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        String input1 ="H e H";
        boolean isPalindrome = isPalindrome(input);
        boolean isPalindrome1 = isPalindrome(input1);
        out.println(input + " is a palindrome: " + isPalindrome);
        out.println(input + " is a palindrome: " + isPalindrome1);
    }
}
