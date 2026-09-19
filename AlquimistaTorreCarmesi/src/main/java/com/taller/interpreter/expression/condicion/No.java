package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/** Combinador lógico: NO (NOT) — invierte el resultado de una sub-condición. */
public class No implements Condicion {

    private final Condicion condicion;

    public No(Condicion condicion) {
        this.condicion = condicion;
    }

    @Override
    public boolean evaluar(Context context) {
        return !condicion.evaluar(context);
    }
}
