package com.harry_potter.Personaje;

import com.harry_potter.Hechizos.WingardiumLeviosa;

/** Personaje Hermione Granger: hábil inicialmente en Wingardium Leviosa. */
public class HermioneGranger extends Personaje {

    public HermioneGranger() {
        super("Hermione Granger", new WingardiumLeviosa());
    }
}
