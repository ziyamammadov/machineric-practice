package com.machineric.machinericpractice.scheduler;

import com.machineric.machinericpractice.client.WorldTimeClient;
import com.machineric.machinericpractice.client.dto.WorldTimeResponse;
import com.machineric.machinericpractice.mapper.WorldTimeMapper;
import com.machineric.machinericpractice.service.WorldTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class CustomDynamicScheduler implements SchedulingConfigurer {
    Logger logger = LoggerFactory.getLogger(CustomDynamicScheduler.class);

    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    private final Map<String, ScheduledFuture> futureMap = new HashMap<>();
    private int delay = 3;
    private final WorldTimeClient client;
    private final WorldTimeService service;

    public CustomDynamicScheduler(WorldTimeClient client, WorldTimeService service) {
        this.client = client;
        this.service = service;
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (taskRegistrar.getScheduler() == null) {
            taskRegistrar.setScheduler(poolScheduler());
        }
    }

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

    public void getWorldTime() {
        logger.info("====== Polling ======");
        WorldTimeResponse currentWorldTime = client.getCurrentWorldTime();
        service.save(WorldTimeMapper.INSTANCE.worldTimeResponseToEntity(currentWorldTime));
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}