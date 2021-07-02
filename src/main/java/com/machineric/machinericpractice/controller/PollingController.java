package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.entity.Polling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollingController {

    @GetMapping("/polling/start")
    public String startPolling(){
        return "OK";
    }

    @GetMapping("/polling/stop")
    public String stopPolling(){
        return "OK";
    }

    @GetMapping()
    public Polling get(){
        return null;
    }
}
