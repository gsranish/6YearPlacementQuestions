package com.anish.basics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class EvenOddFilter {
    private static final List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87,21);
    
    private static void getEvenOddNumbers1(List<Integer> listOfIntegers){
        List<Integer> evenNumber = listOfIntegers.stream().filter(i-> i%2==0).collect(Collectors.toList());
        List<Integer> oddNumber = listOfIntegers.stream().filter(i-> i%2!=0).collect(Collectors.toList());
        System.out.println(evenNumber);
        System.out.println("*********************");
        System.out.println(oddNumber);
    }

    private static void getEvenOddNumbers2(List<Integer> listOfIntegers){
        Map<Boolean, List<Integer>> oddEvenNumbersMap = listOfIntegers.stream().collect(Collectors.partitioningBy(i-> i%2 ==0));
        Set<Entry<Boolean, List<Integer>>> entrySet = oddEvenNumbersMap.entrySet();
        for (Entry<Boolean, List<Integer>> entry : entrySet) {
            if(entry.getKey()) {
                System.out.println("Even Numbers");
                System.out.println(entry.getValue());
            } else {
                System.out.println("Odd Numbers");
                System.out.println(entry.getValue());
            }
            List<Integer> list = entry.getValue();
            for (int i : list) {
                System.out.println(i);
            }
        }
    }
    
    public static void main(String[] args) {
        getEvenOddNumbers1(listOfIntegers);
        System.out.println("In other ways Internet method ");
        getEvenOddNumbers2(listOfIntegers);
    }
}
