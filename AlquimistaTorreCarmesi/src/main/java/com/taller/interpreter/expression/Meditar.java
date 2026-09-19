package com.taller.interpreter.expression;

import com.taller.interpreter.context.Context;

/**
 * Terminal: MEDITAR — el alquimista se detiene a recuperar maná. El tiempo
 * que pasa quieto también deja que la torre siga crujiendo un poco.
 */
public class Meditar implements Expression {

    private static final int MANA_RECUPERADO = 25;
    private static final int AUMENTO_INESTABILIDAD = 5;

    @Override
    public void interpret(Context context) {
        context.recuperarMana(MANA_RECUPERADO);
        context.aumentarInestabilidad(AUMENTO_INESTABILIDAD);
        System.out.println("  -> MEDITAR: el alquimista recupera maná mientras la torre sigue crujiendo. " + context);
    }
}
