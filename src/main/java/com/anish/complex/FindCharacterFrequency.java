package com.anish.complex;

public class FindCharacterFrequency {
    // print the character and their occurrences
    static Character findCharacterFrequency(String inputString, Character inputChar){
        int count = 1;
        for(char ch : inputString.toCharArray()){
            if(inputChar.equals(ch)){
                System.out.println(inputChar + " occurs at " + count + " times" );
                count++;
            }
        
        }
        return inputChar;
    }

    public static void main(String[] args) {
        Character f = 'a';
        findCharacterFrequency("anisha",f);
    }
    
    
}
