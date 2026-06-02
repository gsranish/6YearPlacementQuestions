package com.anish.thread;

class Hi implements Runnable {
    public void run(){
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hi");
        }

    }
}
class Hello implements Runnable {
    public void run(){
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello");
        }
    }

}
public class RunnableThreadExample {

    static void main() throws InterruptedException {
        Hi hi = new Hi();
        Hello hello = new Hello();;
        hi.run();
        Thread.sleep(50);
        hello.run();
        System.out.println("------------------");
        // right way to call thread
        Thread t1= new Thread(hi,"Hi Thread");
        Thread t2=new Thread(hello,"Hello Thread");
        t1.start();
        t2.start();
    }

}
