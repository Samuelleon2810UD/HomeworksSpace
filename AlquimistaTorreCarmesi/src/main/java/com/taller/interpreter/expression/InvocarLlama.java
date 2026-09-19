package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Terminal: INVOCAR_LLAMA — el alquimista lanza fuego contra el Constructo.
 * <p>
 * Esta es una de las expresiones que TRANSFORMA el Context de dos maneras a
 * la vez: reduce la vida del Constructo Y aumenta la inestabilidad de la
 * torre (el fuego arcano sacude los cimientos). Una condición posterior
 * sobre "inestabilidad alta" puede así reaccionar a un estado que esta misma
 * expresión ayudó a crear.
 */
public class InvocarLlama implements Expression {

    private static final int DANIO = 35;
    private static final int COSTO_MANA = 15;
    private static final int AUMENTO_INESTABILIDAD = 25;

    @Override
    public void interpret(Context context) {
        if (!context.isConstructoActivo()) {
            System.out.println("  -> INVOCAR_LLAMA: el Constructo ya está destruido, no hay blanco. " + context);
            return;
        }
        if (!context.consumirMana(COSTO_MANA)) {
            System.out.println("  -> INVOCAR_LLAMA: maná insuficiente, el conjuro fracasa. " + context);
            return;
        }
        context.danarConstructo(DANIO, AUMENTO_INESTABILIDAD);
        System.out.println("  -> INVOCAR_LLAMA: el alquimista lanza fuego arcano contra el Constructo "
                + "(-" + DANIO + " vida, +" + AUMENTO_INESTABILIDAD + " inestabilidad). " + context);
    }
}
