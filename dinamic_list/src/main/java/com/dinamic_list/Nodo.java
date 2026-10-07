package com.dinamic_list;

/**
 * Nodo
 */
public class Nodo {
    int num;
    Nodo sig; 

    // Constructor para inicializar el nodo con un valor
    public Nodo(int dato) {
        this.num = dato;
        this.sig = null; 
    }
}
