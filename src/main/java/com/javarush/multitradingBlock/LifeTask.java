package com.javarush.multitradingBlock;

import com.javarush.abstraction.Organism;
import com.javarush.logicBlock.LiveCycleOfEachAnimal;

import java.util.List;

public class LifeTask implements Runnable {
    private List<Organism>[][]rannableList;

    LifeTask(List<Organism>[][]rannableList){
        this.rannableList = rannableList;
    }
    @Override
    public void run() {
        LiveCycleOfEachAnimal.animalLifeIndicatorReducer(rannableList);
    }
}
