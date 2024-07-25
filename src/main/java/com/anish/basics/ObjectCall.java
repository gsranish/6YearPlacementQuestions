package com.anish.basics;

public class ObjectCall {

    static void method1(Object object){
        System.out.println("Object class method");
    }

    static void method1(String str){
        System.out.println("String class method");
    }


    public static void main(String[] args) {
        method1(null); // prints String class method
    }



}
