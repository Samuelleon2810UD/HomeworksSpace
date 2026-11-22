package com.binary_tree.Arbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.binary_tree.Nodo.Nodo;
import com.binary_tree.Nodo.NodoSimple;

public class Arbol {
    public enum TipoComparacion {
        IGUALES,
        EQUIVALENTES,
        ISOMORFOS
    }

    public static class ParSubarboles {
        private final Nodo primero;
        private final Nodo segundo;

        private ParSubarboles(Nodo primero, Nodo segundo) {
            this.primero = primero;
            this.segundo = segundo;
        }

        public Nodo getPrimero() {
            return primero;
        }

        public Nodo getSegundo() {
            return segundo;
        }

        @Override
        public String toString() {
            return primero.num + " y " + segundo.num;
        }
    }

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

    public List<Integer> getNodosRama() {
        List<Integer> nodos = new ArrayList<>();
        recolectarNodosRama(raiz, nodos);
        return nodos;
    }

    public List<Integer> getNodosHoja() {
        List<Integer> nodos = new ArrayList<>();
        recolectarHojas(raiz, nodos);
        return nodos;
    }

    public int gradoNodo(int numero) {
        Nodo nodo = buscar(numero);
        if (nodo == null) {
            return -1;
        }
        return (nodo.left == null ? 0 : 1) + (nodo.right == null ? 0 : 1);
    }

    public List<Integer> getCaminoNodo(int numero) {
        List<Integer> camino = new ArrayList<>();
        Nodo actual = raiz;
        while (actual != null) {
            camino.add(actual.num);
            if (actual.num == numero) {
                return camino;
            }
            actual = numero < actual.num ? actual.left : actual.right;
        }
        return Collections.emptyList();
    }

    public int longitudNodo(int numero) {
        List<Integer> camino = getCaminoNodo(numero);
        return camino.isEmpty() ? -1 : camino.size();
    }

    public int alturaNodo(int numero) {
        List<Integer> camino = getCaminoNodo(numero);
        return camino.isEmpty() ? -1 : camino.size() - 1;
    }

    public int gradoArbol() {
        return gradoMaximo(raiz);
    }

    public int longitudArbol() {
        return longitud(raiz);
    }

    public int pesoArbol() {
        return contarNodos(raiz);
    }

    public List<Integer> getPreorden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerPreorden(raiz, resultado);
        return resultado;
    }

    public List<Integer> getInorden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerInorden(raiz, resultado);
        return resultado;
    }

    public List<Integer> getPostorden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerPostorden(raiz, resultado);
        return resultado;
    }

    public List<Integer> getCaminoHojaMasLejana() {
        if (raiz == null) {
            return Collections.emptyList();
        }
        List<Integer> camino = new ArrayList<>();
        caminoMasLargo(raiz, new ArrayList<Integer>(), camino);
        return camino;
    }

    public List<ParSubarboles> getSubarbolesComparables(TipoComparacion tipo) {
        List<Nodo> raices = new ArrayList<>();
        recolectarNodos(raiz, raices);
        List<ParSubarboles> pares = new ArrayList<>();

        for (int i = 0; i < raices.size(); i++) {
            for (int j = i + 1; j < raices.size(); j++) {
                Nodo primero = raices.get(i);
                Nodo segundo = raices.get(j);
                if (coinciden(primero, segundo, tipo)) {
                    pares.add(new ParSubarboles(primero, segundo));
                }
            }
        }
        return pares;
    }

    public int contarSubarbolesComparables(TipoComparacion tipo) {
        List<Nodo> raices = new ArrayList<>();
        recolectarNodos(raiz, raices);
        int cantidad = 0;

        for (int i = 0; i < raices.size(); i++) {
            for (int j = i + 1; j < raices.size(); j++) {
                if (coinciden(raices.get(i), raices.get(j), tipo)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
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

    private void recolectarNodosRama(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        if (nodo.left != null || nodo.right != null) {
            resultado.add(nodo.num);
        }
        recolectarNodosRama(nodo.left, resultado);
        recolectarNodosRama(nodo.right, resultado);
    }

    private void recolectarHojas(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        if (nodo.left == null && nodo.right == null) {
            resultado.add(nodo.num);
            return;
        }
        recolectarHojas(nodo.left, resultado);
        recolectarHojas(nodo.right, resultado);
    }

    private int gradoMaximo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int gradoActual = (nodo.left == null ? 0 : 1) + (nodo.right == null ? 0 : 1);
        return Math.max(gradoActual, Math.max(gradoMaximo(nodo.left), gradoMaximo(nodo.right)));
    }

    private int longitud(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(longitud(nodo.left), longitud(nodo.right));
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.left) + contarNodos(nodo.right);
    }

    private void recorrerPreorden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        resultado.add(nodo.num);
        recorrerPreorden(nodo.left, resultado);
        recorrerPreorden(nodo.right, resultado);
    }

    private void recorrerInorden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        recorrerInorden(nodo.left, resultado);
        resultado.add(nodo.num);
        recorrerInorden(nodo.right, resultado);
    }

    private void recorrerPostorden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        recorrerPostorden(nodo.left, resultado);
        recorrerPostorden(nodo.right, resultado);
        resultado.add(nodo.num);
    }

    private void caminoMasLargo(Nodo nodo, List<Integer> actual, List<Integer> mejor) {
        if (nodo == null) {
            return;
        }
        actual.add(nodo.num);
        if (nodo.left == null && nodo.right == null && actual.size() > mejor.size()) {
            mejor.clear();
            mejor.addAll(actual);
        } else {
            caminoMasLargo(nodo.left, actual, mejor);
            caminoMasLargo(nodo.right, actual, mejor);
        }
        actual.remove(actual.size() - 1);
    }

    private Nodo buscar(int numero) {
        Nodo actual = raiz;
        while (actual != null) {
            if (actual.num == numero) {
                return actual;
            }
            actual = numero < actual.num ? actual.left : actual.right;
        }
        return null;
    }

    private void recolectarNodos(Nodo nodo, List<Nodo> resultado) {
        if (nodo == null) {
            return;
        }
        resultado.add(nodo);
        recolectarNodos(nodo.left, resultado);
        recolectarNodos(nodo.right, resultado);
    }

    private boolean coinciden(Nodo primero, Nodo segundo, TipoComparacion tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de comparación es obligatorio.");
        }
        switch (tipo) {
            case IGUALES:
                return iguales(primero, segundo);
            case EQUIVALENTES:
                return equivalentes(primero, segundo);
            case ISOMORFOS:
                return isomorfos(primero, segundo);
            default:
                throw new IllegalArgumentException("Tipo de comparación no válido: " + tipo);
        }
    }

    private boolean iguales(Nodo primero, Nodo segundo) {
        if (primero == null || segundo == null) {
            return primero == segundo;
        }
        return primero.num == segundo.num
                && iguales(primero.left, segundo.left)
                && iguales(primero.right, segundo.right);
    }

    private boolean equivalentes(Nodo primero, Nodo segundo) {
        if (primero == null || segundo == null) {
            return primero == segundo;
        }
        return equivalentes(primero.left, segundo.left)
                && equivalentes(primero.right, segundo.right);
    }

    private boolean isomorfos(Nodo primero, Nodo segundo) {
        if (primero == null || segundo == null) {
            return primero == segundo;
        }
        return (isomorfos(primero.left, segundo.left) && isomorfos(primero.right, segundo.right))
                || (isomorfos(primero.left, segundo.right) && isomorfos(primero.right, segundo.left));
    }
}
