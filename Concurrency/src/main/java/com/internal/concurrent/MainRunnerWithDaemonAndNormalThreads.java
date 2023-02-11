package com.internal.concurrent;

class NRunner1 extends Thread{
    public void run(){
        try {
            System.out.println("NRunner1 sleeping ....");
            Thread.sleep(10000);
            System.out.println("NRunner1 done sleeping !!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class DRunner2 implements Runnable{
    @Override
    public void run() {
        // Even though this is an infinite while loop, it ends (terminated by JVM) when other threads are done execution.
        // This is because this thread is marked as Daemon thread
        while(true){
            System.out.println("DRunner2 working ....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class MainRunnerWithDaemonAndNormalThreads {
    public static void main(String[] args) {
        Thread nRunner1 = new NRunner1();
        Thread dRunner2 = new Thread(new DRunner2());

        dRunner2.setDaemon(true);

        nRunner1.start();
        dRunner2.start();

    }
}
