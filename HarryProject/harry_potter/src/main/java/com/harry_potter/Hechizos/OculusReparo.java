package com.harry_potter.Hechizos;

/** Hechizo para reparar objetos dañados (ej: anteojos). */
public class OculusReparo implements Hechizo {

    @Override
    public void lanzar(String objetivo) {
        System.out.println("¡Oculus Reparo! " + objetivo + " ha quedado completamente reparado.");
    }

    @Override
    public String getNombre() {
        return "Oculus Reparo";
    }
}
