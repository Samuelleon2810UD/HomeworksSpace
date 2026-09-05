package com.chainofresponsability.Handler;

import com.chainofresponsability.Problem.Problem;
import com.chainofresponsability.Problem.RegularProblem;

/**
 * BaseHandler
 * Clase abstracta que agrupa el comportamiento común de los handlers
 * concretos (MonitorHandler, ProfesorHandler, CoordinadorHandler,
 * SecretariaHandler, DecanaturaHandler). Cada uno de ellos solo se
 * diferencia por su nombre de rol, su nivel de autoridad y el
 * SubHandler (persona) que tiene asignado.
 *
 * Nota de diseño: el diagrama no mostraba explícitamente esta clase,
 * pero se introduce para no repetir la misma implementación de
 * canResolve()/delegate() en las 5 subclases (principio DRY), sin
 * cambiar el contrato público definido por la interfaz Handler.
 */
public abstract class BaseHandler implements Handler {

    protected String nombreRol;
    protected int nivel;
    protected SubHandler subHandler;
    protected Problem problemaActual;

    public BaseHandler(String nombreRol, int nivel, SubHandler subHandler) {
        this.nombreRol = nombreRol;
        this.nivel = nivel;
        this.subHandler = subHandler;
    }

    @Override
    public boolean canResolve(Problem problem) {
        this.problemaActual = problem;
        return problem.isSolucionable(nivel);
    }

    @Override
    public void delegate() {
        if (problemaActual == null) {
            System.out.println("[" + nombreRol + "] No hay ningún problema pendiente para delegar.");
            return;
        }
        subHandler.asignarProblema(problemaActual);
        if (problemaActual instanceof RegularProblem) {
            ((RegularProblem) problemaActual).resolver(
                    "Atendido por " + subHandler.getName() + " (" + nombreRol + ")");
        }
        System.out.println("   -> [" + nombreRol + "] delega el problema \"" + problemaActual.getDescripcion()
                + "\" a " + subHandler.getName());
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public int getNivel() {
        return nivel;
    }

    public SubHandler getSubHandler() {
        return subHandler;
    }
}
