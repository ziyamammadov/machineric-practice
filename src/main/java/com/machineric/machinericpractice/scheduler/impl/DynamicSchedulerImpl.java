package com.machineric.machinericpractice.scheduler.impl;

import com.machineric.machinericpractice.client.WorldTimeClient;
import com.machineric.machinericpractice.client.dto.WorldTimeResponse;
import com.machineric.machinericpractice.mapper.WorldTimeMapper;
import com.machineric.machinericpractice.scheduler.DynamicScheduler;
import com.machineric.machinericpractice.service.WorldTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerImpl implements SchedulingConfigurer, DynamicScheduler {
    private final Map<String, ScheduledFuture> futureMap = new HashMap<>();
    private final WorldTimeClient client;
    private final WorldTimeService service;
    private final TaskScheduler scheduler;
    Logger logger = LoggerFactory.getLogger(DynamicScheduler.class);
    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    private int delay = 10;

    public DynamicSchedulerImpl(WorldTimeClient client, WorldTimeService service, TaskScheduler scheduler) {
        this.client = client;
        this.service = service;
        this.scheduler = scheduler;
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (taskRegistrar.getScheduler() == null) {
            taskRegistrar.setScheduler(scheduler);
        }
    }

    @Override
    public boolean addTask(String taskName) {
        logger.info("Adding new scheduled task");
        if (futureMap.containsKey(taskName)) {
            return false;
        }

        ScheduledFuture future = scheduledTaskRegistrar.getScheduler().schedule(this::getWorldTime, t -> {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = t.lastActualExecutionTime();
            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
            nextExecutionTime.add(Calendar.SECOND, delay);
            return nextExecutionTime.getTime();
        });

        configureTasks(scheduledTaskRegistrar);
        futureMap.put(taskName, future);
        return true;
    }

    @Override
    public boolean removeTask(String taskName) {
        logger.info("Removing scheduled task");
        if (!futureMap.containsKey(taskName)) {
            return false;
        }
        ScheduledFuture future = futureMap.get(taskName);
        future.cancel(true);
        futureMap.remove(taskName);
        return true;
    }

    @Override
    public void getWorldTime() {
        logger.info("Polling from an API");
        WorldTimeResponse currentWorldTime = client.getCurrentWorldTime();
        service.save(WorldTimeMapper.INSTANCE.worldTimeResponseToEntity(currentWorldTime));
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }
}