package com.anish.jdk8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TwiceAppearInList {

    public static boolean containsDuplicate(int[] nums) {
        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(list);
        if(set.size() == list.size()) {
            return false;
        }
        return true;

        /* or can also try below way */
        }

    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> setData = new HashSet<>();
        return Arrays.stream(nums)
                .anyMatch(num -> !setData.add(num));

    }

    public static void main(String[] args) {
        containsDuplicate(null);
    }
}
