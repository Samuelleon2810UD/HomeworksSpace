package com.harry_potter.Personaje;

import com.harry_potter.Hechizos.ExpectoPatronum;

/** Personaje Harry Potter: hábil inicialmente en Expecto Patronum. */
public class HarryPotter extends Personaje {

    public HarryPotter() {
        super("Harry Potter", new ExpectoPatronum());
    }
}
