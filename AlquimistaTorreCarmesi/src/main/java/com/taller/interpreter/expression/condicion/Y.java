package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/**
 * Combinador lógico: Y (AND) — verdadero solo si ambas sub-condiciones lo
 * son. Al implementar la misma interfaz {@link Condicion} que las
 * condiciones base, permite construir árboles de condiciones tan profundos
 * como se necesite (composición sobre condiciones, no solo sobre acciones).
 */
public class Y implements Condicion {

    private final Condicion izquierda;
    private final Condicion derecha;

    public Y(Condicion izquierda, Condicion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public boolean evaluar(Context context) {
        return izquierda.evaluar(context) && derecha.evaluar(context);
    }
}
