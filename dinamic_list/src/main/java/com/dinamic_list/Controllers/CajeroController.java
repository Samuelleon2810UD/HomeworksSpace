package com.dinamic_list.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.dinamic_list.Lists.Listas;
import com.dinamic_list.Nodes.Nodo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CajeroController {
    private static final String ESTILO_OK = "-fx-text-fill: #176b58; -fx-font-weight: bold;";
    private static final String ESTILO_ERROR = "-fx-text-fill: #a63d2a; -fx-font-weight: bold;";

    private final Listas cola = new Listas();
    private final List<String> clientes = new ArrayList<>();

    @FXML
    private TextField clienteField;
    @FXML
    private TextField tramitesField;
    @FXML
    private Label statusLabel;
    @FXML
    private Label listLabel;
    @FXML
    private Label countLabel;

    @FXML
    private void initialize() {
        statusLabel.setText("Registra un cliente para comenzar.");
        actualizarCola();
    }

    @FXML
    private void agregarCliente() {
        String nombre = clienteField.getText() == null ? "" : clienteField.getText().trim();
        if (nombre.isEmpty()) {
            error("Escribe el nombre del cliente.");
            return;
        }

        Integer tramites = leerTramites();
        if (tramites == null) {
            return;
        }
        if (tramites < 1) {
            error("La cantidad de trámites debe ser al menos 1.");
            return;
        }

        int posicion = cola.tamano() + 1;
        if (!cola.insertarNodo(posicion, tramites)) {
            error("No se pudo agregar el cliente a la cola.");
            return;
        }
        clientes.add(nombre);
        clienteField.clear();
        tramitesField.clear();
        exito(nombre + " se agregó al final de la cola con " + tramites + " trámite(s).");
    }

    @FXML
    private void atenderCliente() {
        Nodo primero = cola.capturar(1);
        if (primero == null) {
            error("No hay clientes en espera.");
            return;
        }

        String nombre = clientes.get(0);
        int tramites = primero.num;
        if (!cola.quitarNodo(1)) {
            error("No se pudo atender al siguiente cliente.");
            return;
        }
        if (tramites > 5) {
            int restantes = tramites - 5;

            cola.insertarNodo(cola.tamano() + 1, restantes);
            clientes.remove(0);
            clientes.add(nombre);

            exito("Turno de " + nombre + " transferido con "
                    + restantes + " trámite(s) restantes.");
            return;
        }
        clientes.remove(0);
        exito("Turno atendido: " + nombre + " (" + tramites + " trámite(s)).");
    }

    private Integer leerTramites() {
        String texto = tramitesField.getText() == null ? "" : tramitesField.getText().trim();
        if (texto.isEmpty()) {
            error("Escribe la cantidad de trámites.");
            return null;
        }
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            error("La cantidad de trámites debe ser un número entero.");
            return null;
        }
    }

    private void actualizarCola() {
        StringBuilder representacion = new StringBuilder("HEAD");
        int total = cola.tamano();
        for (int i = 0; i < total; i++) {
            Nodo nodo = cola.capturar(i + 1);
            representacion.append(" -> [")
                    .append(clientes.get(i))
                    .append(" | trámites: ")
                    .append(nodo.num)
                    .append("]");
        }
        representacion.append(" -> NULL");
        listLabel.setText(representacion.toString());
        countLabel.setText("Clientes en espera: " + total);
    }

    private void exito(String mensaje) {
        statusLabel.setStyle(ESTILO_OK);
        statusLabel.setText(mensaje);
        actualizarCola();
    }

    private void error(String mensaje) {
        statusLabel.setStyle(ESTILO_ERROR);
        statusLabel.setText(mensaje);
    }
}
