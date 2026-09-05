package com.chainofresponsability.Problem;
/**
 * Interfaz Problem.
 * Representa cualquier tipo de problema que puede entrar a la cadena
 * de responsabilidad para ser resuelto por algún Handler.
 */
public interface Problem {

    /**
     * Indica si este problema puede ser resuelto por un handler que
     * tiene el nivel de autoridad/competencia dado.
     *
     * @param nivelHandler nivel del handler que pregunta
     * @return true si el problema es solucionable con ese nivel
     */
    boolean isSolucionable(int nivelHandler);

    // Métodos de apoyo (no aparecían explícitos en el diagrama, pero son
    // necesarios para que PrincipalHandler y los Handlers puedan reportar
    // información del problema sin hacer casts a RegularProblem).
    String getDescripcion();

    int getLevel();
}
