package com.car_decoration.Decoration;

import java.util.List;

public class TurnOnKit extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public TurnOnKit(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Turn On Kit");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (1500000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

}
