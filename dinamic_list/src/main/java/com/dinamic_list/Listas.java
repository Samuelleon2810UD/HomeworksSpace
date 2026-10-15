package com.dinamic_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Listas {
    private int i;
    private Nodo p;
    private Nodo q;
    private Nodo cab;
    private Nodo aux;
    private List<Integer> lista = new ArrayList<>();

    // Lista doblemente enlazada (cabeza y cola propias para no mezclarla con la simple)
    private Nodo pDoble;
    private Nodo cabDoble;
    private Random aleatorio = new Random();

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

    // Genera una lista doble con un número aleatorio de nodos (3 a 8)
    // y valores aleatorios (1 a 9). Devuelve la cantidad de nodos creados.
    public int generarListaDoble() {
        int valor = aleatorio.nextInt(6) + 3;
        int num1;
        int num2;
        for (i = 1; i <= valor; i++) {
            if(cabDoble == null){
                num1 = aleatorio.nextInt(9) + 1;
                q = new Nodo(num1);
                q.sig = null;
                q.ant = null;
                cabDoble = q;
                pDoble = q;
            } else {
                num2 = aleatorio.nextInt(9) + 1;
                q = new Nodo(num2);
                q.ant = cabDoble;
                q.sig = null;
                cabDoble.sig = q;
                cabDoble = q;
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

    public String imprimirListaDoble() {
        lista.clear();
        aux = null;
        cabDoble = null;
        aux = this.pDoble;
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

    public String visualizarListaDoble(){
        StringBuilder representacion = new StringBuilder("NULL");
        Nodo actual = this.pDoble;
        while(actual != null){
            representacion.append(" <-> [").append(actual.num).append("]");
            actual = actual.sig;
        }
        return representacion.append(" <-> NULL").toString();
    }
}
