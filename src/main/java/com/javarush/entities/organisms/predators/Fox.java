package com.javarush.entities.organisms.predators;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Predator;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Fox extends Animal implements Predator {
    public Fox() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode foxNode = rootNode.get("Fox");
            this.weight = foxNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = foxNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = foxNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = foxNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
