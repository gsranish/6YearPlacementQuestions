package com.anish.complex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
            out.println(firstRepeatedCh);
        }
        else {
            out.println("No Repeated");
        }

    }
    
}
