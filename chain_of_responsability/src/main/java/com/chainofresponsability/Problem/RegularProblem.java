package com.chainofresponsability.Problem;
/**
 * RegularProblem
 * Implementación concreta de Problem. Representa un problema típico
 * reportado por un estudiante, con un nivel de complejidad asociado.
 */
public class RegularProblem implements Problem {

    private String description;
    private String actualSolution;
    private boolean isResolved;
    private int level;

    public RegularProblem(String description, int level) {
        this.description = description;
        this.level = level;
        this.isResolved = false;
        this.actualSolution = "";
    }

    @Override
    public boolean isSolucionable(int nivelHandler) {
        // Un handler puede resolver el problema si su nivel de autoridad
        // es igual o superior al nivel requerido por el problema.
        return this.level <= nivelHandler;
    }

    @Override
    public String getDescripcion() {
        return description;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public void resolver(String solucion) {
        this.actualSolution = solucion;
        this.isResolved = true;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public String getActualSolution() {
        return actualSolution;
    }

    @Override
    public String toString() {
        return String.format("Problema[\"%s\", nivel=%d, resuelto=%s%s]",
                description, level, isResolved,
                isResolved ? ", solucion=\"" + actualSolution + "\"" : "");
    }
}
