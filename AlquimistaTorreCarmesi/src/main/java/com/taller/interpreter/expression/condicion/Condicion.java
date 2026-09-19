package com.taller.interpreter.expression.condicion;

import com.taller.interpreter.context.Context;

/**
 * Estrategia de evaluación para la expresión Condicional del lenguaje.
 * <p>
 * Las condiciones base (ConstructoActivo, InestabilidadAlta, etc.) y los
 * combinadores lógicos (Y, O, No) implementan esta misma interfaz, así que
 * se pueden anidar libremente para formar condiciones tan complejas como se
 * quiera (ej. Y(ConstructoActivo, No(InestabilidadAlta))) sin que ninguna
 * clase pregunte por el tipo de condición que tiene delante.
 */
public interface Condicion {
    boolean evaluar(Context context);
}
