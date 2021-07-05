package com.machineric.machinericpractice.service;

import com.machineric.machinericpractice.entity.WorldTime;

public interface WorldTimeService {

    WorldTime get();

    void save(WorldTime worldTime);
}
