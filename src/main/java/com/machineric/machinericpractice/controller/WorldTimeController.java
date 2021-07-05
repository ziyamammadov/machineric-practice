package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.entity.WorldTime;
import com.machineric.machinericpractice.service.WorldTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worldtime")
@Api(value = "Operations related to world time entity")
public class WorldTimeController {
    private final WorldTimeService service;

    public WorldTimeController(WorldTimeService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "Returns the latest world time polled from the API", response = WorldTime.class)
    public WorldTime get() {
        return service.get();
    }
}
