package com.javarush.entities.organisms.predators;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Predator;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Bear extends Animal implements Predator {
    public Bear() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode bearNode = rootNode.get("Bear");
            this.weight = bearNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = bearNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = bearNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = bearNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
