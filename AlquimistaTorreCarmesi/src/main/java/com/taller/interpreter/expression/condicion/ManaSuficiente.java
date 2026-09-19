package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/** Condición base: MANA_SUFICIENTE — ¿hay maná por encima del umbral configurado? */
public class ManaSuficiente implements Condicion {
    @Override
    public boolean evaluar(Context context) {
        return context.getManaActual() >= context.getUmbralManaSuficiente();
    }
}
