package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Buffalo extends Animal implements Herbivore {
    public Buffalo() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode buffaloNode = rootNode.get("Buffalo");
            this.weight = buffaloNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = buffaloNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = buffaloNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = buffaloNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
