package com.internal.concurrent;

class LowPriorityRunner implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("Low Priority Runner - "+i);
        }
    }
}

class HighPriorityRunner implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("High Priority Runner - "+i);
        }
    }
}
public class PriorityRunnerUsingThreads {
    public static void main(String[] args) {
        Thread lpThread = new Thread(new LowPriorityRunner());
        Thread hpThread = new Thread(new HighPriorityRunner());

        lpThread.setPriority(Thread.MIN_PRIORITY);  // 1
        hpThread.setPriority(Thread.MAX_PRIORITY);  // 10
        // Default priority is Thread.NORM_PRIORITY = 5


        lpThread.start();
        hpThread.start();

        System.out.println("Main Thread completed execution !!!");
    }
}
