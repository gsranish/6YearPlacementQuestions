package com.anish.jdk17;

sealed interface Human permits Manish, Anjali
{
    void printName();
}

non-sealed class Manish implements Human
{
    public void printName()
    {
        System.out.println("Manish Sharma");
    }
}

/*  not valid
sealed class Vartika implements Human
{
    public void printName()
    {
        System.out.println("Vartika Dadheech");
    }
}
*/

final class Anjali implements Human
{
    public void printName()
    {
        System.out.println("Anjali Sharma");
    }
}

public class TestSealedInterface {


    public static void main(String[] args) {
        SealedInterface sealedInterface = new FinalClass1();
        sealedInterface.shut();
        FinalClass1 finalClass1 = new FinalClass1();
        finalClass1.shut();
            Human h1 = new Anjali();
            // Human h2 = new Vartika();
            Human h3 = new Manish();
            h1.printName();
            // h2.printName();
            h3.printName();
    }
}
