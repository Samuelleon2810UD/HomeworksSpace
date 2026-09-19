package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Expresión compuesta: REPETIR n VECES (expresión). Interpreta la
 * sub-expresión n veces sobre el mismo Context, dejando que cada repetición
 * vea el estado que dejaron las anteriores (por ejemplo, repetir
 * INVOCAR_LLAMA varias veces va subiendo la inestabilidad en cada vuelta).
 */
public class Repetir implements Expression {

    private final int veces;
    private final Expression cuerpo;

    public Repetir(int veces, Expression cuerpo) {
        this.veces = veces;
        this.cuerpo = cuerpo;
    }

    @Override
    public void interpret(Context context) {
        for (int i = 0; i < veces; i++) {
            cuerpo.interpret(context);
        }
    }
}
