package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;


public class Horse  extends Animal implements Herbivore {
    public Horse() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode horseNode = rootNode.get("Horse");
            this.weight = horseNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = horseNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = horseNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = horseNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
