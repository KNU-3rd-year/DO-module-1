package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int harborSize = 1000;
        Harbor harbor = new Harbor(generateContainers(harborSize / 2), harborSize);
        List<Ship> ships = generateShips(harborSize / 5, harbor);
        start(ships);
    }

    private static List<Container> generateContainers(int count) {
        List<Container> containers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            containers.add(new Container());
        }
        return containers;
    }

    private static List<Ship> generateShips(int count, Harbor harbor) {
        List<Ship> containers = new ArrayList<>();
        Random r = new Random();
        int maxSheepSize = 50;
        for (int i = 0; i < count; i++) {
            containers.add(new Ship(r.nextInt(maxSheepSize), harbor));
        }
        return containers;
    }

    private static void start(List<Ship> ships) {
        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).start();
        }
    }
}