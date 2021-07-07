package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.scheduler.DynamicScheduler;
import com.machineric.machinericpractice.scheduler.impl.DynamicSchedulerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling")
@Api(value = "Operations related to polling namely start, stop and interval modification")
public class PollingController {

    private final DynamicScheduler dynamicScheduler;

    public PollingController(DynamicScheduler dynamicScheduler) {
        this.dynamicScheduler = dynamicScheduler;
    }

    @GetMapping("/start")
    @ApiOperation(value = "Starts polling by creating new scheduled task in the map", response = Boolean.class)
    public Boolean startPolling() {
        return dynamicScheduler.addTask("polling_task");
    }

    @GetMapping("/stop")
    @ApiOperation(value = "Stops polling by removing scheduled task from the map", response = Boolean.class)
    public Boolean stopPolling() {
        return dynamicScheduler.removeTask("polling_task");
    }

    @PutMapping("/{interval}")
    @ApiOperation(value = "Changes polling interval according to the interval variable passed via link", response = Integer.class)
    public Integer changeInterval(@PathVariable Integer interval) {
        dynamicScheduler.setDelay(interval);
        return dynamicScheduler.getDelay();
    }
}
