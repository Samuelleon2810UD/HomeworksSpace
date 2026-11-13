package core;

/**
 * Abstraccion compartida entre peto y botas.
 * Rompe el ciclo: ambos componentes dependen de core, y core no depende de nadie.
 */
public interface GemaEnergia {
    boolean tieneCarga();
}
