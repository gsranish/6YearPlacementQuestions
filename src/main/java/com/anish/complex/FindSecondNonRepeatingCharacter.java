package com.anish.complex;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class FindSecondNonRepeatingCharacter {

    public static Character findSecondNonRepeatingCharacter(String input) {
        Map<Character, Long> charFrequency = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return charFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {

        String input = "agbxfdrrrgggughghgffidsassdfg";
        Character secondNonRepeatingChar = findSecondNonRepeatingCharacter(input);
        if (secondNonRepeatingChar != null) {
            System.out.println("Second non-repeating character: " + secondNonRepeatingChar);
        } else {
            System.out.println("No second non-repeating character found");
        }
    }

}
