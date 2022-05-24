package com.premiumminds.internship.screenlocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class future_int {    
    
    private ExecutorService executor 
      = Executors.newSingleThreadExecutor();
    
    public Future<Integer> generate(Integer input) {        
        return executor.submit(() -> {
            return input;
        });
    }
}
