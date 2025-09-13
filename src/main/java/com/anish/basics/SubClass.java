package com.anish.basics;

public class SubClass extends SuperClass {

    public void exampleMethod() {
        System.out.println("Sub class method");
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.exampleMethod();
        SuperClass superClass = new SuperClass();
        superClass.exampleMethod();
        SuperClass superClass2 = new SubClass();
        superClass2.exampleMethod();
    }
}
