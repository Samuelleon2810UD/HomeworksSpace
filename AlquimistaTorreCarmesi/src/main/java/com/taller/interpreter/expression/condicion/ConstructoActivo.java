package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/** Condición base: CONSTRUCTO_ACTIVO — ¿el Constructo sigue con vida? */
public class ConstructoActivo implements Condicion {
    @Override
    public boolean evaluar(Context context) {
        return context.isConstructoActivo();
    }
}
