package com.car_decoration.Decoration;

import java.util.List;

public class SecurityPerns extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public SecurityPerns(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Security Perns");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (156100 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

    
}
