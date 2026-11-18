package com.binary_tree.Arbol;

import java.util.Random;

import com.binary_tree.Nodo.Nodo;
import com.binary_tree.Nodo.NodoSimple;

public class Arbol {
    private NodoSimple cabeza;
    private NodoSimple ultimo;
    private Nodo raiz;

    public void generarLista() {
        Random random = new Random();
        int cantidad = random.nextInt(10) + 1;
        int limiteAleatorio = Math.max(100, contarElementos() + cantidad);
        raiz = null;

        for (int i = 0; i < cantidad; i++) {
            int numero;
            do {
                numero = random.nextInt(limiteAleatorio) + 1;
            } while (existeEnLista(numero));

            NodoSimple nuevo = new NodoSimple(numero);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                ultimo.sig = nuevo;
            }
            ultimo = nuevo;
        }
    }

    private boolean existeEnLista(int numero) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.num == numero) {
                return true;
            }
            actual = actual.sig;
        }
        return false;
    }

    private int contarElementos() {
        int cantidad = 0;
        NodoSimple actual = cabeza;
        while (actual != null) {
            cantidad++;
            actual = actual.sig;
        }
        return cantidad;
    }

    public void vaciarLista() {
        cabeza = null;
        ultimo = null;
        raiz = null;
    }

    public String imprimirLista() {
        StringBuilder lista = new StringBuilder("HEAD");
        NodoSimple actual = cabeza;
        while (actual != null) {
            lista.append(" -> [").append(actual.num).append("]");
            actual = actual.sig;
        }
        lista.append(" -> NULL");
        System.out.println("Lista simple: " + lista);
        return lista.toString();
    }

    public Nodo createTree() {
        raiz = null;
        NodoSimple actual = cabeza;
        while (actual != null) {
            insertar(actual.num);
            actual = actual.sig;
        }
        return raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    private void insertar(int numero) {
        Nodo nuevo = new Nodo(numero);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        Nodo actual = raiz;
        while (true) {
            if (numero < actual.num) {
                if (actual.left == null) {
                    actual.left = nuevo;
                    return;
                }
                actual = actual.left;
            } else {
                if (actual.right == null) {
                    actual.right = nuevo;
                    return;
                }
                actual = actual.right;
            }
        }
    }
}
