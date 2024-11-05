package com.example.testanddb.business.controller;

import com.example.testanddb.business.component.SharedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    private SharedState sharedState;  // TODO: does not support multi requests

    @GetMapping("/receive-first")
    public String receiveFirst() {
        String response;
        try {
            response = sharedState.waitForSecondResponse();
            return "Response from second: " + response + " | First request completed!";
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return "Error while waiting for second response.";
        } finally {
            sharedState.reset();
        }
    }

    @GetMapping("/receive-second/{message}")
    public String receiveSecond(@PathVariable String message) {
        sharedState.setSecondResponse("Second response " + message);
        return "Second response received!";
    }
}
