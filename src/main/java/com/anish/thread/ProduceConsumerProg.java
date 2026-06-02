package com.anish.thread;

class Resource {
    int number;
    boolean valueSet = false;
    public synchronized void put(int number) throws InterruptedException {
       while (valueSet) {
           wait();
       }
        System.out.println("put Resource : " + number);
        this.number = number;
        this.valueSet = true;
        notify();

    }

    public synchronized int get() throws InterruptedException {
        while (!valueSet) {
            wait();
        }
        System.out.println("get Resource :  " + number);
        this.valueSet = false;
        notify();
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
        int i = 0;
        while (true) {
            try {
                resource.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

public class ProduceConsumerProg {

    static void main() {
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
    }

}
