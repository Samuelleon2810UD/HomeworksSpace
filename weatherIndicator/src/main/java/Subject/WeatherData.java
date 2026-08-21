/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Subject;

import Observer.Observer;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class WeatherData implements Subject {
    
    private int temperature;
    private int pressure;
    private int humidity;
    private List<Observer> observers;

    public WeatherData() {
        this.observers = new java.util.ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o){
        if(this.observers.contains(o)){
            System.out.print("---- Elemento ya existente ----\n");
            return;
        }
        
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.UpdateDisplay(getTemperature(), getHumidity(), getPressure());
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if(!this.observers.contains(o)){
            System.out.print("---- Observador no existente en la lista ----\n");
            return;
        }
        this.observers.remove(o);
        
    }
    
    private int getTemperature(){
        return this.temperature;
    }
    
    private int getHumidity(){
        return this.humidity;
    }
    
    private int getPressure(){
        return this.pressure;
    }
    
    public void changeMeasurements(int temperature, int humidity, int pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
    
}