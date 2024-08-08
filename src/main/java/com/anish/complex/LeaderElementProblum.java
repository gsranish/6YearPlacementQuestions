package com.anish.complex;

public class LeaderElementProblum {
    
    public static void main(String[] args) {
        //int arr[] ={16,17,4,3,2};
        int arr[] ={1,2,4,8,2,20,5};
        int n = arr.length;
        int max = arr[0];
        System.out.println(max);
        for(int i=0;i<n;i++){
            if(arr[i]>max){
                max= arr[i];
                System.out.println(max);
            }
        }
    }
}
