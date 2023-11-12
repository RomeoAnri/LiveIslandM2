package com.javarush.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.abstraction.Animal;
import com.javarush.abstraction.Herbivore;
import com.javarush.logicBlock.ReaderJacksonFile;

import java.io.IOException;

public class Mouse extends Animal implements Herbivore {
    public Mouse() {
        super(0, 0, 0, 0.0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode mouseNode = rootNode.get("Mouse");
            this.weight = mouseNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = mouseNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = mouseNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = mouseNode.get("food_required_kg").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
