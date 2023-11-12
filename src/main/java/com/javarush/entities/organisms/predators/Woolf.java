package com.javarush.entities.organisms.predators;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Predator;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Woolf  extends Animal implements  Predator {

    public Woolf() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode woolfNode = rootNode.get("Woolf");
            this.weight = woolfNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = woolfNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = woolfNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = woolfNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
