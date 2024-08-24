package com.anish;

public class VOLKATEST {

    public static void main(String[] args) {

        String input = "abcabcb";
        int n = input.length();
        int indexAt = (int) input
                .chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch))
                .count();
        System.out.println(indexAt);
        System.out.println(input.substring(indexAt));
    }
}
