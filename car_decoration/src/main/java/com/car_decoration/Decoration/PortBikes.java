package com.car_decoration.Decoration;

import java.util.List;

public class PortBikes extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public PortBikes(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Port Bikes 2 places");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (910000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

}
