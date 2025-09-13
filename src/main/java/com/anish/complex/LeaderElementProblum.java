package com.anish.complex;

public class LeaderElementProblum {

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 8, 2, 20, 5};
        int n = arr.length;
        int maxFromRight = arr[n - 1];
        System.out.print(maxFromRight + " "); // The rightmost element is always a leader

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                System.out.print(maxFromRight + " ");
            }
        }
    }
}
