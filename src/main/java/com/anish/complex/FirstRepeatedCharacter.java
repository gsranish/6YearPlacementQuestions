package com.anish.complex;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class FirstRepeatedCharacter {
    
    public static void main(String[] args) {
        
        String str ="Coforge Software Company";
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        str.chars().mapToObj(c->(char)c).forEach(c->charCountMap.merge(c, 1, Integer::sum));
        Optional<Character> firstRepeatedCh = charCountMap.entrySet()
                .stream().filter(entry->entry.getValue()>1)
                .map(Map.Entry::getKey).findFirst();
        if(firstRepeatedCh.get() !='\0'){
            out.println(firstRepeatedCh.get());
        }
        else {
            out.println("No Repeated");
        }
        Character result = str.chars() // Stream of String
                .mapToObj(s -> Character.valueOf((char) s)) // First convert to Character object
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .skip(0)
                .findFirst()
                .get();
        System.out.println("Second Way - > " + result);

        // In other ways
        Character character1 = str.chars().mapToObj(c -> (char) c)
                .filter(ch -> str.indexOf(ch) != str.lastIndexOf(ch))
                .findFirst().orElse(null);
        System.out.println( "Third Way - > " + character1);

    }
    
}
