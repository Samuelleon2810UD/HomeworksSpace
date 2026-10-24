package com.ui.content;

/**
 * Patrón de Contenido y Estructura: Progressive Disclosure (Acordeón).
 * Por defecto solo se muestra la información principal (nombre y estado);
 * los detalles técnicos se revelan únicamente si el usuario lo solicita.
 */
public class TarjetaElemento {

    private final String nombre;
    private final String estado;
    private final String ip;
    private final int puerto;
    private final int ramUsadaGb;
    private final int ramTotalGb;

    private boolean verAvanzado = false;

    public TarjetaElemento(String nombre, String estado, String ip, int puerto,
                           int ramUsadaGb, int ramTotalGb) {
        this.nombre = nombre;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
        this.ramUsadaGb = ramUsadaGb;
        this.ramTotalGb = ramTotalGb;
    }

    public void desplegar() {
        verAvanzado = true;
    }

    public void ocultar() {
        verAvanzado = false;
    }

    public void alternar() {
        verAvanzado = !verAvanzado;
    }

    public boolean isVerAvanzado() {
        return verAvanzado;
    }

    public String getNombre() {
        return nombre;
    }

    /** Devuelve la vista según el estado booleano verAvanzado. */
    public String render() {
        if (!verAvanzado) {
            return "[Vista Resumen] " + nombre + " · Estado: " + estado;
        }
        return "[Acordeón Desplegado] IP: " + ip
                + " | Puerto: " + puerto
                + " | RAM: " + ramUsadaGb + "GB/" + ramTotalGb + "GB";
    }
}
