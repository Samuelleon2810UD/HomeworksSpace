/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Display;

import java.util.ArrayList;
import java.util.List;

import Observer.Observer;

/**
 *
 * @author Estudiantes
 */
public class ForeCastDisplay implements Observer, Display {
    
    private List<Integer> disTempList;
    private List<Integer> disHumList;
    private List<Integer> disPreList;
    private List<List<Integer>> statisticsList;
    private String foreCast;

    public ForeCastDisplay() {

        this.disTempList = new ArrayList<>();
        this.disHumList = new ArrayList<>();
        this.disPreList = new ArrayList<>();
        this.statisticsList = new ArrayList<>();


        this.statisticsList.add(disTempList);
        this.statisticsList.add(disHumList);
        this.statisticsList.add(disPreList);
    }

    @Override
    public void UpdateDisplay(int temperature, int humidity, int pressure) {
        this.disTempList.add(temperature);
        this.disHumList.add(humidity);
        this.disPreList.add(pressure);

        generateForeCast();
    }

    @Override
    public void display() {
        System.out.println("=== Pronóstico ===\n");
        System.out.println(this.foreCast);
    }

    private void generateForeCast() {
        int lastIdx = disPreList.size() - 1;
    
        // Si solo hay un dato registrado, no hay suficiente historial para predecir
        if (lastIdx < 1) {
            this.foreCast = "Suficientes datos aún no disponibles para generar pronóstico.";
            return;
        }
    
        // Obtener valores actuales y anteriores de presión
        int currentPressure = disPreList.get(lastIdx);
        int prevPressure = disPreList.get(lastIdx - 1);
    
        // Obtener valor actual de humedad
        int currentHumidity = disHumList.get(lastIdx);
    
        // Lógica de predicción basada en la tendencia de la presión
        if (currentPressure > prevPressure) {
            this.foreCast = "Mejorando el tiempo: Se espera un día despejado y seco.";
        } else if (currentPressure < prevPressure) {
            if (currentHumidity > 80) {
                this.foreCast = "Alerta de tormenta: Alta probabilidad de lluvias fuertes o tormenta.";
            } else {
                this.foreCast = "Tiempo empeorando: Posibilidad de lluvias o nubosidad.";
            }
        } else {
            this.foreCast = "Sin cambios significativos: Se mantendrán las condiciones actuales.";
        }
    }
}
