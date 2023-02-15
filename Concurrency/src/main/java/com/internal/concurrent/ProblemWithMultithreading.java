package com.internal.concurrent;

public class ProblemWithMultithreading {
    private static int counter = 0;
    private static void badProcess(){
        counter = 0;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    counter++;
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
    }

    private static synchronized void increment(){
        counter++;
    }
    private static void goodProcess(){
        counter = 0;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    increment();
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
    }
    public static void main(String[] args) {
        badProcess();
        // The problem here is that the value of the counter is not exactly deterministic since the counter++ is not atomic
        // Multiple threads acting on a variable in a non-atomic way could mess up its data
        System.out.println("The final value of counter = "+counter);

        // We solved the problem by creating a method for increment operation and marking it as synchronized.
        // By marking it as synchronized we ensure that only one thread executes it at a given time - effectively making it an atomic operation
        goodProcess();
        System.out.println("The final value of counter = "+counter);
    }
}
