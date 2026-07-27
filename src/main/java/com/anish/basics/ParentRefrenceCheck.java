package com.anish.basics;

public class ParentRefrenceCheck {
    static void main(String[] args) {
        Parent obj = new Child ();
       //  obj.n(); not allowed as method is not available in Parent
        obj.m();
    }

}

class Parent
{
    void m() {
        System.out.println("Parent m");
    }
}

class Child extends Parent
{
    void n() {
        System.out.println("Child n");
    }
}