package com.javarush.multitradingBlock;

import com.javarush.abstraction.Organism;
import com.javarush.logicBlock.CurrentInformation;

import java.util.List;

public class InformationalTask implements Runnable{
    private List<Organism>[][]rannableList;

    InformationalTask(List<Organism>[][]rannableList){
        this.rannableList = rannableList;
    }
    @Override
    public void run() {
        CurrentInformation.printInfoAboutIsland(rannableList);
    }
}
