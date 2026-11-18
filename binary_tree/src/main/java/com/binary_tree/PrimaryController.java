package com.binary_tree;

import java.util.IdentityHashMap;
import java.util.Map;

import com.binary_tree.Arbol.Arbol;
import com.binary_tree.Nodo.Nodo;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PrimaryController {
    @FXML
    private Label listaLabel;
    @FXML
    private Label mensajeLabel;
    @FXML
    private Button construirButton;
    @FXML
    private Button vaciarButton;
    @FXML
    private Pane arbolPane;

    private final Arbol arbol = new Arbol();

    @FXML
    private void initialize() {
        construirButton.setDisable(true);
        vaciarButton.setDisable(true);
    }

    @FXML
    private void generarLista() {
        arbol.generarLista();
        listaLabel.setText(arbol.imprimirLista());
        mensajeLabel.setText("Se añadieron elementos a la lista. Ahora puedes construir el árbol.");
        arbolPane.getChildren().clear();
        construirButton.setDisable(false);
        vaciarButton.setDisable(false);
    }

    @FXML
    private void vaciarLista() {
        arbol.vaciarLista();
        listaLabel.setText(arbol.imprimirLista());
        mensajeLabel.setText("La lista y el árbol se vaciaron.");
        arbolPane.getChildren().clear();
        construirButton.setDisable(true);
        vaciarButton.setDisable(true);
    }

    @FXML
    private void construirArbol() {
        Nodo raiz = arbol.createTree();
        arbolPane.getChildren().clear();
        if (raiz == null) {
            mensajeLabel.setText("La lista está vacía; genera una lista antes de construir el árbol.");
            return;
        }

        int cantidad = contarNodos(raiz);
        int profundidad = profundidadMaxima(raiz);
        double ancho = Math.max(700, (cantidad + 1) * 70);
        double alto = profundidad * 85 + 100;
        arbolPane.setPrefSize(ancho, alto);

        Map<Nodo, Point2D> posiciones = new IdentityHashMap<>();
        asignarPosiciones(raiz, 0, new int[] {0}, ancho / (cantidad + 1), posiciones);
        dibujarConexiones(raiz, posiciones);
        dibujarNodos(raiz, posiciones);
        mensajeLabel.setText("Árbol binario de búsqueda construido desde la lista.");
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.left) + contarNodos(nodo.right);
    }

    private int profundidadMaxima(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(profundidadMaxima(nodo.left), profundidadMaxima(nodo.right));
    }

    private void asignarPosiciones(
            Nodo nodo, int profundidad, int[] indice, double separacion, Map<Nodo, Point2D> posiciones) {
        if (nodo == null) {
            return;
        }
        asignarPosiciones(nodo.left, profundidad + 1, indice, separacion, posiciones);
        double x = separacion * (++indice[0]);
        double y = 45 + profundidad * 85;
        posiciones.put(nodo, new Point2D(x, y));
        asignarPosiciones(nodo.right, profundidad + 1, indice, separacion, posiciones);
    }

    private void dibujarConexiones(Nodo nodo, Map<Nodo, Point2D> posiciones) {
        if (nodo == null) {
            return;
        }
        Point2D origen = posiciones.get(nodo);
        agregarConexion(nodo.left, origen, posiciones);
        agregarConexion(nodo.right, origen, posiciones);
        dibujarConexiones(nodo.left, posiciones);
        dibujarConexiones(nodo.right, posiciones);
    }

    private void agregarConexion(Nodo hijo, Point2D origen, Map<Nodo, Point2D> posiciones) {
        if (hijo == null) {
            return;
        }
        Point2D destino = posiciones.get(hijo);
        arbolPane.getChildren().add(new Line(origen.getX(), origen.getY(), destino.getX(), destino.getY()));
    }

    private void dibujarNodos(Nodo nodo, Map<Nodo, Point2D> posiciones) {
        if (nodo == null) {
            return;
        }
        Point2D posicion = posiciones.get(nodo);
        Circle circulo = new Circle(posicion.getX(), posicion.getY(), 20, Color.LIGHTBLUE);
        circulo.setStroke(Color.DARKSLATEBLUE);
        Text valor = new Text(Integer.toString(nodo.num));
        valor.setX(posicion.getX() - valor.getLayoutBounds().getWidth() / 2);
        valor.setY(posicion.getY() + valor.getLayoutBounds().getHeight() / 4);
        arbolPane.getChildren().addAll(circulo, valor);
        dibujarNodos(nodo.left, posiciones);
        dibujarNodos(nodo.right, posiciones);
    }
}
