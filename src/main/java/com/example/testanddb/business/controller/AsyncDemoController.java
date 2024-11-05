package com.example.testanddb.business.controller;

import com.example.testanddb.business.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncDemoController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/demo-start-async")
    public String startAsyncTask() {
        asyncService.performAsyncTask();
        return "Async task started!";
    }

    @GetMapping("/demo-wait-for-async")
    public String waitForAsyncTask() throws Exception {
        CompletableFuture<String> future = asyncService.performAsyncTask();
        return future.get();
    }

    @GetMapping("/demo-start-async2")
    public String startAsyncTask2() {
        asyncService.performAsyncTask();
        return "Async task2 started!";
    }
}
