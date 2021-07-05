package com.machineric.machinericpractice.controller;

import com.machineric.machinericpractice.entity.WorldTime;
import com.machineric.machinericpractice.service.WorldTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worldtime")
public class WorldTimeController {
    private WorldTimeService service;

    public WorldTimeController(WorldTimeService service) {
        this.service = service;
    }

    @GetMapping()
    public WorldTime get() {
        return service.get();
    }
}
