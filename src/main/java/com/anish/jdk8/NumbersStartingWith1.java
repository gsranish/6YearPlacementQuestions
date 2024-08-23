package com.anish.jdk8;

import java.util.Arrays;
import java.util.List;

public class NumbersStartingWith1 {

    public static void main(String[] args) {

        List<Integer> inputList = Arrays.asList(10,15,8,49,25,98,32);
        String whichNumberWanttoSearch = "1";
        List<String> resultOutput = inputList.stream().map(s->s + "").filter(s-> s.startsWith(whichNumberWanttoSearch)).toList();
        System.out.println(resultOutput);

    }
}
