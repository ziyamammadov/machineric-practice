package com.machineric.machinericpractice.service.impl;

import com.machineric.machinericpractice.entity.WorldTime;
import com.machineric.machinericpractice.exception.DataNotFoundException;
import com.machineric.machinericpractice.exception.response.ResponseMessage;
import com.machineric.machinericpractice.repository.WorldTimeRepository;
import com.machineric.machinericpractice.service.WorldTimeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldTimeServiceImpl implements WorldTimeService {
    private final WorldTimeRepository repository;

    public WorldTimeServiceImpl(WorldTimeRepository repository) {
        this.repository = repository;
    }

    public WorldTime get() {
        List<WorldTime> list = repository.findAll();
        if (list.size() > 0) return list.get(0);
        throw new DataNotFoundException(ResponseMessage.ERROR_WORLD_TIME_NOT_FOUND);
    }

    public void save(WorldTime worldTime) {
        repository.save(worldTime);
    }
}
