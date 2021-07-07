package com.machineric.machinericpractice.scheduler;

public interface DynamicScheduler {


    boolean addTask(String taskName);

    boolean removeTask(String taskName);

    void getWorldTime();

    int getDelay();

    void setDelay(int delay);

}