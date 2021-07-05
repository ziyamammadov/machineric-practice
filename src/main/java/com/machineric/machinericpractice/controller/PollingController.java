package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.scheduler.CustomDynamicScheduler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling")
public class PollingController {

    private final CustomDynamicScheduler customDynamicScheduler;

    public PollingController(CustomDynamicScheduler customDynamicScheduler) {
        this.customDynamicScheduler = customDynamicScheduler;
    }

    @GetMapping("/start")
    public Boolean startPolling() {
        return customDynamicScheduler.addTask("polling_task");
    }

    @GetMapping("/stop")
    public boolean stopPolling() {
        return customDynamicScheduler.removeTask("polling_task");
    }

    @PutMapping("/{interval}")
    public int changeInterval(@PathVariable Integer interval) {
        customDynamicScheduler.setDelay(interval);
        return customDynamicScheduler.getDelay();
    }
}
