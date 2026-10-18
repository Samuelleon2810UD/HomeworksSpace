package com.dinamic_list.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dinamic_list.Nodes.NodoDouble;

/**
 * Lista doblemente enlazada.
 * p   : primer nodo (extremo izquierdo).
 * cab : último nodo (extremo derecho).
 */
public class ListasDoble {
    private int i;
    private NodoDouble p;
    private NodoDouble q;
    private NodoDouble cab;
    private NodoDouble aux;
    private List<Integer> lista = new ArrayList<>();
    private Random aleatorio = new Random();

    public void generarLista(int n, boolean porIzquierda) {
        p = null;
        cab = null;
        for (i = 1; i <= n; i++) {
            int num = aleatorio.nextInt(9) + 1;
            if (porIzquierda) {
                insertarIzquierda(num);
            } else {
                insertarDerecha(num);
            }
        }
    }

    public void insertarIzquierda(int num) {
        q = new NodoDouble(num);
        q.ant = null;
        q.sig = null;
        if (cab == null) {
            cab = q;
            p = q;
        } else {
            q.sig = p;
            p.ant = q;
            p = q;
        }
    }

    /** Inserta un nodo después del último (cab pasa a ser el nuevo nodo). */
    public void insertarDerecha(int num) {
        q = new NodoDouble(num);
        q.ant = null;
        q.sig = null;
        if (cab == null) {
            cab = q;
            p = q;
        } else {
            q.ant = cab;
            cab.sig = q;
            cab = q;
        }
    }

    public String imprimirLista() {
        lista.clear();
        aux = this.p;
        while(aux != null){
            lista.add(aux.num);
            aux = aux.sig;
        }
        return lista.toString();
    }

    public boolean insertarNodo(int posicion, int num) {
        if (posicion < 1) {
            return false;
        }
        NodoDouble ins = new NodoDouble(num);
        ins.ant = null;
        ins.sig = null;
        if (this.p == null) {
            this.p = ins;
            this.cab = ins;
            return true;
        }
        if (posicion == 1) {
            ins.sig = this.p;
            this.p.ant = ins;
            this.p = ins;
            return true;
        }
        NodoDouble anterior = this.p;
        for (int i = 2; i < posicion && anterior != null; i++) {
            anterior = anterior.sig;
        }
        if (anterior == null) {
            return false;
        }
        NodoDouble siguiente = anterior.sig;
        ins.ant = anterior;
        ins.sig = siguiente;
        anterior.sig = ins;
        if (siguiente != null) {
            siguiente.ant = ins;
        } else {
            this.cab = ins;
        }
        return true;
    }

    public boolean quitarNodo(int posicion) {
        if (posicion < 1 || this.p == null) {
            return false;
        }
        if (posicion == 1) {
            this.p = this.p.sig;
            if (this.p != null) {
                this.p.ant = null;
            } else {
                this.cab = null;
            }
            return true;
        }
        NodoDouble anterior = this.p;
        NodoDouble actual = this.p.sig;
        for (int i = 2; i < posicion && actual != null; i++) {
            anterior = actual;
            actual = actual.sig;
        }
        if (actual == null) {
            return false;
        }
        NodoDouble siguiente = actual.sig;
        anterior.sig = siguiente;
        if (siguiente != null) {
            siguiente.ant = anterior;
        } else {
            this.cab = anterior;
        }
        return true;
    }

    public NodoDouble capturar(int posicion) {
        if (posicion < 1) {
            return null;
        }
        aux = this.p;
        for (i = 1; i < posicion && aux != null; i++) {
            aux = aux.sig;
        }
        return aux;
    }

    public int tamano() {
        int total = 0;
        for (NodoDouble actual = this.p; actual != null; actual = actual.sig) {
            total++;
        }
        return total;
    }

    public String visualizarLista(){
        StringBuilder representacion = new StringBuilder("NULL");
        NodoDouble actual = this.p;
        while(actual != null){
            representacion.append(" <-> [").append(actual.num).append("]");
            actual = actual.sig;
        }
        return representacion.append(" <-> NULL").toString();
    }
}
