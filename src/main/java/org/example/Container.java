package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Container {
    static final List<String> goodTypes = Arrays.asList("Banana", "Apple", "Meat", "Cucumber");
    String goodsName;

    public Container() {
        Random r = new Random();
        this.goodsName = goodTypes.get(r.nextInt(4));
    }
}
