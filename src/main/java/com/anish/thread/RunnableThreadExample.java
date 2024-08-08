package com.anish.thread;

public class RunnableThreadExample implements Runnable {
    
    public static Thread t1;
    
    @Override
    public void run() {
        try {
            // moving thread t2 to the state timed waiting  
            Thread.sleep(100);
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }

        System.out.println("The state of thread t1 while it invoked the method join() on thread t2 -");
    }
}
