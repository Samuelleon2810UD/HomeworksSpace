package com.car_decoration.Decoration;

import java.util.List;

public class DragWidget extends DecorationCar {
    DecorationCar decoration;
    int quantity;

    public DragWidget(DecorationCar decoration , int quantity) {
        this.decoration = decoration;
        this.quantity = quantity;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Drag Widget");
        }
        return decorations;
    }

    public float cost() {
        return decoration.cost() + (810000 * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

    
}
