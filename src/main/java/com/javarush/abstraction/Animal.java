package com.javarush.abstraction;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.entities.Island;
import com.javarush.entities.organisms.herbivores.*;
import com.javarush.entities.organisms.predators.*;
import com.javarush.logicBlock.FactoryNewAnimal;
import com.javarush.logicBlock.RandomProbabilityGenerator;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;
import java.util.*;

public abstract class Animal implements  Organism{

    private static Island island = new Island();
    private static RandomProbabilityGenerator generator = new RandomProbabilityGenerator();
    private static int randomRow;
    private static int randomColumn;
    protected double weight;
    protected int maxQuantityPerCell;
    protected int movementSpeedPerTurn;
    protected double foodRequired;

    public Animal(int weight, int maxQuantityPerCell, int movementSpeedPerTurn, double foodRequired) {
        this.weight = weight;
        this.maxQuantityPerCell = maxQuantityPerCell;
        this.movementSpeedPerTurn = movementSpeedPerTurn;
        this.foodRequired = foodRequired;
    }
    public Animal() {
    }

    public static void eat(List<Organism>[][] location){
        for (int row = 0; row < island.getRows(); row++) {
            for (int column = 0; column < island.getColumns(); column++) {
                List<Organism> actualIteratedList = new ArrayList<>(location[row][column]);
                List<Integer> indicesToRemove = new ArrayList<>();
                Set<Integer> eatenIndices = new HashSet<>();
                for (int countSearchinPredator = 0; countSearchinPredator < actualIteratedList.size(); countSearchinPredator++) {
                    if (eatenIndices.contains(countSearchinPredator)) {continue;}
                    Organism organism =  actualIteratedList.get(countSearchinPredator);
                    if (organism instanceof Predator) {
                        for (int countSearchinPrey = 0; countSearchinPrey < actualIteratedList.size(); countSearchinPrey++) {
                            if (eatenIndices.contains(countSearchinPrey)) {continue;}
                            Organism prey =  actualIteratedList.get(countSearchinPrey);
                            if (prey instanceof Herbivore) {
                                if (generator.whetherEaten(organism, prey)) {
                                    replenishesOfLive(actualIteratedList,organism,prey,countSearchinPredator);
                                    indicesToRemove.add(countSearchinPrey);
                                    eatenIndices.add(countSearchinPrey);
                                }
                            }
                        }
                    } else  if (organism instanceof Herbivore) {
                        if (organism instanceof Boar || organism instanceof Duck || organism instanceof Mouse){
                            for (int countSearchinPrey = 0; countSearchinPrey < actualIteratedList.size(); countSearchinPrey++) {
                                if (eatenIndices.contains(countSearchinPrey)) {continue;}
                                Organism prey =  actualIteratedList.get(countSearchinPrey);
                                if (prey instanceof Plant || prey instanceof Caterpillar || prey instanceof Mouse) {
                                    if (generator.whetherEaten(organism, prey)) {
                                        replenishesOfLiveForBoarDuckMouse(actualIteratedList,organism,prey,countSearchinPrey);
                                        indicesToRemove.add(countSearchinPrey);
                                        eatenIndices.add(countSearchinPrey);
                                    }
                                }
                            }
                        }
                        for (int countSearchinPrey = 0; countSearchinPrey < actualIteratedList.size(); countSearchinPrey++) {
                            if (eatenIndices.contains(countSearchinPrey)) {continue;}
                            Organism prey =  actualIteratedList.get(countSearchinPrey);
                            if (prey instanceof Plant) {
                                if (generator.whetherEaten(organism, prey)) {
                                    replenishesOfLive(actualIteratedList,organism,prey,countSearchinPrey);
                                    indicesToRemove.add(countSearchinPrey);
                                    eatenIndices.add(countSearchinPrey);
                                }
                            }
                        }
                    }
                }
                indicesToRemove.stream()
                        .sorted(Comparator.reverseOrder())
                        .forEach(index -> actualIteratedList.remove((int) index));
                location[row][column]=actualIteratedList;
            }
        }
    }

private static Animal updateAnimalFoodRequired(List<Organism> actualIteratedList, Organism whoEats, Organism canBeEaten, int indexIteration) {
    Animal newInstanceHunter = FactoryNewAnimal.factoryNewInstance((Animal) whoEats);
    Animal hunter = (Animal) whoEats;
    double foodRequired = hunter.getFoodRequired();
    if (canBeEaten instanceof Animal) {
        Animal victim = (Animal) canBeEaten;
        double victimWeight = victim.getWeight();
        foodRequired += victimWeight;
    } else if (canBeEaten instanceof Plant) {
        Plant victim = (Plant) canBeEaten;
        int herbWeight = victim.getWeight();
        foodRequired += herbWeight;
    }
    if (foodRequired < newInstanceHunter.getFoodRequired()) {
        newInstanceHunter.setFoodRequired(foodRequired);
    }
    actualIteratedList.set(indexIteration, newInstanceHunter);
    return newInstanceHunter;
}
private static void replenishesOfLiveForBoarDuckMouse(List<Organism> actualIteratedList, Organism whoEats, Organism canBeEaten, int indexIteration) {
    if (canBeEaten instanceof Herbivore || canBeEaten instanceof Plant) {
        updateAnimalFoodRequired(actualIteratedList, whoEats, canBeEaten, indexIteration);
    }
}

private static void replenishesOfLive(List<Organism> actualIteratedList, Organism whoEats, Organism canBeEaten, int indexIteration) {
    if (whoEats instanceof Predator || whoEats instanceof Herbivore) {
        updateAnimalFoodRequired(actualIteratedList, whoEats, canBeEaten, indexIteration);
    }
}

    public static void move(List<Organism>[][] locationIsland){
        List<Organism> currentListForMovement = new ArrayList<>();
        for (int row = 0; row < locationIsland.length; row++) {
            for (int column = 0; column < locationIsland[row].length; column++) {
                Collections.copy(locationIsland[row][column],currentListForMovement);
                for (int countInside=0;countInside<currentListForMovement.size();countInside++){
                    Organism organism = currentListForMovement.get(countInside);
                    transmitCurrentLevelLife(row,column,locationIsland,organism);
                }
            }
        }
    }

    private static void transmitCurrentLevelLife(int row, int column, List<Organism>[][] locationIsland, Organism organism){
        Animal animal;
     animal = FactoryNewAnimal.factoryNewInstance((Animal) organism);
    if (animal != null) {
        animal.setFoodRequired(((Animal) organism).getFoodRequired());
        switch (animal.getClass().getSimpleName()) {
            case "Woolf": moveActionByOneAnimal(row, column, locationIsland, (Woolf) animal);
                break;
            case "Bear": moveActionByOneAnimal(row, column, locationIsland, (Bear) animal);
                break;
            case "Boa": moveActionByOneAnimal(row, column, locationIsland, (Boa) animal);
                break;
            case "Eagle": moveActionByOneAnimal(row, column, locationIsland, (Eagle) animal);
                break;
            case "Fox": moveActionByOneAnimal(row, column, locationIsland, (Fox) animal);
                break;
            case "Boar": moveActionByOneAnimal(row, column, locationIsland, (Boar) animal);
                break;
            case "Buffalo": moveActionByOneAnimal(row, column, locationIsland, (Buffalo) animal);
                break;
            case "Caterpillar": moveActionByOneAnimal(row, column, locationIsland, (Caterpillar) animal);
                break;
            case "Deer": moveActionByOneAnimal(row, column, locationIsland, (Deer) animal);
                break;
            case "Duck": moveActionByOneAnimal(row, column, locationIsland, (Duck) animal);
                break;
            case "Goat": moveActionByOneAnimal(row, column, locationIsland, (Goat) animal);
                break;
            case "Horse": moveActionByOneAnimal(row, column, locationIsland, (Horse) animal);
                break;
            case "Mouse": moveActionByOneAnimal(row, column, locationIsland, (Mouse) animal);
                break;
            case "Rabbit": moveActionByOneAnimal(row, column, locationIsland, (Rabbit) animal);
                break;
            case "Sheep": moveActionByOneAnimal(row, column, locationIsland, (Sheep) animal);
                break;
        }
    }
    }

    private static int getMovementSpeedPerTurn(Class clazz){
        int movementSpeedPerTurn = 0;
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode entityNode = rootNode.get(clazz.getSimpleName());
            movementSpeedPerTurn = entityNode.get("movement_speed_per_turn").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movementSpeedPerTurn;
    }
    private static void moveActionByOneAnimal(int currentRow, int currentColumn, List<Organism>[][] location, Organism organism) {
        int[] coordinates = validatorRowOrColumn(currentRow, currentColumn, organism.getClass());
        randomRow = coordinates[0];
        randomColumn = coordinates[1];
        while (!isValidPosition(randomRow, randomColumn)) {
            coordinates = validatorRowOrColumn(currentRow, currentColumn, organism.getClass());
            randomRow = coordinates[0];
            randomColumn = coordinates[1];
        }
        location[randomRow][randomColumn].add(organism);
        location[currentRow][currentColumn].remove(organism);
    }
    private static int[] validatorRowOrColumn(int row, int column, Class clazz) {
        Random random = new Random();
        int randomIntBetweenNulAndOne = random.nextInt(2);
        int newCoordinate = getRandomMovement(clazz);
        if (randomIntBetweenNulAndOne > 0) {
            row = newCoordinate;
        } else {
            column = newCoordinate;
        }
        return new int[]{row, column};
    }

    private static int getRandomMovement(Class clazz) {
        return new Random().nextInt(getMovementSpeedPerTurn(clazz)*2+1) - getMovementSpeedPerTurn(clazz);
    }

    private static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < island.getRows() && column >= 0 && column < island.getColumns();
    }

    public void reproduce(List<Organism>[][] result, Animal animal) {
        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getColumns(); j++) {
                List<Organism> initCopieOfResultList = new ArrayList<>(result[i][j]);
                result[i][j]= implementAnyTypeForReproducing(initCopieOfResultList,animal);
                }
        }
    }

    private List<Organism> implementAnyTypeForReproducing(List<Organism> animalsInCell, Animal animal){
        List<Organism> innerListForHandling = new ArrayList<>(animalsInCell);
        int numberOfSameAnimalsInCell = countOrganismsOfTypeInSameCell(innerListForHandling, animal.getClass());
        if((numberOfSameAnimalsInCell+(numberOfSameAnimalsInCell/2))<=maxQuantityPerCell){
            int countExistentAnimals = numberOfSameAnimalsInCell/2;
            for (int countNewCreation = 0;countNewCreation<countExistentAnimals;countNewCreation++){
                innerListForHandling.add(animal);
            }
        } else if ((numberOfSameAnimalsInCell+(numberOfSameAnimalsInCell/2))>maxQuantityPerCell) {
            int countExistentAnimals = maxQuantityPerCell- numberOfSameAnimalsInCell;
            for (int countNewCreation = 0;countNewCreation<countExistentAnimals;countNewCreation++){
                innerListForHandling.add(animal);
            }
        }
     return innerListForHandling;
    }

    private  int countOrganismsOfTypeInSameCell(List<Organism> organisms, Class<?extends Organism> clazz){
        int count = 0;
        for (Organism organism : organisms) {
            if (clazz.isInstance(organism)) {
                count++;
            }
        }
        return count;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxQuantityPerCell() {
        return maxQuantityPerCell;
    }

    public double getFoodRequired() {
        return foodRequired;
    }

    public void setFoodRequired(double foodRequired) {
        this.foodRequired = foodRequired;
    }

}
