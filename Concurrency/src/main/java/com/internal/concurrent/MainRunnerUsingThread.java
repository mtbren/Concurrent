package com.internal.concurrent;

class TRunner1 extends Thread{
    @Override
    public void run(){
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

class TRunner2 extends Thread{
    @Override
    public void run(){
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
public class MainRunnerUsingThread {
    public static void main(String[] args) {
        Thread thread1 = new TRunner1();
        Thread thread2 = new TRunner2();

        Thread thread3 = new Thread(){
            @Override
            public void run(){
                for(int i=1;i<=10;i++){
                    System.out.println("Runner3 - "+i);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Thread execution in progress !!!");

        try {
            // Wait for the threads to die (finish execution)
            thread1.join();
            thread2.join();
            thread3.join();
        }catch(InterruptedException ie){
            throw new RuntimeException(ie);
        }
        System.out.println("Finished execution of all threads !!!");

    }
}
