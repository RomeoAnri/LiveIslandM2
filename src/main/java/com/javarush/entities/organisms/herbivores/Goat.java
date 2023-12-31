package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Goat extends Animal implements Herbivore {
    public Goat() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode goatNode = rootNode.get("Goat");
            this.weight = goatNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = goatNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = goatNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = goatNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
