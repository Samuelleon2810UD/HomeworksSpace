package com.dinamic_list.Controllers;

import com.dinamic_list.Lists.Listas;
import com.dinamic_list.Lists.ListasDoble;
import com.dinamic_list.Nodes.Nodo;
import com.dinamic_list.Nodes.NodoDouble;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ShowlistsController {
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";
    private static final int MAX_ELEMENTOS = 1000;
    private static final String ESTILO_OK = "-fx-text-fill: #176b58; -fx-font-weight: bold;";
    private static final String ESTILO_ERROR = "-fx-text-fill: #a63d2a; -fx-font-weight: bold;";

    private final Listas controladorListas = new Listas();
    private final ListasDoble controladorListasDoble = new ListasDoble();

    @FXML
    private ComboBox<String> listType;
    @FXML
    private TextField cantidadField;
    @FXML
    private HBox ladoBox;
    @FXML
    private RadioButton izquierdaRadio;
    @FXML
    private TextField posInsertarField;
    @FXML
    private TextField valorField;
    @FXML
    private HBox extremosBox;
    @FXML
    private TextField posQuitarField;
    @FXML
    private TextField posCapturarField;
    @FXML
    private Label statusLabel;
    @FXML
    private Label listLabel;

    @FXML
    private void initialize() {
        listType.getItems().addAll(SIMPLE, DOBLE);
        listType.setValue(SIMPLE);
        mostrarControlesDoble(false);
        statusLabel.setText("Elige un tipo de lista y genera una.");
        listLabel.setText("Genera una lista para ver sus nodos.");
    }

    private boolean esDoble() {
        return DOBLE.equals(listType.getValue());
    }

    private void mostrarControlesDoble(boolean visible) {
        ladoBox.setVisible(visible);
        ladoBox.setManaged(visible);
        extremosBox.setVisible(visible);
        extremosBox.setManaged(visible);
    }

    @FXML
    private void changeListType() {
        boolean doble = esDoble();
        mostrarControlesDoble(doble);
        exito("Lista " + (doble ? "doble" : "simple") + " seleccionada.");
    }

    @FXML
    private void generateList() {
        Integer n = leerEntero(cantidadField, "la cantidad de elementos");
        if (n == null) {
            return;
        }
        if (n < 1 || n > MAX_ELEMENTOS) {
            error("La cantidad debe estar entre 1 y " + MAX_ELEMENTOS + ".");
            return;
        }
        if (esDoble()) {
            boolean izquierda = izquierdaRadio.isSelected();
            controladorListasDoble.generarLista(n, izquierda);
            exito("Lista doble generada con " + n + " elementos aleatorios, insertados por la "
                    + (izquierda ? "izquierda" : "derecha") + ".");
        } else {
            controladorListas.generarLista(n);
            exito("Lista generada con " + n + " elementos.");
        }
    }

    @FXML
    private void printLista() {
        String text = esDoble() ? controladorListasDoble.imprimirLista() : controladorListas.imprimirLista();
        exito("Contenido: " + text);
    }

    @FXML
    private void insertNode() {
        Integer posicion = leerEntero(posInsertarField, "la posición");
        if (posicion == null) {
            return;
        }
        Integer valor = leerEntero(valorField, "el valor");
        if (valor == null) {
            return;
        }
        int limite = tamano() + 1;
        if (posicion < 1 || posicion > limite) {
            error("La posición debe estar entre 1 y " + limite + ".");
            return;
        }
        boolean insertado = esDoble()
                ? controladorListasDoble.insertarNodo(posicion, valor)
                : controladorListas.insertarNodo(posicion, valor);
        if (insertado) {
            exito("Nodo " + valor + " insertado en la posición " + posicion + ".");
        } else {
            error("No se pudo insertar en la posición " + posicion + ".");
        }
    }

    @FXML
    private void insertLeft() {
        insertarEnExtremo(true);
    }

    @FXML
    private void insertRight() {
        insertarEnExtremo(false);
    }

    private void insertarEnExtremo(boolean izquierda) {
        Integer valor = leerEntero(valorField, "el valor");
        if (valor == null) {
            return;
        }
        if (izquierda) {
            controladorListasDoble.insertarIzquierda(valor);
        } else {
            controladorListasDoble.insertarDerecha(valor);
        }
        exito("Nodo " + valor + " insertado a la " + (izquierda ? "izquierda" : "derecha") + " de la lista.");
    }

    @FXML
    private void removeNode() {
        Integer posicion = leerPosicionExistente(posQuitarField);
        if (posicion == null) {
            return;
        }
        boolean quitado = esDoble()
                ? controladorListasDoble.quitarNodo(posicion)
                : controladorListas.quitarNodo(posicion);
        if (quitado) {
            exito("Nodo de la posición " + posicion + " eliminado.");
        } else {
            error("No se pudo quitar la posición " + posicion + ".");
        }
    }

    @FXML
    private void captureNode() {
        Integer posicion = leerPosicionExistente(posCapturarField);
        if (posicion == null) {
            return;
        }
        String info;
        if (esDoble()) {
            NodoDouble nodo = controladorListasDoble.capturar(posicion);
            if (nodo == null) {
                error("No existe un nodo en la posición " + posicion + ".");
                return;
            }
            info = "anterior = " + valorDe(nodo.ant) + ", num = " + nodo.num + ", siguiente = " + valorDe(nodo.sig);
        } else {
            Nodo nodo = controladorListas.capturar(posicion);
            if (nodo == null) {
                error("No existe un nodo en la posición " + posicion + ".");
                return;
            }
            info = "num = " + nodo.num + ", siguiente = " + valorDe(nodo.sig);
        }
        exito("Nodo capturado en la posición " + posicion + " → " + info);
    }

    private int tamano() {
        return esDoble() ? controladorListasDoble.tamano() : controladorListas.tamano();
    }

    /** Lee una posición que debe existir en la lista actual (1 a tamaño). Devuelve null y muestra el error si no es válida. */
    private Integer leerPosicionExistente(TextField campo) {
        Integer posicion = leerEntero(campo, "la posición");
        if (posicion == null) {
            return null;
        }
        int total = tamano();
        if (total == 0) {
            error("La lista está vacía.");
            return null;
        }
        if (posicion < 1 || posicion > total) {
            error("La posición debe estar entre 1 y " + total + ".");
            return null;
        }
        return posicion;
    }

    private Integer leerEntero(TextField campo, String nombre) {
        String texto = campo.getText() == null ? "" : campo.getText().trim();
        if (texto.isEmpty()) {
            error("Escribe " + nombre + ".");
            return null;
        }
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            error("Escribe " + nombre + " como un número entero.");
            return null;
        }
    }

    private String valorDe(Nodo nodo) {
        return nodo == null ? "NULL" : String.valueOf(nodo.num);
    }

    private String valorDe(NodoDouble nodo) {
        return nodo == null ? "NULL" : String.valueOf(nodo.num);
    }

    private void exito(String mensaje) {
        statusLabel.setStyle(ESTILO_OK);
        statusLabel.setText(mensaje);
        listLabel.setText(esDoble() ? controladorListasDoble.visualizarLista() : controladorListas.visualizarLista());
    }

    private void error(String mensaje) {
        statusLabel.setStyle(ESTILO_ERROR);
        statusLabel.setText(mensaje);
    }
}
