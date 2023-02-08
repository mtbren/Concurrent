package com.internal.concurrent;

class Runner1 implements Runnable{
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.println("Runner1 - "+i);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Runner2 implements Runnable{
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.println("Runner2 - "+i);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class MainRunnerUsingRunnable {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner1());
        Thread thread2 = new Thread(new Runner2());
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++){
                    System.out.println("Runner3 - "+i);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
