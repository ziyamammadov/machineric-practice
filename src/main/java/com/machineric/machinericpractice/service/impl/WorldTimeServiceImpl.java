package com.machineric.machinericpractice.service.impl;

import com.machineric.machinericpractice.entity.WorldTime;
import com.machineric.machinericpractice.exception.DataNotFoundException;
import com.machineric.machinericpractice.exception.response.ResponseMessage;
import com.machineric.machinericpractice.repository.WorldTimeRepository;
import com.machineric.machinericpractice.service.WorldTimeService;
import org.springframework.stereotype.Service;

@Service
public class WorldTimeServiceImpl implements WorldTimeService {
    private final WorldTimeRepository repository;

    public WorldTimeServiceImpl(WorldTimeRepository repository) {
        this.repository = repository;
    }

    public WorldTime get() {
        return repository.findById(1L).orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_WORLD_TIME_NOT_FOUND));
    }

    public void save(WorldTime worldTime) {
        repository.save(worldTime);
    }
}
