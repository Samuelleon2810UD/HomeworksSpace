package com.car_decoration.Decoration;

import java.util.List;

public class ParkingSensor extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public ParkingSensor(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Parking Sensor");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (150000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

}
