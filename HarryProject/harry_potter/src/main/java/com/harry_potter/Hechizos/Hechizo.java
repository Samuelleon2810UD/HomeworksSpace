package com.harry_potter.Hechizos;

/**
 * Interfaz que representa la estrategia "Hechizo".
 * Cada hechizo concreto define su propio comportamiento al lanzarse.
 * Esto permite el patrón STRATEGY: los personajes pueden cambiar
 * de hechizo en tiempo de ejecución sin modificar su clase.
 */
public interface Hechizo {

    /**
     * Ejecuta el efecto del hechizo sobre un objetivo.
     * @param objetivo nombre del objeto/persona/situación sobre la que actúa el hechizo
     */
    void lanzar(String objetivo);

    /**
     * @return el nombre mágico del hechizo (ej: "Expecto Patronum")
     */
    String getNombre();
}
