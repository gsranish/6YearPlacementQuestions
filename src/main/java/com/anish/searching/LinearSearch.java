package com.anish.searching;

public class LinearSearch {

    static int count = 1;
    static int linearSearch(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++){
            System.out.println(" Step taken to linear search "+ count++);
            if(arr[i] == target){
                System.out.println(arr[i]+ " Element found at index: " + i);
                return i;
            }
        }
        System.out.println(target+" Element not found ");
        return -1; // if element doesn't exist
    }

    static void main() {
        int[] arr = {7, 10, 4, 3, 20, 15,25,78,56,65,45,67,34,43,32,23};
        linearSearch(arr, 23);
        linearSearch(arr, 100);
    }
}
