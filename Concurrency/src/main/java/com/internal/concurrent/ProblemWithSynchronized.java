package com.internal.concurrent;

public class ProblemWithSynchronized {
    private static int counter1;
    private static int counter2;

    private static void increment1(){

        // Rule of thumb : Synchronize blocks of code that are 100% necessary instead of synchronizing the whole method
        synchronized (ProblemWithSynchronized.class){
            counter1++;
        }
    }

    private static void increment2(){
        // Although we are incrementing 2 different counters in two different threads, since the blocks are synchronized,
        // the threads cannot run in parallel. So it is similar to running sequentially.
        synchronized (ProblemWithSynchronized.class){
            counter2++;
        }
    }
    private static void process(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5000;i++){
                    increment2();
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
        process();
        System.out.println(counter1);
        System.out.println(counter2);
    }
}
