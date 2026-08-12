package com.harry_potter.Hechizos;

/** Hechizo que materializa un guardián mágico a partir de pensamientos felices. */
public class ExpectoPatronum implements Hechizo {

    @Override
    public void lanzar(String objetivo) {
        System.out.println("¡Expecto Patronum! Un guardián mágico aparece para proteger contra " + objetivo + ".");
    }

    @Override
    public String getNombre() {
        return "Expecto Patronum";
    }
}
