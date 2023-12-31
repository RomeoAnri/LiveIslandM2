package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Deer extends Animal implements Herbivore {
    public Deer() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode deerNode = rootNode.get("Deer");
            this.weight = deerNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = deerNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = deerNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = deerNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
