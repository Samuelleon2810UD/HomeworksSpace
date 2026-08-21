/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Display;

import Observer.Observer;


/**
 *
 * @author Estudiantes
 */
public class HeatIndexDisplay implements Observer, Display{
    
    private float heatIndex;
    private int temperature;
    private int humidity;
    private double c1 = -8.78469475556;
    private double c2 = 1.61139411;
    private double c3 = 2.33854883889;
    private double c4 = -0.14611605;
    private double c5 = -0.0123081;
    private double c6 = -0.0164248277778;
    private double c7 = 0.002211732;
    private double c8 = 0.00072546;
    private double c9 = -0.000003582;
    
    @Override
    public void UpdateDisplay(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        getHeatIndex();
    }

    @Override
    public void display() {
        System.out.print("Indice de Calor: " + this.heatIndex + " Grados\n");
    }

    public float getHeatIndex() {
        this.heatIndex = (float) (this.c1 + this.c2*this.temperature + this.c3*this.humidity + this.c4*this.humidity*this.temperature + this.c5*this.temperature*this.temperature + this.c6*this.humidity*this.humidity + this.c7*this.humidity*this.temperature*this.temperature + this.c8*this.temperature*this.humidity*this.humidity + this.c9*this.humidity*this.temperature*this.temperature*this.humidity);
        return heatIndex;
    }


    
}