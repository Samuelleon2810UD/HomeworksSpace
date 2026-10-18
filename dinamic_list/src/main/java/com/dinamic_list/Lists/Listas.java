package com.dinamic_list.Lists;

import java.util.ArrayList;
import java.util.List;

import com.dinamic_list.Nodes.Nodo;

public class Listas {
    private int i;
    private Nodo p;
    private Nodo q;
    private Nodo cab;
    private Nodo aux;
    private List<Integer> lista = new ArrayList<>();

    /** Crea una lista nueva con los valores 1, 2, ..., n (descarta la anterior). */
    public void generarLista(int n) {
        p = null;
        cab = null;
        for (i = 1; i <= n; i++) {
            if(cab == null){
                q = new Nodo(i);
                q.sig = null;
                cab = q;
                p = q;
            } else {
                q = new Nodo(i);
                q.sig = null;
                cab.sig = q;
                cab = q;
            }
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

    /**
     * Inserta un nodo de modo que quede en la posición indicada (1 = primero).
     * Devuelve false si la posición es inválida o queda fuera de la lista.
     */
    public boolean insertarNodo(int posicion, int num){
        if(posicion < 1){
            return false;
        }
        Nodo ins = new Nodo(num);
        if(this.p == null){
            this.p = ins;
            return true;
        }
        if(posicion == 1){
            ins.sig = this.p;
            this.p = ins;
            return true;
        }
        Nodo anterior = this.p;
        for(int i = 2; i < posicion && anterior != null; i++){
            anterior = anterior.sig;
        }
        if(anterior == null){
            return false;
        }
        ins.sig = anterior.sig;
        anterior.sig = ins;
        return true;
    }

    /** Quita el nodo de la posición indicada. Devuelve false si no existe. */
    public boolean quitarNodo(int posicion){
        if(posicion < 1 || this.p == null){
            return false;
        }
        if(posicion == 1){
            this.p = this.p.sig;
            return true;
        }

        Nodo anterior = this.p;
        Nodo actual = this.p.sig;
        for(int i = 2; i < posicion && actual != null; i++){
            anterior = actual;
            actual = actual.sig;
        }
        if(actual == null){
            return false;
        }
        anterior.sig = actual.sig;
        return true;
    }

    /** Devuelve el nodo de la posición indicada, o null si no existe. */
    public Nodo capturar(int posicion){
        if(posicion < 1){
            return null;
        }
        aux = this.p;
        for(i = 1; i < posicion && aux != null; i++){
            aux = aux.sig;
        }
        return aux;
    }

    public int tamano(){
        int total = 0;
        for(Nodo actual = this.p; actual != null; actual = actual.sig){
            total++;
        }
        return total;
    }

    public String visualizarLista(){
        StringBuilder representacion = new StringBuilder("HEAD");
        Nodo actual = this.p;
        while(actual != null){
            representacion.append(" -> [").append(actual.num).append("]");
            actual = actual.sig;
        }
        return representacion.append(" -> NULL").toString();
    }
}
