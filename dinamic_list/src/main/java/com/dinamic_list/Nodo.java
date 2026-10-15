package com.dinamic_list;

/**
 * Nodo
 */
public class Nodo {
    int num;
    Nodo ant;
    Nodo sig; 

    // Constructor para inicializar el nodo con un valor
    public Nodo(int dato) {
        this.num = dato;
        this.ant = null;
        this.sig = null; 
    }
}
