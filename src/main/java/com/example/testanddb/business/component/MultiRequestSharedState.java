package com.example.testanddb.business.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@Component
public class MultiRequestSharedState {
    private ConcurrentHashMap<String, CompletableFuture<String>> futureMap = new ConcurrentHashMap<>();

    public void setSecondResponse(String id, String response) {
        CompletableFuture<String> future = futureMap.get(id);
        if (future != null) {
            future.complete(response);  // compute the result
        }
    }

    public String waitForSecondResponse(String id) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = futureMap.computeIfAbsent(id, k -> new CompletableFuture<>());
        return future.get();  // block the thread
    }

    public void reset(String id) {
        futureMap.remove(id);
    }
}
