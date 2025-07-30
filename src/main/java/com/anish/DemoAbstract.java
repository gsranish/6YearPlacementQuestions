package com.anish;

public class DemoAbstract {


    public static void main(String[] args) {
        class SubClass extends AbstactClass{
        }
        SubClass c1 =new SubClass();
        c1.getMessage();
    }

}

class AbstactClass{
    public String getMessage(){
        return "hello from ";
    }
}
