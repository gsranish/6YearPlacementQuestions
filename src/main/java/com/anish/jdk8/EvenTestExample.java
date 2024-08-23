package com.anish.jdk8;

import java.util.Arrays;
import java.util.List;

public class EvenTestExample {

    // Given a list of integers, find out all the even numbers that exist in the list using Stream functions ?
    public static void main(String[] args) {

        List<Integer> inputList = Arrays.asList(10,15,8,49,25,98,32);
        List<Integer> resultOutput = inputList.stream().filter(i-> i%2 == 0).toList() ;
        System.out.println(resultOutput);

    }
}

