package com.anish.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Pallindrom {
    
    private static Boolean isPallindrom(String input){
        return input.equalsIgnoreCase(new StringBuffer(input).reverse().toString());
    }
    public static void main(String[] args){
        List<String> inputList = new ArrayList<>();
        inputList.add("anish");
        inputList.addFirst("ana");
        out.println(inputList.stream().distinct().filter(Pallindrom::isPallindrom).collect(Collectors.toList()));
    }
}
