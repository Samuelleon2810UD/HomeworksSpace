package com.car_decoration.Car;

import java.util.ArrayList;
import java.util.List;

public abstract class Car {
    float cost;
    List<String> decorations = new ArrayList<>();
    protected String type = "Kia";

    public List<String> getDescriptions() {
        return new ArrayList<>(decorations);
    }

    public String getType() {
        return type;
    }

    public abstract float cost();
}
