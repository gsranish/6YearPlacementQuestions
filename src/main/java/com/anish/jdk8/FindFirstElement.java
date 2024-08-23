package com.anish.jdk8;

import java.util.Arrays;
import java.util.List;

public class FindFirstElement {

    public static void main(String[] args) {

        List<Integer> inputList = Arrays.asList(10,15,8,49,25,98,32);
        inputList.stream().findFirst().ifPresent(System.out::println);
        System.out.println(inputList.stream().skip(0).findFirst().orElse(null));
        System.out.println(inputList.stream().skip(1).findFirst().orElse(null));


    }
}
