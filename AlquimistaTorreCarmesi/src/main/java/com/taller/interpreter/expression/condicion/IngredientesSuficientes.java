package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/** Condición base: INGREDIENTES_SUFICIENTES — ¿hay ingredientes por encima del umbral? */
public class IngredientesSuficientes implements Condicion {
    @Override
    public boolean evaluar(Context context) {
        return context.getIngredientes() >= context.getUmbralIngredientesSuficientes();
    }
}
