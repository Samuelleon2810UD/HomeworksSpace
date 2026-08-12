package com.harry_potter.Hechizos;

/** Hechizo para hacer levitar objetos. */
public class WingardiumLeviosa implements Hechizo {

    @Override
    public void lanzar(String objetivo) {
        System.out.println("¡Wingardium Leviosa! " + objetivo + " comienza a levitar en el aire.");
    }

    @Override
    public String getNombre() {
        return "Wingardium Leviosa";
    }
}
