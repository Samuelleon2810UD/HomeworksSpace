package com.harry_potter.Personaje;

import com.harry_potter.Hechizos.Hechizo;

/**
 * Clase base abstracta para todos los personajes del videojuego.
 *
 * Usa COMPOSICIÓN sobre HERENCIA: en lugar de que cada subclase implemente
 * su propio método de hechizo, cada Personaje "tiene un" Hechizo (interfaz),
 * y ese hechizo puede sustituirse en tiempo de ejecución mediante
 * aprenderHechizo(). Esto es el patrón de diseño STRATEGY.
 */
public abstract class Personaje {

    private final String nombre;
    private Hechizo hechizo; // estrategia actual del personaje

    protected Personaje(String nombre, Hechizo hechizoInicial) {
        this.nombre = nombre;
        this.hechizo = hechizoInicial;
    }

    /**
     * Cambia el hechizo hábil del personaje en tiempo de ejecución.
     * Ej: hermione.aprenderHechizo(new OculusReparo());
     */
    public void aprenderHechizo(Hechizo nuevoHechizo) {
        System.out.println(nombre + " cambia su hechizo a: " + nuevoHechizo.getNombre());
        this.hechizo = nuevoHechizo;
    }

    /**
     * Lanza el hechizo actualmente asignado sobre un objetivo.
     */
    public void lanzarHechizo(String objetivo) {
        System.out.print(nombre + " lanza un hechizo -> ");
        hechizo.lanzar(objetivo);
    }

    public String getNombre() {
        return nombre;
    }

    public Hechizo getHechizoActual() {
        return hechizo;
    }
}
