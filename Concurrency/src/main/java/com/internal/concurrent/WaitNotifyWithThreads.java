package com.internal.concurrent;

class Process{
    public void produce() throws InterruptedException{
        synchronized (this){
            System.out.println("Producer executing first line.....");
            wait();
            Thread.sleep(5000);
            System.out.println("Producer executing second line.....");
        }
    }
    public void consume() throws InterruptedException{
        Thread.sleep(5000);
        synchronized (this){
            System.out.println("Consumer executing first line.....");
            notify();
            Thread.sleep(5000);
            System.out.println("Consumer executing second line.....");
        }
    }

}
public class WaitNotifyWithThreads {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread done!!!");
    }


}
