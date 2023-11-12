package com.javarush.logicBlock;


import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Organism;
import com.javarush.abstraction.Plant;
import com.javarush.entities.organisms.herbivores.*;
import com.javarush.entities.organisms.predators.*;

import java.util.ArrayList;
import java.util.List;

public class LiveCycleOfEachAnimal {
public static void animalLifeIndicatorReducer(List<Organism>[][] locationIsland) {
    for (int row = 0; row < locationIsland.length; row++) {
        for (int column = 0; column < locationIsland[row].length; column++) {
            List<Organism> currentListForMovement = new ArrayList<>(locationIsland[row][column]);
            List<Organism> updatedList = new ArrayList<>();
            for (int countInside = 0;countInside<currentListForMovement.size();countInside++) {
                if (currentListForMovement.get(countInside) instanceof Animal) {
                    Animal animal = (Animal) currentListForMovement.get(countInside);
                    if (animal.getFoodRequired() > 0) {
                        Animal newAnimal = identificationOfUsedInstance(animal) ;
                        double oldFoodRequiredValue = animal.getFoodRequired();
                        double differenceOfEachLifeCycle = newAnimal.getFoodRequired()*0.1;
                        double newFoodRequired = Math.round((oldFoodRequiredValue-differenceOfEachLifeCycle) * 10) / 10.0;
                        newAnimal.setFoodRequired(newFoodRequired);
                        updatedList.add(newAnimal);
                    }
                } else if(currentListForMovement.get(countInside) instanceof Plant){
                    updatedList.add(currentListForMovement.get(countInside));
                }
            }
            locationIsland[row][column] = new ArrayList<>(updatedList);
        }
    }
}
    private static Animal identificationOfUsedInstance(Animal animal){
    Animal newInstance = new Animal() {};
if (animal instanceof Woolf) {newInstance = new Woolf();}
else if(animal instanceof Bear) {newInstance = new Bear();}
else if(animal instanceof Boa) {newInstance = new Boa();}
else if(animal instanceof Eagle) {newInstance = new Eagle();}
else if(animal instanceof Fox) {newInstance = new Fox();}
else if(animal instanceof Horse) {newInstance = new Horse();}
else if(animal instanceof Boar) {newInstance = new Boar();}
else if(animal instanceof Buffalo) {newInstance = new Buffalo();}
else if(animal instanceof Caterpillar) {newInstance = new Caterpillar();}
else if(animal instanceof Deer) {newInstance = new Deer();}
else if(animal instanceof Duck) {newInstance = new Duck();}
else if(animal instanceof Goat) {newInstance = new Goat();}
else if(animal instanceof Mouse) {newInstance = new Mouse();}
else if(animal instanceof Rabbit) {newInstance = new Rabbit();}
else if(animal instanceof Sheep) {newInstance = new Sheep();}
return newInstance;
    }


}
