package com.javarush.multitradingBlock;

import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Organism;

import java.util.List;

public class MovableTask implements Runnable {
    private List<Organism>[][]rannableList;

    MovableTask(List<Organism>[][]rannableList){
        this.rannableList = rannableList;
    }
    @Override
    public void run() {
        Animal.move(rannableList);

    }
}
