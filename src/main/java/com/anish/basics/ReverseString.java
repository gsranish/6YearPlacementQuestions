package com.anish.basics;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseString {

    public static void reverseString(String inputString) {
        int length = inputString.length();
        String reversedString = IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(inputString.charAt(length - 1 - i)))
                .map(String::valueOf).collect(Collectors.joining());
        System.out.println(reversedString);
    }

    static void main() {
        String inputString = "Anish Mahan";
        int length = inputString.length();
        String reversedString = IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(inputString.charAt(length - 1 - i)))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        System.out.println(reversedString);
        reverseString("Hello, World!");
    }
}
