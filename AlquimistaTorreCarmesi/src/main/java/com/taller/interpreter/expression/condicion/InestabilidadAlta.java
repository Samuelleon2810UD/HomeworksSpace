package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/**
 * Condición base: INESTABILIDAD_ALTA — ¿la torre está por encima del umbral
 * de inestabilidad? Es la condición que demuestra el requisito central del
 * taller: si se evalúa después de INVOCAR_LLAMA o SELLAR_GRIETA, refleja el
 * estado que esas expresiones ya transformaron.
 */
public class InestabilidadAlta implements Condicion {
    @Override
    public boolean evaluar(Context context) {
        return context.getInestabilidadTorre() >= context.getUmbralInestabilidadAlta();
    }
}
