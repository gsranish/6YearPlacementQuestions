package com.anish.basics;

import java.util.ArrayList;
import java.util.List;

public class CharFrequency {

    public static void main(String[] args) {
        String input = "AAABBCCAAEEE";
        List<String> charsC = new ArrayList<>();
        int count = 1;
        for(int i = 1; i<input.length(); i++ ) {
            if(input.charAt(i) == input.charAt(i-1)) {
                count++;
            }
            else {
                charsC.add( " " + input.charAt(i-1) + count );
                count = 1 ;
            }
        }
        //add the least sequence
        charsC.add( " " + input.charAt(input.length()-1) + count );
        String result = String.join(" ", charsC);

        System.out.println(result);

    }
}
