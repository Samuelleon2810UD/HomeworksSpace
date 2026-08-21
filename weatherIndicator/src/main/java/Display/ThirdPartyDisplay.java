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
public class ThirdPartyDisplay implements Display , Observer{
    
    private int disPre;


    @Override
    public void UpdateDisplay(int temperature, int humidity, int pressure) {
        this.disPre = pressure;
    }

    @Override
    public void display() {
        System.out.print("Presion: " + this.disPre + " Grados\n");
    }
    
}
