package com.anish.design_pattern;

/*
    Why is it Thread-Safe?
	1.	All fields are final → no visibility issues between threads.
	2.	Defensive copies → each thread works on its own Address instance.
	3.	No setters or mutable state → cannot modify once created.
	4.	Deep copy ensures nested mutable objects don’t leak references.
 */

public class SingletonDemo {
    public static void main(String[] args) throws InterruptedException {

        Address addr = new Address("Pune", "MH");
        Employee emp = new Employee(101, "Anish", addr);

        System.out.println("Initial Employee: " + emp);

        // Thread 1 - trying to modify the original Address
        Thread t1 = new Thread(() -> {
            addr.setCity("Delhi");
            System.out.println("Thread 1 changed Address: " + addr);
        });

        // Thread 2 - trying to modify the Address fetched via getter
        Thread t2 = new Thread(() -> {
            Address eAddr = emp.getAddress();
            eAddr.setCity("Mumbai");
            System.out.println("Thread 2 changed Getter Address: " + eAddr);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // ✅ Employee remains unaffected
        System.out.println("Final Employee: " + emp);
    }
}
