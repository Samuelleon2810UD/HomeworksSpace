package com.dinamic_list;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {
    private Listas controladorListas = new Listas();
    @FXML 
    private Label listLabel;

    @FXML
    private void generateList() throws IOException {
        controladorListas.generarLista(4);
        mostrarLista("Lista generada con 4 elementos.");
    }

    @FXML
    private void printLista() throws IOException {
        String text = controladorListas.imprimirLista();
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
        listLabel.setText(mensaje + "\n\n" + controladorListas.visualizarLista());
    }
}
