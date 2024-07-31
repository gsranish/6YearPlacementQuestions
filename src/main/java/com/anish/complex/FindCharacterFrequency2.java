package com.anish.complex;

public class FindCharacterFrequency2 {

    // print the character and their count
    static Character findCharacterFrequency(String inputString, Character inputChar){
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
        findCharacterFrequency("anisha",inputChar);
    }

}
