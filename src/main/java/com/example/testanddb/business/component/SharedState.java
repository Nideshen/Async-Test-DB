package com.example.testanddb.business.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SharedState {
    private CompletableFuture<String> secondResponseFuture = new CompletableFuture<>();

    public void setSecondResponse(String response) {
        secondResponseFuture.complete(response);  // do not return anything
        // TODO: else from String?
    }

    public String waitForSecondResponse() throws Exception {
        // block the thread
        return secondResponseFuture.get();  // get returns the value
    }

    public void reset() {
        secondResponseFuture = new CompletableFuture<>();
    }
}
