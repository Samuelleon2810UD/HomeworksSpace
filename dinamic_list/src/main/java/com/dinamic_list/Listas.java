package com.dinamic_list;

import java.util.ArrayList;
import java.util.List;

public class Listas {
    private int i;
    private Nodo p;
    private Nodo q;
    private Nodo cab;
    private Nodo aux;
    private List<Integer> lista = new ArrayList<>();

    public void generarLista(int n) {
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
        aux = null;
        cab = null;
        aux = this.p;
        while(aux != null){
            lista.add(aux.num);
            aux = aux.sig;
        }
        return lista.toString();
    }
}
