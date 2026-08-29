package com.tallerbuild.Parte6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class Personaje {

    private final String nombre;
    private final String raza;
    private final String clase;
    private final String arma;
    private final String armadura;
    private final List<String> habilidades;
    private final String mascota;
    private final int nivel;


    private Personaje(Builder builder) {
        this.nombre = builder.nombre;
        this.raza = builder.raza;
        this.clase = builder.clase;
        this.arma = builder.arma;
        this.armadura = builder.armadura;
        this.habilidades = Collections.unmodifiableList(new ArrayList<>(builder.habilidades));
        this.mascota = builder.mascota;
        this.nivel = builder.nivel;
    }


    public String ficha() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Ficha de personaje ===\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Raza: ").append(raza).append("\n");
        sb.append("Clase: ").append(clase).append("\n");
        sb.append("Nivel: ").append(nivel).append("\n");

        if (arma != null && !arma.isBlank()) {
            sb.append("Arma equipada: ").append(arma).append("\n");
        }
        if (armadura != null && !armadura.isBlank()) {
            sb.append("Armadura: ").append(armadura).append("\n");
        }
        if (!habilidades.isEmpty()) {
            sb.append("Habilidades: ").append(String.join(", ", habilidades)).append("\n");
        }
        if (mascota != null && !mascota.isBlank()) {
            sb.append("Mascota/compañero: ").append(mascota).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return ficha();
    }

    // ---------- Builder ----------
    public static class Builder {
        // Obligatorios
        private final String nombre;
        private final String raza;
        private final String clase;


        private String arma;
        private String armadura;
        private final List<String> habilidades = new ArrayList<>();
        private String mascota;
        private int nivel = 1;

        public Builder(String nombre, String raza, String clase) {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre es obligatorio");
            }
            if (raza == null || raza.isBlank()) {
                throw new IllegalArgumentException("La raza es obligatoria");
            }
            if (clase == null || clase.isBlank()) {
                throw new IllegalArgumentException("La clase es obligatoria");
            }
            this.nombre = nombre;
            this.raza = raza;
            this.clase = clase;
        }

        public Builder conArma(String arma) {
            this.arma = arma;
            return this;
        }

        public Builder conArmadura(String armadura) {
            this.armadura = armadura;
            return this;
        }

        public Builder conHabilidad(String habilidad) {
            this.habilidades.add(habilidad);
            return this;
        }

        public Builder conMascota(String mascota) {
            this.mascota = mascota;
            return this;
        }

        public Builder conNivel(int nivel) {
            this.nivel = nivel;
            return this;
        }

        public Personaje build() {
            return new Personaje(this);
        }
    }
}
