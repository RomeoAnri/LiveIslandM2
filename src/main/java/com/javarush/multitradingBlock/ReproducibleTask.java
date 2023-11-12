package com.javarush.multitradingBlock;

import com.javarush.abstraction.Organism;
import com.javarush.entities.organisms.Herb;
import com.javarush.entities.organisms.herbivores.*;
import com.javarush.entities.organisms.predators.*;

import java.util.List;

public class ReproducibleTask implements Runnable{
    Woolf woolf = new Woolf();
    Bear bear = new Bear();
    Eagle eagle = new Eagle();
    Fox fox = new Fox();
    Boa boa = new Boa();
    Horse horse = new Horse();
    Boar boar = new Boar();
    Buffalo buffalo = new Buffalo();
    Caterpillar caterpillar = new Caterpillar();
    Deer deer = new Deer();
    Duck duck = new Duck();
    Goat goat = new Goat();
    Mouse mouse = new Mouse();
    Rabbit rabbit = new Rabbit();
    Sheep sheep = new Sheep();
    Herb herb = new Herb();
    private List<Organism>[][]rannableList;

    ReproducibleTask(List<Organism>[][]rannableList){
        this.rannableList = rannableList;
    }
    @Override
    public void run() {
        woolf.reproduce(rannableList,woolf);
        horse.reproduce(rannableList,horse);
        herb.reproduce(rannableList,herb);
        bear.reproduce(rannableList,bear);
        eagle.reproduce(rannableList,eagle);
        fox.reproduce(rannableList,fox);
        boa.reproduce(rannableList,boa);
        boar.reproduce(rannableList,boar);
        buffalo.reproduce(rannableList,buffalo);
        caterpillar.reproduce(rannableList,caterpillar);
        deer.reproduce(rannableList,deer);
        duck.reproduce(rannableList,duck);
        goat.reproduce(rannableList,goat);
        mouse.reproduce(rannableList,mouse);
        rabbit.reproduce(rannableList,rabbit);
        sheep.reproduce(rannableList,sheep);
    }

}
