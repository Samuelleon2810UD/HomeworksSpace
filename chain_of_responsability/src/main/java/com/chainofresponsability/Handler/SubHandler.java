package com.chainofresponsability.Handler;

import com.chainofresponsability.Problem.Problem;

/**
 * SubHandler
 * Representa a la persona concreta (monitor, profesor, coordinador, etc.)
 * que finalmente atiende el problema una vez que su Handler determinó
 * que puede resolverlo.
 */
public class SubHandler {

    private String name;
    private int level;
    private boolean isOcuped;
    private Problem problemAssigment;

    public SubHandler(String name, int level) {
        this.name = name;
        this.level = level;
        this.isOcuped = false;
        this.problemAssigment = null;
    }

    public void asignarProblema(Problem problem) {
        this.problemAssigment = problem;
        this.isOcuped = true;
    }

    public void liberar() {
        this.problemAssigment = null;
        this.isOcuped = false;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public boolean isOcuped() {
        return isOcuped;
    }

    public Problem getProblemAssigment() {
        return problemAssigment;
    }

    @Override
    public String toString() {
        return name + (isOcuped ? " (ocupado con: " + problemAssigment.getDescripcion() + ")" : " (libre)");
    }
}
