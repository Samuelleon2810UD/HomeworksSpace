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
        listLabel.setText("Lista generada con 4 elementos.");
    }

    @FXML
    private void printLista() throws IOException {
        String text = controladorListas.imprimirLista();
        listLabel.setText(text);
    }

    
}
