package com.car_decoration.Decoration;

import java.util.List;

public class ChargeMatrix extends DecorationCar{
    DecorationCar decoration;
    int quantity;

    public ChargeMatrix(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Charge Matrix");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (110000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

    
}
