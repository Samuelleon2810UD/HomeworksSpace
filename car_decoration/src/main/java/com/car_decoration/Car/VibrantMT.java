package com.car_decoration.Car;

import com.car_decoration.Decoration.DecorationCar;

public class VibrantMT extends DecorationCar {
    public VibrantMT() {
        this.type = "VibrantMT";
    }

    @Override
    public float cost() {
        return 15000000;
    }
    
}
