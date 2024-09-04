package com.anish.complex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedCharacter {

    public static void main(String[] args) {
        
        String input ="Coforge is Software Company";
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(Character ch : input.toCharArray()) {
            map.put(ch, map.containsKey(ch)?map.get(ch)+1:1);
        }
        char character = map.entrySet().stream().filter(i->i.getValue()==1).findFirst().get().getKey();
        System.out.println("First Way - > " + character);


        // In other ways
        Character character1 = input.chars().mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch)).skip(0)
                .findFirst().orElse(null);
        System.out.println( "Second Way - > " + character1);


        // In other ways
        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("Third way - > " + result);


    }
}
