package com.anish.complex;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAllRepeatedCharacters {

    public static void main(String[] args) {

        String str ="Coforge Software Company";
        List<Character> charlist = str.chars()
                .filter(c-> !Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .filter(ch -> str.indexOf(ch) != str.lastIndexOf(ch))
                .distinct().sorted().toList();
        System.out.println( "First Way - > " + charlist);

        Set<Character> characterlist = str.chars()
                .filter(c->!Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .filter(ch -> str.indexOf(ch) != str.lastIndexOf(ch))
                .collect(Collectors.toSet());
        System.out.println( "Second Way - > " + characterlist);
    }
}
