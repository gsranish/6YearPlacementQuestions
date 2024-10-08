package com.anish.interview24;

import com.anish.Employee;

import java.util.*;

public class IRISTEST {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("spring", "java", "jpa", "hibernate");
        // print the smallest word in the list
        String out = words.stream().min(Comparator.reverseOrder()).get();
        long count =words.stream().count();
        System.out.println(out);
        System.out.println();

    }

}
