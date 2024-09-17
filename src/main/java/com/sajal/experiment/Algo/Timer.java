package com.sajal.experiment.Algo;

public class Timer {
     static void runWithTimer(Runnable task ){
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed Time: "+elapsedTime +" milli seconds");
    }
}
