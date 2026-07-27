package com.anish.basics;

import java.util.ArrayList;
import java.util.List;

public class CharFrequency {

    static void main() {
        String input = "AAABBCCAAEEE";
        List<String> charsC = new ArrayList<>();
        int count = 1;
        for(int i = 1; i < input.length(); i++ ) {
            if(input.charAt(i) == input.charAt(i-1)) {
                count++;
            }
            else {
                charsC.add( " " + input.charAt(i-1) + count );
                count = 1 ;
            }
        }
        //add the least sequence
        charsC.add( " " + input.charAt(input.length()-1) + count );
        String result = String.join(" ", charsC);
        System.out.println("Original Approach: " + result);
        // Alternative Logic 2: Using HashMap
        String result3 = compressHashMapApproach(input);
        System.out.println("HashMap Approach: " + result3);
    }
    // Alternative Logic 2: Using HashMap to track character frequencies with positions
    static String compressHashMapApproach(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char currentChar = input.charAt(i);
            int count = 1;
            // Count consecutive identical characters
            while (i + 1 < input.length() && input.charAt(i + 1) == currentChar) {
                count++;
                i++;
            }
            result.append("  ").append(currentChar).append(count);
            i++;
        }
        return result.toString();
    }
}
