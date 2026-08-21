/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Display;

import Observer.Observer;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Estudiantes
 * 
 */
public class StatisticsDisplay implements Display, Observer {
    
    private List<Integer> disTempList;
    private List<Integer> disHumList;
    private List<Integer> disPreList;
    private List<List<Integer>> statisticsList;
    
    private final String[] metricTypes = {"Temperatura", "Humedad", "Presión"};

    public StatisticsDisplay() {

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
    }

    @Override
    public void display() {
        System.out.println("=== Historial de Mediciones ===");
        for (int index = 0; index < statisticsList.size(); index++) {
            String tipoMedicion = metricTypes[index];
            List<Integer> historial = statisticsList.get(index);
            
            System.out.println("Índice " + index + " (" + tipoMedicion + "): " + historial);
        }
        System.out.println();
    }
}