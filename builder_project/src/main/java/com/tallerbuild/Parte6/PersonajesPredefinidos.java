package com.tallerbuild.Parte6;


public final class PersonajesPredefinidos {

    public static Personaje guerreroPorDefecto() {
        return new Personaje.Builder("Thorgar", "enano", "guerrero")
                .conArma("Hacha de batalla")
                .conArmadura("Cota de placas")
                .conHabilidad("Golpe devastador")
                .conNivel(3)
                .build();
    }

    public static Personaje magoPorDefecto() {
        return new Personaje.Builder("Elowen", "elfo", "mago")
                .conArma("Bastón arcano")
                .conHabilidad("Bola de fuego")
                .conHabilidad("Escudo mágico")
                .conMascota("Familiar búho")
                .build();
    }

    public static Personaje arqueroPorDefecto() {
        return new Personaje.Builder("Rin", "humano", "arquero")
                .conArma("Arco largo")
                .conHabilidad("Disparo certero")
                .conNivel(2)
                .build();
    }
}
