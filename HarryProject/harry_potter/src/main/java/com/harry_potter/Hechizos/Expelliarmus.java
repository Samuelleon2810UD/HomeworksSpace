package com.harry_potter.Hechizos;

/** Hechizo de desarme: quita el objeto de las manos de la víctima. */
public class Expelliarmus implements Hechizo {

    @Override
    public void lanzar(String objetivo) {
        System.out.println("¡Expelliarmus! El objeto sale volando de las manos de " + objetivo + ".");
    }

    @Override
    public String getNombre() {
        return "Expelliarmus";
    }
}
