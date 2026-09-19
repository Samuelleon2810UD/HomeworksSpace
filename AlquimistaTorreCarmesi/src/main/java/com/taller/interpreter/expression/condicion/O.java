package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/** Combinador lógico: O (OR) — verdadero si al menos una sub-condición lo es. */
public class O implements Condicion {

    private final Condicion izquierda;
    private final Condicion derecha;

    public O(Condicion izquierda, Condicion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public boolean evaluar(Context context) {
        return izquierda.evaluar(context) || derecha.evaluar(context);
    }
}
