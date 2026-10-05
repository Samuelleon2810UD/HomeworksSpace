package com.car_decoration.Decoration;

import java.util.List;

public class AlarmsControls extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public AlarmsControls(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Alarms Controls 2 controls");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (205000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

}
