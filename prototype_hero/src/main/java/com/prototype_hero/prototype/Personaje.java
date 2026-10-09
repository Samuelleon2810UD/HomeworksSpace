package com.prototype_hero.prototype;

import com.prototype_hero.model.Inventario;

public class Personaje {

    private String nombre;
    private String clase;
    private Inventario inventario;

    public Personaje(String nombre, String clase, Inventario inventario) {
        this.nombre = nombre;
        this.clase = clase;
        this.inventario = inventario;
    }

    // Constructor de copia privado: solo lo usa clonar()
    private Personaje(Personaje original) {
        this.nombre = original.nombre;
        this.clase = original.clase;
        // Copia profunda: el inventario es una instancia nueva
        this.inventario = original.inventario.copiar();
    }

    public Personaje clonar() {
        return new Personaje(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public Inventario getInventario() {
        return inventario;
    }

    @Override
    public String toString() {
        return nombre + " (" + clase + ") - Inventario: " + inventario;
    }
}
