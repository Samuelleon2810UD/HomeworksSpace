package com.chainofresponsability.Handler;
import java.util.ArrayList;
import java.util.List;

import com.chainofresponsability.Problem.Problem;

/**
 * PrincipalHandler
 * Es quien conoce la cadena completa de Handlers (handlersList) y la
 * lista de problemas por atender (problemsList). Para cada problema
 * seleccionado recorre la cadena en orden, encuentra el primer handler
 * capaz de resolverlo (canResolve) y le delega el trabajo (delegate).
 *
 * Nota de diseño: en el diagrama, PrincipalHandler aparece con una
 * relación de implementación hacia <<interface>> Handler, pero sus
 * métodos canResolve()/delegate() no reciben un Problem como parámetro
 * (a diferencia de la firma de Handler). Por esa razón aquí
 * PrincipalHandler NO implementa formalmente la interfaz Handler (para
 * no romper el contrato ni forzar una firma inconsistente); en cambio
 * conserva exactamente los métodos que muestra el diagrama:
 * canResolve(), delegate() y getSate().
 */
public class PrincipalHandler {

    private List<Problem> problemsList;
    private List<Handler> handlersList;
    private Problem selectedProblem;
    private Handler selectedHandler;
    private String problemState;

    public PrincipalHandler() {
        this.problemsList = new ArrayList<>();
        this.handlersList = new ArrayList<>();
        this.problemState = "SIN_PROCESAR";
    }

    public void agregarProblema(Problem problem) {
        problemsList.add(problem);
    }

    public void agregarHandler(Handler handler) {
        handlersList.add(handler);
    }

    public List<Problem> getProblemsList() {
        return problemsList;
    }

    public List<Handler> getHandlersList() {
        return handlersList;
    }

    public void setSelectedProblem(Problem problem) {
        this.selectedProblem = problem;
    }

    public Problem getSelectedProblem() {
        return selectedProblem;
    }

    public Handler getSelectedHandler() {
        return selectedHandler;
    }

    /**
     * Recorre la cadena de handlers en orden y busca el primero que
     * pueda resolver el problema actualmente seleccionado.
     */
    public boolean canResolve() {
        for (Handler handler : handlersList) {
            if (handler.canResolve(selectedProblem)) {
                selectedHandler = handler;
                return true;
            }
        }
        selectedHandler = null;
        return false;
    }

    /**
     * Delega el problema seleccionado al handler encontrado por
     * canResolve() y actualiza el estado del proceso.
     */
    public void delegate() {
        if (selectedHandler != null) {
            selectedHandler.delegate();
            problemState = "RESUELTO por " + selectedHandler.getClass().getSimpleName();
        } else {
            problemState = "SIN RESOLVER: ningún handler de la cadena pudo atenderlo";
        }
    }

    public String getSate() {
        return problemState;
    }

    /**
     * Procesa un único problema: lo selecciona, recorre la cadena y
     * delega el resultado. Método de conveniencia para el Main.
     */
    public void procesarProblema(Problem problem) {
        setSelectedProblem(problem);
        System.out.println("\n>> Procesando: \"" + problem.getDescripcion() + "\" (nivel " + problem.getLevel() + ")");
        canResolve();
        delegate();
        System.out.println(">> Estado final: " + getSate());
    }

    /**
     * Procesa toda la lista de problemas pendientes en orden.
     */
    public void procesarTodos() {
        if (problemsList.isEmpty()) {
            System.out.println("No hay problemas pendientes por procesar.");
            return;
        }
        for (Problem p : problemsList) {
            procesarProblema(p);
        }
    }
}
