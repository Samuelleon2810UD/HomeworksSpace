package com.car_decoration.Decoration;

import java.util.List;

public class PicantoRug extends DecorationCar {
        DecorationCar decoration;
    int quantity;

    public PicantoRug(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Picanto Rug 3 pieces");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (92000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

}
