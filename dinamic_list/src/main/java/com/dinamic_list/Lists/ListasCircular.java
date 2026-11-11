package com.dinamic_list.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dinamic_list.Nodes.Nodo;

public class ListasCircular {
    private Nodo p;
    private Nodo cab;
    private final Random aleatorio = new Random();

    public void generarLista() {
        generarLista(aleatorio.nextInt(10) + 1);
    }

    public void instanciarPrimerElemento() {
        p = new Nodo(aleatorio.nextInt(9) + 1);
        p.sig = p;
        cab = p;
    }

    public void generarLista(int cantidad) {
        p = null;
        cab = null;
        añadirNElementos(cantidad);
    }

    public void añadirNElementos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Nodo nuevo = new Nodo(aleatorio.nextInt(9) + 1);
            if (p == null) {
                p = nuevo;
                cab = nuevo;
                nuevo.sig = nuevo;
            } else {
                cab.sig = nuevo;
                cab = nuevo;
                cab.sig = p;
            }
        }
    }

    public String imprimirLista() {
        List<Integer> valores = new ArrayList<>();
        if (p == null) {
            return valores.toString();
        }
        Nodo actual = p;
        do {
            valores.add(actual.num);
            actual = actual.sig;
        } while (actual != p);
        return valores.toString();
    }

    public boolean insertarNodo(int posicion, int num) {
        int total = tamano();
        if (posicion < 1 || posicion > total + 1) {
            return false;
        }
        Nodo nuevo = new Nodo(num);
        if (p == null) {
            nuevo.sig = nuevo;
            p = nuevo;
            cab = nuevo;
            return true;
        }
        if (posicion == 1) {
            nuevo.sig = p;
            cab.sig = nuevo;
            p = nuevo;
            return true;
        }
        Nodo anterior = capturar(posicion - 1);
        nuevo.sig = anterior.sig;
        anterior.sig = nuevo;
        if (posicion == total + 1) {
            cab = nuevo;
        }
        return true;
    }

    public boolean insertarNodoDespuesDeValor(int valorReferencia, int num) {
        return insertarNodoDespuesDeValor(valorReferencia, 1, num);
    }

    public boolean insertarNodoDespuesDeValor(int valorReferencia, int ocurrencia, int num) {
        Nodo referencia = capturarPorValor(valorReferencia, ocurrencia);
        if (referencia == null) {
            return false;
        }
        Nodo nuevo = new Nodo(num);
        nuevo.sig = referencia.sig;
        referencia.sig = nuevo;
        if (referencia == cab) {
            cab = nuevo;
        }
        return true;
    }

    public boolean quitarNodo(int posicion) {
        int total = tamano();
        if (posicion < 1 || posicion > total) {
            return false;
        }
        if (total == 1) {
            p = null;
            cab = null;
            return true;
        }
        if (posicion == 1) {
            p = p.sig;
            cab.sig = p;
            return true;
        }
        Nodo anterior = capturar(posicion - 1);
        anterior.sig = anterior.sig.sig;
        if (posicion == total) {
            cab = anterior;
        }
        return true;
    }

    public boolean quitarNodoPorValor(int valor) {
        return quitarNodoPorValor(valor, 1);
    }

    public boolean quitarNodoPorValor(int valor, int ocurrencia) {
        if (p == null) {
            return false;
        }
        int coincidencia = 0;
        if (p.num == valor && ++coincidencia == ocurrencia) {
            if (p == cab) {
                p = null;
                cab = null;
            } else {
                p = p.sig;
                cab.sig = p;
            }
            return true;
        }
        Nodo anterior = p;
        Nodo actual = p.sig;
        while (actual != p) {
            if (actual.num == valor && ++coincidencia == ocurrencia) {
                anterior.sig = actual.sig;
                if (actual == cab) {
                    cab = anterior;
                }
                return true;
            }
            anterior = actual;
            actual = actual.sig;
        }
        return false;
    }

    public Nodo capturar(int posicion) {
        int total = tamano();
        if (posicion < 1 || posicion > total) {
            return null;
        }
        Nodo actual = p;
        for (int i = 1; i < posicion; i++) {
            actual = actual.sig;
        }
        return actual;
    }

    public Nodo capturarPorValor(int valor) {
        return capturarPorValor(valor, 1);
    }

    public Nodo capturarPorValor(int valor, int ocurrencia) {
        if (ocurrencia < 1) {
            return null;
        }
        if (p == null) {
            return null;
        }
        Nodo actual = p;
        int coincidencia = 0;
        do {
            if (actual.num == valor && ++coincidencia == ocurrencia) {
                return actual;
            }
            actual = actual.sig;
        } while (actual != p);
        return null;
    }

    public int tamano() {
        if (p == null) {
            return 0;
        }
        int total = 1;
        for (Nodo actual = p.sig; actual != p; actual = actual.sig) {
            total++;
        }
        return total;
    }

    public String visualizarLista() {
        return visualizarLista(0);
    }

    public String visualizarLista(int posicionActiva) {
        if (p == null) {
            return "La lista está vacía.";
        }
        StringBuilder representacion = new StringBuilder("p (inicio)\n  ↓\n");
        Nodo actual = p;
        for (int i = 0, total = tamano(); i < total; i++) {
            if (i > 0) {
                representacion.append("  →  ");
            }
            if (i + 1 == posicionActiva) {
                representacion.append("▶ ");
            }
            representacion.append("[").append(i + 1).append(" | num = ").append(actual.num).append("]");
            if (i == total - 1) {
                representacion.append(" (cab / último)");
            }
            if (i + 1 == posicionActiva) {
                representacion.append(" ◀");
            }
            actual = actual.sig;
        }
        return representacion.append("\n  ↖────────────── el enlace de cab vuelve a p").toString();
    }
}