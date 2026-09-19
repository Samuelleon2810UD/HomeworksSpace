package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Terminal: HUIR — el alquimista abandona la torre. Gasta el maná restante
 * en un teletransporte de emergencia.
 */
public class Huir implements Expression {
    @Override
    public void interpret(Context context) {
        System.out.println("  -> HUIR: el alquimista teletransporta lejos de la Torre Carmesí. " + context);
    }
}
