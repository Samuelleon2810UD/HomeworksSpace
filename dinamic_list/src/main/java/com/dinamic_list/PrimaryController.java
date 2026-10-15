package com.dinamic_list;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PrimaryController {
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";

    private Listas controladorListas = new Listas();
    @FXML 
    private Label listLabel;
    @FXML
    private ComboBox<String> listType;
    @FXML
    private Button insertButton;
    @FXML
    private Button removeButton;

    @FXML
    private void initialize() {
        listType.getItems().addAll(SIMPLE, DOBLE);
        listType.setValue(SIMPLE);
    }

    private boolean esDoble() {
        return DOBLE.equals(listType.getValue());
    }

    @FXML
    private void changeListType() {
        // Insertar y quitar solo están implementados para la lista simple
        boolean doble = esDoble();
        insertButton.setDisable(doble);
        removeButton.setDisable(doble);
        mostrarLista("Lista " + (doble ? "doble" : "simple") + " seleccionada.");
    }

    @FXML
    private void generateList() throws IOException {
        if (esDoble()) {
            int cantidad = controladorListas.generarListaDoble();
            mostrarLista("Lista doble generada con " + cantidad + " elementos aleatorios.");
        } else {
            controladorListas.generarLista(4);
            mostrarLista("Lista generada con 4 elementos.");
        }
    }

    @FXML
    private void printLista() throws IOException {
        String text = esDoble() ? controladorListas.imprimirListaDoble() : controladorListas.imprimirLista();
        mostrarLista("Contenido: " + text);
    }

    @FXML
    private void insertNode() throws IOException {
        controladorListas.insertarNodo(2, 99);
        mostrarLista("Nodo 99 insertado en la posición 2.");
    }

    @FXML
    private void removeNode() {
        controladorListas.quitarNodo(2);
        mostrarLista("Nodo de la posición 2 eliminado.");
    }

    private void mostrarLista(String mensaje) {
        String visual = esDoble() ? controladorListas.visualizarListaDoble() : controladorListas.visualizarLista();
        listLabel.setText(mensaje + "\n\n" + visual);
    }
}
