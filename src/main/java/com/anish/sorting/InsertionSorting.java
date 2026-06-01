package com.anish.sorting;

public class InsertionSorting {

    static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j=j-1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Original Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        int[] sortedArr = insertionSort(arr);
        System.out.println();
        System.out.print("Sorted Array: ");
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
    }
}
