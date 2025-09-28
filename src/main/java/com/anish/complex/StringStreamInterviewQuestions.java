package com.anish.complex;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringStreamInterviewQuestions {

    // 1. Count frequency of each character
    static void charFrequency(String str) {
        System.out.println(
                str.chars().mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        );
    }

    // 2. Count frequency of each word
    static void wordFrequency(String str) {
        System.out.println(
                Arrays.stream(str.split("\\s+"))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        );
    }

    // 3. Find first non-repeated character
    static void firstNonRepeatedChar(String str) {
        Character ch = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
        System.out.println("First Non-Repeated: " + ch);
    }

    // 4. Find first repeated character
    static void firstRepeatedChar(String str) {
        Character ch = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
        System.out.println("First Repeated: " + ch);
    }

    // 5. Reverse string using streams
    static void reverseString(String str) {
        String rev = IntStream.range(0, str.length())
                .mapToObj(i -> str.charAt(str.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Reversed: " + rev);
    }

    // 6. Check if string is palindrome
    static void checkPalindrome(String str) {
        String rev = new StringBuilder(str).reverse().toString();
        System.out.println(str + " is palindrome? " + str.equals(rev));
    }

    // 7. Count vowels & consonants
    static void countVowelsConsonants(String str) {
        long vowels = str.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) >= 0).count();
        long consonants = str.replaceAll("\\s+", "").length() - vowels;
        System.out.println("Vowels=" + vowels + ", Consonants=" + consonants);
    }

    // 8. Remove duplicates characters
    static void removeDuplicates(String str) {
        String result = str.chars().mapToObj(c -> (char)c)
                .distinct().map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Without Duplicates: " + result);
    }

    // 9. Find longest word
    static void longestWord(String str) {
        String lw = Arrays.stream(str.split("\\s+"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest Word: " + lw);
    }

    // 10. Find shortest word
    static void shortestWord(String str) {
        String sw = Arrays.stream(str.split("\\s+"))
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Shortest Word: " + sw);
    }

    // 11. Anagram check
    static void checkAnagram(String s1, String s2) {
        boolean res = Arrays.equals(
                s1.chars().sorted().toArray(),
                s2.chars().sorted().toArray()
        );
        System.out.println(s1 + " & " + s2 + " Anagram? " + res);
    }

    // 12. Count uppercase & lowercase letters
    static void countCase(String str) {
        long upper = str.chars().filter(Character::isUpperCase).count();
        long lower = str.chars().filter(Character::isLowerCase).count();
        System.out.println("Upper=" + upper + ", Lower=" + lower);
    }

    // 13. Count digits
    static void countDigits(String str) {
        long digits = str.chars().filter(Character::isDigit).count();
        System.out.println("Digits Count: " + digits);
    }

    // 14. Count special characters
    static void countSpecialChars(String str) {
        long special = str.chars()
                .filter(c -> !Character.isLetterOrDigit(c) && !Character.isWhitespace(c))
                .count();
        System.out.println("Special Char Count: " + special);
    }

    // 15. Remove all whitespaces
    static void removeWhiteSpaces(String str) {
        String res = str.replaceAll("\\s+", "");
        System.out.println("Without Spaces: " + res);
    }

    // 16. Find duplicate words
    static void duplicateWords(String str) {
        Map<String, Long> freq = Arrays.stream(str.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        freq.entrySet().stream().filter(e -> e.getValue() > 1)
                .forEach(System.out::println);
    }

    // 17. Find distinct words
    static void distinctWords(String str) {
        Arrays.stream(str.split("\\s+")).distinct().forEach(System.out::println);
    }

    // 18. Sort words alphabetically
    static void sortWords(String str) {
        Arrays.stream(str.split("\\s+"))
                .sorted().forEach(System.out::println);
    }

    // 19. Sort words by length
    static void sortWordsByLength(String str) {
        Arrays.stream(str.split("\\s+"))
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);
    }

    // 20. Find word with max frequency
    static void maxFrequencyWord(String str) {
        String res = Arrays.stream(str.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("");
        System.out.println("Max Freq Word: " + res);
    }

    // 21. Reverse each word
    static void reverseEachWord(String str) {
        String res = Arrays.stream(str.split("\\s+"))
                .map(s -> new StringBuilder(s).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println("Reverse Each Word: " + res);
    }

    // 22. Count unique characters
    static void uniqueChars(String str) {
        str.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1)
                .forEach(System.out::println);
    }

    // 23. Check if all characters are unique
    static void allUniqueChars(String str) {
        boolean allUnique = str.chars().mapToObj(c -> (char)c)
                .collect(Collectors.toSet()).size() == str.length();
        System.out.println("All unique? " + allUnique);
    }

    // 24. Find common characters between two strings
    static void commonChars(String s1, String s2) {
        Set<Character> set1 = s1.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        Set<Character> set2 = s2.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        set1.retainAll(set2);
        System.out.println("Common Chars: " + set1);
    }

    // 25. Print character frequency in sorted order
    static void sortedCharFrequency(String str) {
        str.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
    }

    // 26. Find substring frequency
    static void substringFrequency(String str, String sub) {
        long count = IntStream.range(0, str.length() - sub.length() + 1)
                .mapToObj(i -> str.substring(i, i + sub.length()))
                .filter(s -> s.equals(sub)).count();
        System.out.println("Substring '" + sub + "' Count: " + count);
    }

    // 27. Remove duplicate words
    static void removeDuplicateWords(String str) {
        String res = Arrays.stream(str.split("\\s+")).distinct()
                .collect(Collectors.joining(" "));
        System.out.println("Without Duplicate Words: " + res);
    }

    // 28. Find longest palindrome word in sentence
    static void longestPalindromeWord(String str) {
        String res = Arrays.stream(str.split("\\s+"))
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .max(Comparator.comparingInt(String::length)).orElse("");
        System.out.println("Longest Palindrome Word: " + res);
    }

    // 29. Count number of lines (using \n)
    static void countLines(String str) {
        long lines = Arrays.stream(str.split("\n")).count();
        System.out.println("Line Count: " + lines);
    }

    // 30. Check if string contains only digits
    static void onlyDigits(String str) {
        boolean res = str.chars().allMatch(Character::isDigit);
        System.out.println(str + " only digits ? " + res);
    }

    public static void main(String[] args) {
        String test = "Java streams are powerful more powerful and flexible";

        charFrequency(test);
        wordFrequency(test);
        firstNonRepeatedChar(test);
        firstRepeatedChar(test);
        reverseString("hello");
        checkPalindrome("madam");
        countVowelsConsonants(test);
        removeDuplicates("programming");
        longestWord(test);
        shortestWord(test);
        checkAnagram("listen", "silent");
        countCase("HeLLoJava");
        countDigits("abc123");
        countSpecialChars("abc@123!");
        removeWhiteSpaces("Java   Streams   API");
        duplicateWords(test);
        distinctWords(test);
        sortWords(test);
        sortWordsByLength(test);
        maxFrequencyWord(test);
        reverseEachWord("Java Streams API");
        uniqueChars("balloon");
        allUniqueChars("world");
        commonChars("hello", "yellow");
        sortedCharFrequency("banana");
        substringFrequency("abababa", "aba");
        removeDuplicateWords(test);
        longestPalindromeWord("madam level racecar apple");
        countLines("line1\nline2\nline3");
        onlyDigits("12345");
    }
}