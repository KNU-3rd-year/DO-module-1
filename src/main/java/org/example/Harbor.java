package org.example;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Harbor {
    final List<Container> containers;
    final Boolean[] piers;
    Semaphore piersSemaphore;

    public Harbor(List<Container> containers, int harborSize) {
        this.containers = containers;
        this.piers = new Boolean[harborSize];
        this.piersSemaphore = new Semaphore(harborSize, true);

        for (int i = 0; i < harborSize; i++) {
            piers[i] = false;
        }
    }
}
