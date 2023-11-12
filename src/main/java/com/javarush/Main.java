package com.javarush;


import com.javarush.abstraction.Organism;
import com.javarush.logicBlock.InitializingIslandLocation;
import com.javarush.multitradingBlock.MyScheduledTaskExecutor;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Organism>[][] island;
        InitializingIslandLocation islandLocation = new InitializingIslandLocation();
        island = islandLocation.initGameFields();

        MyScheduledTaskExecutor service = new MyScheduledTaskExecutor();
        service.scheduleRunSimulation(island);


    }
}