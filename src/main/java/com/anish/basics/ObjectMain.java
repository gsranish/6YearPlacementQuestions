package com.anish.basics;

public class ObjectMain {
    
    void method1(Object obj){
        System.out.println("Object class");
    }
    void method1(String obj){
        System.out.println("String class");
    }
   
    public static void main(String[] args) {
        ObjectMain m = new ObjectMain();
        m.method1(null);
    }
}
