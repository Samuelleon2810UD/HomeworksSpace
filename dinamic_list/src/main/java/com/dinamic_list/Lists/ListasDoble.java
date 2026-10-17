package com.dinamic_list.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dinamic_list.Nodes.NodoDouble;

public class ListasDoble {
    private int i;
    private NodoDouble p;
    private NodoDouble q;
    private NodoDouble cab;
    private NodoDouble aux;
    private List<Integer> lista = new ArrayList<>();
    private Random aleatorio = new Random();


    public int generarLista() {
        int valor = aleatorio.nextInt(6) + 3;
        int num1;
        int num2;
        for (i = 1; i <= valor; i++) {
            if(cab == null){
                num1 = aleatorio.nextInt(9) + 1;
                q = new NodoDouble(num1);
                q.sig = null;
                q.ant = null;
                cab = q;
                p = q;
            } else {
                num2 = aleatorio.nextInt(9) + 1;
                q = new NodoDouble(num2);
                q.ant = cab;
                q.sig = null;
                cab.sig = q;
                cab = q;
            }
        }
        return valor;
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
