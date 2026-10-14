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
        lista.clear();
        aux = null;
        cab = null;
        aux = this.p;
        while(aux != null){
            lista.add(aux.num);
            aux = aux.sig;
        }
        return lista.toString();
    }

    public void insertarNodo(int count , int num){
        Nodo temp1 = this.p ;
        Nodo temp2 = this.p.sig;
        for(int i = 1; i < count - 1; i++){
            if(i == count){
                break;
            }
            temp1 = temp2;
            temp2 = temp2.sig;
        }
        Nodo ins = new Nodo(num);
        ins.sig = temp2;
        temp1.sig = ins;
    }

    public void quitarNodo(int posicion){
        if(posicion < 1 || this.p == null){
            return;
        }
        if(posicion == 1){
            this.p = this.p.sig;
            return;
        }

        Nodo anterior = this.p;
        Nodo actual = this.p.sig;
        for(int i = 2; i < posicion && actual != null; i++){
            anterior = actual;
            actual = actual.sig;
        }
        if(actual != null){
            anterior.sig = actual.sig;
        }
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
