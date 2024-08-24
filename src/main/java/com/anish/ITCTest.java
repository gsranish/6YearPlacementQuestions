package com.anish;

import java.util.Arrays;
import java.util.List;

public class ITCTest {

    public static void main(String[] args) {

        // print the sum of repeating numbers into list 98 +15 = 113
        List<Integer> integerList = Arrays.asList(10,15,8,49,25,98,98,32,15);

        int integerList1 = integerList.stream().
                filter(n-> integerList.indexOf(n) != integerList.lastIndexOf(n)).distinct().mapToInt(Integer::intValue).sum();
        System.out.println(integerList1);

    }
}
