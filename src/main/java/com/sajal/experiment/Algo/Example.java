package com.sajal.experiment.Algo;

import java.util.HashMap;

public class Example {
    public static void main(String[] args) {
//        Timer.runWithTimer(()->{
//            int fib = Fibonacci.fibonacci(57);
//            System.out.println("fib = " + fib);
//        });
        Timer.runWithTimer(()->{
            HashMap<Integer,Integer> ansMap = new HashMap<>();
            int fib = Fibonacci.fibonacciWithDynamic(1000,ansMap);
            System.out.println("fib = " + fib);
        });
        System.out.println(test(null));

    }
    public static Integer test(String s){
        return switch (s){
            case "a" -> 1;
            case "b" -> 2;
	        case null, default -> null;
        };
    }

}
