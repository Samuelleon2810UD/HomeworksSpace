package com.dinamic_list.Controllers;

import java.io.IOException;

import com.dinamic_list.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * Menú principal: desde aquí se abren las interfaces emergentes.
 */
public class PrimaryController {

    @FXML
    private void abrirListas() {
        abrir("showlists", "Listas enlazadas");
    }

    @FXML
    private void abrirCajero() {
        abrir("cajeroSimple", "Cajero de atención al cliente");
    }

    private void abrir(String fxml, String titulo) {
        try {
            App.abrirVentana(fxml, titulo);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana: " + e.getMessage()).show();
        }
    }
}
