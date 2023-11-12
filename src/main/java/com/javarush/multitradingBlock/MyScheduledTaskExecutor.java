package com.javarush.multitradingBlock;

import com.javarush.abstraction.Organism;
import com.javarush.logicBlock.CurrentInformation;

import java.util.List;
import java.util.concurrent.*;

public class MyScheduledTaskExecutor {

    private ScheduledExecutorService scheduledExecutorService;

    public MyScheduledTaskExecutor() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    public void scheduleRunSimulation(List<Organism>[][] island) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            int count = 1;
            boolean conditionOfContinuesGame = true;
            while (conditionOfContinuesGame) {
                Future<?> reproducerFuture = executorService.submit(new ReproducibleTask(island));
                reproducerFuture.get();
                Future<?> eaterFuture = executorService.submit(new EatableTask(island));
                eaterFuture.get();
                Future<?> moverFuture = executorService.submit(new MovableTask(island));
                moverFuture.get();
                Future<?> lifeFuture = executorService.submit(new LifeTask(island));
                lifeFuture.get();
                Future<?> infoFuture = executorService.submit(new InformationalTask(island));
                infoFuture.get();
                System.out.println("iteration : " + count);
            count++;
            conditionOfContinuesGame = CurrentInformation.hasHerbivores(island);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        System.out.println("There is not more herbivores in this island , simulation is over");
    }
}
