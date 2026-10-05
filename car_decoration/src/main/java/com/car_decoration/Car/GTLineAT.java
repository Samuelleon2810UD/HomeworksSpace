package com.car_decoration.Car;

import com.car_decoration.Decoration.DecorationCar;

public class GTLineAT extends DecorationCar {
    public GTLineAT(){
        this.type = "GTLineAT";
    }

    @Override
    public float cost() {
        return 20000000;
    }
}
