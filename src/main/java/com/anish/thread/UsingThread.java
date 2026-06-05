package com.anish.thread;

public class UsingThread {

    private static class Hi extends Thread {
        @Override
        public void run(){
            for( int i = 0; i < 5; i++ ) {
                System.out.println("Hi");
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private static class Hello extends Thread{
        @Override
        public void run(){
            for (int i =0; i<5; i++){
                System.out.println("Hello");
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
            }
        }
    }

    static void main() {
        Hi hi = new Hi();
        Hello hello = new Hello();
        hi.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hello.start();
    }
}
