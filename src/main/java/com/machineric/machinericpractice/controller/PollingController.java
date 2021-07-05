package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.scheduler.CustomDynamicScheduler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling")
@Api(value = "Operations related to polling namely start, stop and interval modification")
public class PollingController {

    private final CustomDynamicScheduler customDynamicScheduler;

    public PollingController(CustomDynamicScheduler customDynamicScheduler) {
        this.customDynamicScheduler = customDynamicScheduler;
    }

    @GetMapping("/start")
    @ApiOperation(value = "Starts polling by creating new scheduled task in the map", response = Boolean.class)
    public Boolean startPolling() {
        return customDynamicScheduler.addTask("polling_task");
    }

    @GetMapping("/stop")
    @ApiOperation(value = "Stops polling by removing scheduled task from the map", response = Boolean.class)
    public Boolean stopPolling() {
        return customDynamicScheduler.removeTask("polling_task");
    }

    @PutMapping("/{interval}")
    @ApiOperation(value = "Changes polling interval according to the interval variable passed via link", response = Integer.class)
    public Integer changeInterval(@PathVariable Integer interval) {
        customDynamicScheduler.setDelay(interval);
        return customDynamicScheduler.getDelay();
    }
}
