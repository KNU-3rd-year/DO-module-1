package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Thread {
    private List<Container> containers;
    private int size;
    private Harbor harbor;

    public Ship(int size, Harbor harbor) {
        this.containers = new ArrayList<>();
        this.size = size;
        this.harbor = harbor;

        for (int i = 0; i < size; i++) {
            this.containers.add(new Container());
        }
    }

    @Override
    public void run() {
        System.out.println("Ship " + Thread.currentThread().getName() + " pulled up to the harbor");
        try {
            harbor.piersSemaphore.acquire();

            int parkingPier = -1;
            synchronized (harbor.piers) {
                for (int i = 0; i < harbor.piers.length; i++) {
                    if (!harbor.piers[i]) {
                        harbor.piers[i] = true;
                        parkingPier = i;
                        System.out.println("Ship " + Thread.currentThread().getName() + " has got the pier number " + parkingPier);
                        break;
                    }
                }
            }

            moveContainers();

            synchronized (harbor.piers) {
                harbor.piers[parkingPier] = false;
            }

            harbor.piersSemaphore.release();
            System.out.println("Ship " + Thread.currentThread().getName() + " leaves the harbor");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void moveContainers() {
        try {
            System.out.println("Ship " + Thread.currentThread().getName() + " start moving containers");
            Thread.sleep(this.containers.size() * 1000L);
            synchronized (harbor.containers) {
                harbor.containers.addAll(this.containers);
                this.containers = harbor.containers.subList(0, size);
                harbor.containers.removeAll(containers);
            }
            System.out.println("Ship " + Thread.currentThread().getName() + " finish moving containers");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
