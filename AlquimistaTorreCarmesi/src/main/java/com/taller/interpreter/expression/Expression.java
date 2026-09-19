package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Interfaz raíz del patrón Interpreter. Cada clase concreta sabe
 * interpretarse a sí misma en {@link #interpret}; ninguna otra clase
 * necesita preguntar "¿qué tipo de expresión es esta?" para decidir qué hacer.
 */
public interface Expression {
    void interpret(Context context);
}
