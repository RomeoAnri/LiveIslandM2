package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Duck extends Animal implements Herbivore {
    public Duck() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode duckNode = rootNode.get("Duck");
            this.weight = duckNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = duckNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = duckNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = duckNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
