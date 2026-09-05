package com.chainofresponsability.Handler;
import com.chainofresponsability.Problem.Problem;

/**
 * Interfaz Handler.
 * Contrato que deben cumplir todos los eslabones de la cadena de
 * responsabilidad (Monitor, Profesor, Coordinador, Secretaria, Decanatura
 * y también PrincipalHandler, que orquesta la cadena).
 */
public interface Handler {

    /**
     * Determina si este handler puede resolver el problema recibido.
     */
    boolean canResolve(Problem problem);

    /**
     * Delega (asigna) el problema previamente evaluado a quien
     * corresponda para que sea atendido.
     */
    void delegate();
}
