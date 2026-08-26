/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extras;

/**
 *
 * @author Estudiantes
 */
public class Lotes {
    private String varietal;
    private String notes;
    private String origin;
    private int units;
    private int altitude;
    private String productorName;

    public Lotes(String v , String n , String o, int u , int a , String p){
        this.altitude = a;
        this.notes = n;
        this.origin = o;
        this.productorName = p;
        this.units = u;
        this.varietal = v;
    }

    // --- Getters (faltaban: sin ellos era imposible mostrar o usar los lotes fuera de la clase) ---

    public String getVarietal() {
        return varietal;
    }

    public String getNotes() {
        return notes;
    }

    public String getOrigin() {
        return origin;
    }

    public int getUnits() {
        return units;
    }

    public int getAltitude() {
        return altitude;
    }

    public String getProductorName() {
        return productorName;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "varietal='" + varietal + '\'' +
                ", notas='" + notes + '\'' +
                ", origen='" + origin + '\'' +
                ", unidades=" + units +
                ", altitud=" + altitude +
                ", productor='" + productorName + '\'' +
                '}';
    }
}
