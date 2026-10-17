package com.dinamic_list.Nodes;

public class NodoDouble {
    public int num;
    public NodoDouble ant;
    public NodoDouble sig; 

public NodoDouble(int dato) {
        this.num = dato;
        this.ant = null;
        this.sig = null; 
    }
}
