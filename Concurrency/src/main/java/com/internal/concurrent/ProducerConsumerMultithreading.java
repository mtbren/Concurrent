package com.internal.concurrent;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerMultithreading {
    private static final List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private static int value = 0;
    private static final Object lock = new Object();

    private void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for Consumer to consume...");
                    lock.notify();
                    lock.wait();
                    value=0;
                } else if (list.size() < UPPER_LIMIT) {
                    System.out.println("Produced - "+value);
                    list.add(value++);
                    //lock.notify();
                    Thread.sleep(500);
                }
            }
        }
    }

    private void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true){
                if(list.size()==LOWER_LIMIT){
                    System.out.println("Waiting for Producer to produce...");
                    lock.notify();
                    lock.wait();
                }else if (list.size() > LOWER_LIMIT){
                    System.out.println("Consumed - "+list.remove(list.size()-1));

                    Thread.sleep(500);
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerMultithreading p = new ProducerConsumerMultithreading();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
