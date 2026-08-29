package com.tallerbuild;

import com.tallerbuild.Parte6.Personaje;
import com.tallerbuild.Parte6.PersonajesPredefinidos;

public class Main {
    public static void main(String[] args) {

        Personaje heroeCompleto = new Personaje.Builder("Aldric", "humano", "guerrero")
                .conArma("Espada larga")
                .conArmadura("Armadura de placas")
                .conHabilidad("Golpe poderoso")
                .conHabilidad("Grito de guerra")
                .conMascota("Lobo entrenado")
                .conNivel(5)
                .build();


        Personaje personajeMinimo = new Personaje.Builder("Kira", "elfo", "arquero")
                .build();

        Personaje magoPlantilla = PersonajesPredefinidos.magoPorDefecto();

        System.out.println(heroeCompleto.ficha());
        System.out.println(personajeMinimo.ficha());
        System.out.println(magoPlantilla.ficha());
    }
}