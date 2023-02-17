package com.internal.concurrent;

public class ProblemWithSynchronizedSolvedWithLocks {
    private static int counter1;
    private static int counter2;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static void increment1(){
        // By synchronizing on 2 different object locks, the independent threads
        // don't have to wait for each other. They can run in parallel
        synchronized (lock1) {
            counter1++;
        }
    }
    private static void increment2(){
        synchronized (lock2) {
            counter2++;
        }
    }
    private static void process(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
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
        System.out.println("Counter1="+counter1);
        System.out.println("Counter2="+counter2);
    }
}
