package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Caterpillar extends Animal implements Herbivore {
    public Caterpillar() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode caterpillarNode = rootNode.get("Caterpillar");
            this.weight = caterpillarNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = caterpillarNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = caterpillarNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = caterpillarNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
