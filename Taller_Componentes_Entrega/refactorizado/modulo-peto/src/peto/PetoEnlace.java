package peto;

import core.GemaEnergia;

/**
 * El peto tiene escudo base propio: no depende de que las botas esten puestas.
 * Si hay una fuente de energia externa conectada (cualquier GemaEnergia) con carga,
 * el escudo se refuerza; si se retira (null), el escudo base sigue activo.
 */
public class PetoEnlace {
    private GemaEnergia fuente;

    public PetoEnlace(GemaEnergia fuente) {
        this.fuente = fuente;
    }

    public void conectarFuente(GemaEnergia nuevaFuente) {
        this.fuente = nuevaFuente;
    }

    public boolean escudoActivo() {
        return true;
    }

    public boolean escudoReforzado() {
        return fuente != null && fuente.tieneCarga();
    }
}
