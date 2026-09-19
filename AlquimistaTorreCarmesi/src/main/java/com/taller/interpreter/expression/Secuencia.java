package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

import java.util.ArrayList;
import java.util.List;

/** Expresión compuesta: ejecuta dos o más instrucciones en secuencia (A + B + ...). */
public class Secuencia implements Expression {

    private final List<Expression> pasos = new ArrayList<>();

    public Secuencia(Expression... expresiones) {
        for (Expression e : expresiones) {
            pasos.add(e);
        }
    }

    @Override
    public void interpret(Context context) {
        for (Expression paso : pasos) {
            paso.interpret(context);
        }
    }
}
