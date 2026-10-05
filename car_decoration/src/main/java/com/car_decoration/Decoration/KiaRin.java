package com.car_decoration.Decoration;

import java.util.List;

public class KiaRin extends DecorationCar {
    DecorationCar decoration;
    int quantity;
    int size;
    String color;

    public KiaRin(DecorationCar decoration , int quantity, int size, String color) {
        this.decoration = decoration;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }

    @Override
    public List<String> getDecorations() {
        List<String> decorations = decoration.getDecorations();
        for (int i = 0; i < quantity; i++) {
            decorations.add("Kia Rin - Size: " + size + ", Color: " + color);
        }
        return decorations;
    }

    public float cost() {
        int price = 0;
        if(size == 13){
            price = 350000;
        } else if(size == 14){
            price = 500000;
        }

        return decoration.cost() + (price * quantity);
    }

    @Override
    public String getType() {
        return decoration.getType();
    }

    
}
