package com.anish.complex;

public class BoatPeople {

    public static int boatPeople(int[] people, int limit){
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        while (left <= right){
            if (people[left] + people[right] <= limit){
                left++;
            }
            right--;
            boats++;
        }
        return boats;

    }

    static void main(String[] args) {

        int[] people = {3, 2, 2, 1};
        int limit = 3;
        System.out.println(boatPeople(people, limit));
    }
}
