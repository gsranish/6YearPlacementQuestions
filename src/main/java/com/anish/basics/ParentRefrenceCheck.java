package com.anish.basics;

public class ParentRefrenceCheck {
    public static void main(String[] args) {
        Parent obj = new Child ();
       //  obj.n(); not allowed as method is not available in Parent
        obj.m();
    }

}

class Parent
{
    void m()
    {}
}

class Child extends Parent
{
    void n()
    {}
}