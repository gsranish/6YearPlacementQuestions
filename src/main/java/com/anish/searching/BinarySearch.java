package com.anish.searching;

public class BinarySearch {

    static int count = 1;
    static int binarySearch(int[] arr,int target) {
        int low =0;
        int high = arr.length-1;
        while (low <= high) {
            System.out.println(" Step taken to binary search "+ count++);
            int mid = low + (high - low)/2;
            if (arr[mid] == target) {
                System.out.println(arr[mid]+ " Element found at index: " + mid);
                return mid;
            }
            if (arr[mid] < target) {
                low = mid+1;
            }
            if (arr[mid] > target) {
                high = mid-1;
            }
        }
        System.out.println(target+" Element not found ");
        return -1;
    }

    static void main() {
        int[] arr = {7, 10, 4, 3, 20, 15,25,78,56,65,45,67,34,43,32,23};
        binarySearch(arr, 10);
    }
}
