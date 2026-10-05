package com.car_decoration.Decoration;

import java.util.List;

import com.car_decoration.Car.Car;

public abstract class DecorationCar extends Car {
    public List<String> getDecorations() {
        return getDescriptions();
    }
}
