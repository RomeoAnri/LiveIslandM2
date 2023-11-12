package com.javarush.logicBlock;

import com.javarush.abstraction.Herbivore;
import com.javarush.abstraction.Organism;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentInformation {
private static Map<Class<? extends Organism>, Integer> countOrganismTypes(List<Organism>[][] result) {
    Map<Class<? extends Organism>, Integer> organismCountMap = new HashMap<>();
    for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[i].length; j++) {
            List<Organism> habitat = result[i][j];
            for (Organism organism : habitat) {
                Class<? extends Organism> organismClass = organism.getClass();
                organismCountMap.put(organismClass, organismCountMap.getOrDefault(organismClass, 0) + 1);
            }
        }
    }
    return organismCountMap;
}

    public static void printInfoAboutIsland(List<Organism>[][] result) {
        Map<Class<? extends Organism>, Integer> totalOrganismCountMap = countOrganismTypes(result);
        for (Map.Entry<Class<? extends Organism>, Integer> entry : totalOrganismCountMap.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        }
    }

    public static boolean hasHerbivores(List<Organism>[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                List<Organism> habitat = result[i][j];
                for (Organism organism : habitat) {
                    if (organism instanceof Herbivore) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
