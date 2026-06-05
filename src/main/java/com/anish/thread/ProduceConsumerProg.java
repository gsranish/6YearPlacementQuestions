package com.anish.thread;

class Resource {
    int number;
    boolean valueSet = false;
    public synchronized void put(int number) throws InterruptedException {
       while (valueSet) {
           wait();
       }
        System.out.println("Put Resource : " + number);
        this.number = number;
        this.valueSet = true;
        notifyAll();
    }
    public synchronized int get() throws InterruptedException {
        while (!valueSet) {
            wait();
        }
        System.out.println("Get Resource :  " + number);
        this.valueSet = false;
        notifyAll();
        return number;
    }
}
class Producer implements Runnable {
    private final Resource resource;
    public Producer(Resource resource) {
        this.resource = resource;
        Thread t = new Thread(this,"Producer Thread");
        t.start();
    }
    @Override
    public void run() {
        int i = 0;
        // infinite loop to produce the resource
        while (true) {
            try {
                resource.put(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Consumer implements Runnable {
    private final Resource resource;
    public Consumer(Resource resource) {
        this.resource = resource;
        Thread t = new Thread(this,"Consumer Thread");
        t.start();
    }
    @Override
    public void run() {
        // infinite loop to consume the resource
        while (true) {
            try {
                resource.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
public class ProduceConsumerProg {
    static void main() {
        Resource resource = new Resource();
        new Producer(resource);
        new Consumer(resource);
    }
}
