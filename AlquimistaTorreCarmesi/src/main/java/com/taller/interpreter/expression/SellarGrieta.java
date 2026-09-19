package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Terminal: SELLAR_GRIETA — el alquimista gasta maná para reparar una grieta
 * y reducir la inestabilidad de la torre. Es el contrapeso de
 * {@link InvocarLlama}: mientras esa expresión sube la inestabilidad, esta
 * la baja, permitiendo demostrar decisiones basadas en el estado cambiante.
 */
public class SellarGrieta implements Expression {

    private static final int COSTO_MANA = 10;
    private static final int REDUCCION_INESTABILIDAD = 30;

    @Override
    public void interpret(Context context) {
        if (!context.consumirMana(COSTO_MANA)) {
            System.out.println("  -> SELLAR_GRIETA: maná insuficiente para sellar la grieta. " + context);
            return;
        }
        context.reducirInestabilidad(REDUCCION_INESTABILIDAD);
        System.out.println("  -> SELLAR_GRIETA: el alquimista sella una grieta de la torre. " + context);
    }
}
