package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;
import com.taller.interpreter.expression.condicion.Condicion;

/**
 * Expresión condicional: SI (condición) ENTONCES (rama-si) SINO (rama-sino).
 * <p>
 * La condición puede ser una condición base o un árbol arbitrario de
 * combinadores Y/O/NO; Condicional no sabe ni le importa cuál — solo llama a
 * {@code evaluar}. Encadenar "SINO SI" se logra poniendo otro Condicional
 * dentro de la rama sino.
 */
public class Condicional implements Expression {

    private final Condicion condicion;
    private final Expression ramaSi;
    private final Expression ramaSino;

    public Condicional(Condicion condicion, Expression ramaSi, Expression ramaSino) {
        this.condicion = condicion;
        this.ramaSi = ramaSi;
        this.ramaSino = ramaSino;
    }

    @Override
    public void interpret(Context context) {
        if (condicion.evaluar(context)) {
            ramaSi.interpret(context);
        } else {
            ramaSino.interpret(context);
        }
    }
}
