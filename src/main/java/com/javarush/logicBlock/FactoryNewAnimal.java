package com.javarush.logicBlock;

import com.javarush.abstraction.Animal;
import com.javarush.entities.organisms.herbivores.*;
import com.javarush.entities.organisms.predators.*;

public class FactoryNewAnimal {
    public static Animal factoryNewInstance (Animal animal){
        Animal newInstance = new Animal() {
        };
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
