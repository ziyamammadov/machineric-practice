package com.machineric.machinericpractice.service;

import com.machineric.machinericpractice.entity.WorldTime;
import com.machineric.machinericpractice.exception.DataNotFoundException;
import com.machineric.machinericpractice.exception.response.ResponseMessage;
import com.machineric.machinericpractice.repository.WorldTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class WorldTimeService {
    private WorldTimeRepository repository;

    public WorldTimeService(WorldTimeRepository repository) {
        this.repository = repository;
    }

    public WorldTime get() {
        WorldTime worldTime = repository.findAll().get(0);
        if (worldTime != null) return worldTime;
        throw new DataNotFoundException(ResponseMessage.ERROR_WORLD_TIME_NOT_FOUND);
    }

    public void save(WorldTime worldTime){
        repository.save(worldTime);
    }
}
