package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/** Terminal: RECOLECTAR — el alquimista recoge un ingrediente de la torre. */
public class Recolectar implements Expression {
    @Override
    public void interpret(Context context) {
        context.recolectarIngrediente();
        System.out.println("  -> RECOLECTAR: el alquimista recoge un ingrediente. " + context);
    }
}
