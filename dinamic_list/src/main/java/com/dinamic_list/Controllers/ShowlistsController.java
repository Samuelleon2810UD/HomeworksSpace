package com.dinamic_list.Controllers;

import java.io.IOException;

import com.dinamic_list.Lists.Listas;
import com.dinamic_list.Lists.ListasDoble;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ShowlistsController {
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";

    private Listas controladorListas = new Listas();
    private ListasDoble controladorListasDoble = new ListasDoble();
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
            int cantidad = controladorListasDoble.generarLista();
            mostrarLista("Lista doble generada con " + cantidad + " elementos aleatorios.");
        } else {
            controladorListas.generarLista(4);
            mostrarLista("Lista generada con 4 elementos.");
        }
    }

    @FXML
    private void printLista() throws IOException {
        String text = esDoble() ? controladorListasDoble.imprimirLista() : controladorListas.imprimirLista();
        mostrarLista("Contenido: " + text);
    }

    @FXML
    private void insertNode(int position, int value) throws IOException {
        controladorListas.insertarNodo(position, value);
        mostrarLista("Nodo " + value + " insertado en la posición " + position + ".");
    }

    @FXML
    private void removeNode() {
        controladorListas.quitarNodo(2);
        mostrarLista("Nodo de la posición 2 eliminado.");
    }

    private void mostrarLista(String mensaje) {
        String visual = esDoble() ? controladorListasDoble.visualizarLista() : controladorListas.visualizarLista();
        listLabel.setText(mensaje + "\n\n" + visual);
    }
}
