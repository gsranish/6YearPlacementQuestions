package com.anish.complex;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatedCharacter {
    public static void main(String[] args) {
        String str ="Coforge is Software Company";

        Map<Character, Integer> map = new LinkedHashMap<>();
        for(Character ch : str.toCharArray()) {
            map.put(ch, map.containsKey(ch)?map.get(ch)+1:1);
        }
        char character = map.entrySet().stream().filter(i->i.getValue()==1).findFirst().get().getKey();
        System.out.println(character);

    }
}
