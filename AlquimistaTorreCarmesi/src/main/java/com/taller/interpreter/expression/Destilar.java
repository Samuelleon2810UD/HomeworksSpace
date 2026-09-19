package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Terminal: DESTILAR — el alquimista consume ingredientes para preparar una
 * poción. Requiere al menos 2 ingredientes; si no los tiene, el intento falla
 * sin consumir nada.
 */
public class Destilar implements Expression {

    private static final int COSTO_INGREDIENTES = 2;

    @Override
    public void interpret(Context context) {
        if (context.consumirIngredientes(COSTO_INGREDIENTES)) {
            context.agregarPocion();
            System.out.println("  -> DESTILAR: el alquimista prepara una poción. " + context);
        } else {
            System.out.println("  -> DESTILAR: no hay ingredientes suficientes, la destilación falla. " + context);
        }
    }
}
