package com.anish.complex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorting {

    public static <K, V extends Comparable<V>> Map<K, V> sortByValueDescending(Map<K, V> map) {

        // Sorting based on Key
//        return map.entrySet()
//                .stream()
//                .sorted( Map.Entry.comparingByValue(Comparator.naturalOrder()) )
//                .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new ));

        // Sorting based on Value
        return map.entrySet()
                .stream()
                .sorted( Map.Entry.comparingByValue(Comparator.naturalOrder()) )
                .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new ));
    }

    public static void main(String[] args) {

        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("key1", 10);
        myMap.put("key2", 5);
        myMap.put("key3", 15);
        Map<String, Integer> sortedMap = sortByValueDescending(myMap);
        System.out.println(sortedMap);
    }
}