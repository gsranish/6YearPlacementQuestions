package com.anish.complex;

public class FindCharacterAndFrequency {

    // print the character and their count
    static Character findCharacterAndFrequency(String inputString, Character inputChar){
        int count = 0;
        for(char ch : inputString.toCharArray()){
            if(inputChar.equals(ch)){
                count++;
            }
        }
        System.out.println(inputChar + " occurs at " + count + " times" );
        return inputChar;
    }
    public static void main(String[] args) {

        Character inputChar = 'a';
        findCharacterAndFrequency("anisha",inputChar);
    }

}
