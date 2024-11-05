package com.example.testanddb.business.controller;

import com.example.testanddb.business.component.MultiRequestSharedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiRequestAsyncController {
    @Autowired
    private MultiRequestSharedState multiRequestSharedState;

    @GetMapping("/receive-first-request/{id}")
    public String receiveFirstRequest(@PathVariable("id") String id) {
        String response;
        try {
            response = multiRequestSharedState.waitForSecondResponse(id);
            return "Response for ID " + id + ": " + response + " | First request completed!";
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return "Error while waiting for second response.";
        } finally {
            multiRequestSharedState.reset(id);
        }
    }

    @GetMapping("/receive-second-request/{id}/{message}")
    public String receiveSecondRequest(@PathVariable("id") String id, @PathVariable("message") String message) {
        multiRequestSharedState.setSecondResponse(id, "Second response: " + message);
        return "Second response received for ID " + id + "!";
    }
}
