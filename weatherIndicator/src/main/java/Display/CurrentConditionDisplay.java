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
public class CurrentConditionDisplay implements Observer,Display{
    
    private int disTemp;
    private int disHum;
    private int disPre;


    @Override
    public void UpdateDisplay(int temperature, int humidity, int pressure) {
        this.disTemp = temperature;
        this.disHum = humidity;
        this.disPre = pressure;
    }

    @Override
    public void display() {
        System.out.print("Temperatura Actual: " + this.disTemp + " Grados\n");
        System.out.print("Humedad: " + this.disHum + " Grados\n");
        System.out.print("Presion: " + this.disPre + " Grados\n");
    }
    
}
