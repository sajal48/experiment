package com.sajal.experiment.Algo;

import java.util.Map;

public class Fibonacci {

    static int fibonacciWithDynamic(int n, Map<Integer,Integer> ansMap) {
        if(ansMap.containsKey(n)){
            return ansMap.get(n);
        }
        int result ;
        if(n == 0){
            result =  0 ;
        }
        else if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fibonacciWithDynamic(n - 1,ansMap) + fibonacciWithDynamic(n - 2,ansMap);
        }
        ansMap.put(n, result);
        return result;
    }
    static int fibonacci(int n) {
        if(n == 0){
            return  0 ;
        }
        else if (n == 1 || n == 2) {
            return  1;
        } else {
            return   fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
