package com.dinamic_list.Controllers;

import com.dinamic_list.Lists.Listas;
import com.dinamic_list.Lists.ListasCircular;
import com.dinamic_list.Lists.ListasDoble;
import com.dinamic_list.Nodes.Nodo;
import com.dinamic_list.Nodes.NodoDouble;

import javafx.fxml.FXML;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class ShowlistsController {
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";
    private static final String CIRCULAR = "Circular";
    private static final int MAX_ELEMENTOS = 1000;
    private static final String ESTILO_OK = "-fx-text-fill: #176b58; -fx-font-weight: bold;";
    private static final String ESTILO_ERROR = "-fx-text-fill: #a63d2a; -fx-font-weight: bold;";
    private static final ButtonType CONFIRMAR_NODO = new ButtonType("Sí, este nodo");
    private static final ButtonType CONTINUAR_BUSQUEDA = new ButtonType("No, continuar");
    private static final Duration PAUSA_BUSQUEDA = Duration.millis(350);

    private final Listas controladorListas = new Listas();
    private final ListasCircular controladorListasCircular = new ListasCircular();
    private final ListasDoble controladorListasDoble = new ListasDoble();

    @FXML
    private ComboBox<String> listType;
    @FXML
    private TextField cantidadField;
    @FXML
    private HBox cantidadBox;
    @FXML
    private HBox circularBox;
    @FXML
    private TextField cantidadCircularField;
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
    private HBox insertPositionBox;
    @FXML
    private HBox removePositionBox;
    @FXML
    private HBox capturePositionBox;
    @FXML
    private VBox circularOperationsBox;
    @FXML
    private Label insertPositionTitle;
    @FXML
    private Label removePositionTitle;
    @FXML
    private Label capturePositionTitle;
    @FXML
    private TextField posQuitarField;
    @FXML
    private TextField posCapturarField;
    @FXML
    private TextField valorReferenciaField;
    @FXML
    private TextField nuevoValorCircularField;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox listGraphic;

    private boolean busquedaEnCurso;

    @FXML
    private void initialize() {
        listType.getItems().addAll(SIMPLE, DOBLE, CIRCULAR);
        listType.setValue(SIMPLE);
        mostrarControlesDoble(false);
        mostrarControlCantidad(true);
        mostrarControlesCirculares(false);
        statusLabel.setText("Elige un tipo de lista y genera una.");
        actualizarVista();
    }

    private boolean esDoble() {
        return DOBLE.equals(listType.getValue());
    }

    private boolean esCircular() {
        return CIRCULAR.equals(listType.getValue());
    }

    private void mostrarControlesDoble(boolean visible) {
        ladoBox.setVisible(visible);
        ladoBox.setManaged(visible);
        extremosBox.setVisible(visible);
        extremosBox.setManaged(visible);
    }

    private void mostrarControlCantidad(boolean visible) {
        cantidadBox.setVisible(visible);
        cantidadBox.setManaged(visible);
    }

    @FXML
    private void changeListType() {
        boolean doble = esDoble();
        mostrarControlesDoble(doble);
        mostrarControlCantidad(!esCircular());
        mostrarControlesCirculares(esCircular());
        exito("Lista " + nombreTipoLista() + " seleccionada.");
    }

    private void mostrarControlesCirculares(boolean visibles) {
        circularBox.setVisible(visibles);
        circularBox.setManaged(visibles);
        circularOperationsBox.setVisible(visibles);
        circularOperationsBox.setManaged(visibles);
        insertPositionBox.setVisible(!visibles);
        insertPositionBox.setManaged(!visibles);
        removePositionBox.setVisible(!visibles);
        removePositionBox.setManaged(!visibles);
        capturePositionBox.setVisible(!visibles);
        capturePositionBox.setManaged(!visibles);
        insertPositionTitle.setVisible(!visibles);
        insertPositionTitle.setManaged(!visibles);
        removePositionTitle.setVisible(!visibles);
        removePositionTitle.setManaged(!visibles);
        capturePositionTitle.setVisible(!visibles);
        capturePositionTitle.setManaged(!visibles);
    }

    @FXML
    private void generateList() {
        if (esCircular()) {
            controladorListasCircular.generarLista();
            exito("Lista circular generada con " + tamano() + " elementos aleatorios.");
            return;
        }
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
    private void añadirNElementosListaCricular(){
        Integer cantidad = leerEntero(cantidadCircularField, "la cantidad de elementos que quieres añadir");
        if (cantidad == null) {
            return;
        }
        if (cantidad < 1 || cantidad > MAX_ELEMENTOS) {
            error("La cantidad a añadir debe estar entre 1 y " + MAX_ELEMENTOS + ".");
            return;
        }
        controladorListasCircular.añadirNElementos(cantidad);
        exito("Se añadieron " + cantidad + " nodos aleatorios. La lista tiene "
                + controladorListasCircular.tamano() + " elementos.");
    }

    @FXML
    private void generarListaCircularN() {
        Integer cantidad = leerEntero(cantidadCircularField, "la cantidad de elementos");
        if (cantidad == null) {
            return;
        }
        if (cantidad < 1 || cantidad > MAX_ELEMENTOS) {
            error("La cantidad debe estar entre 1 y " + MAX_ELEMENTOS + ".");
            return;
        }
        controladorListasCircular.generarLista(cantidad);
        exito("Lista circular generada con " + cantidad + " nodos aleatorios.");
    }

    @FXML
    private void generarPrimerElementoListaCircular(){
        if (esCircular()) {
            controladorListasCircular.instanciarPrimerElemento();
            exito("Se creó el primer nodo aleatorio de la lista circular.");
            return;
        }
    }

    @FXML
    private void printLista() {
        exito("Representación visual de la lista (" + tamano() + " nodos).");
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
        boolean insertado = esDoble() ? controladorListasDoble.insertarNodo(posicion, valor)
            : esCircular() ? controladorListasCircular.insertarNodo(posicion, valor)
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
        boolean quitado = esDoble() ? controladorListasDoble.quitarNodo(posicion)
            : esCircular() ? controladorListasCircular.quitarNodo(posicion)
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
        } else if (esCircular()) {
            Nodo nodo = controladorListasCircular.capturar(posicion);
            if (nodo == null) {
                error("No existe un nodo en la posición " + posicion + ".");
                return;
            }
            info = "num = " + nodo.num + ", siguiente = " + valorDe(nodo.sig);
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

    @FXML
    private void capturarCircularPorValor() {
        Integer valor = leerEntero(valorReferenciaField, "el valor num que quieres buscar");
        if (valor == null) {
            return;
        }
        buscarCircularConConfirmacion(valor, posicion -> {
            Nodo nodo = controladorListasCircular.capturarPorValor(valor, ocurrenciaDelValor(valor, posicion));
            if (nodo == null) {
                error("No se encontró un nodo con num = " + valor + ".");
                return;
            }
            exito("Nodo confirmado en la posición " + posicion + ": num = " + nodo.num
                    + ", siguiente num = " + valorDe(nodo.sig) + ".");
        });
    }

    @FXML
    private void quitarCircularPorValor() {
        Integer valor = leerEntero(valorReferenciaField, "el valor num que quieres quitar");
        if (valor == null) {
            return;
        }
        buscarCircularConConfirmacion(valor, posicion -> {
            int ocurrencia = ocurrenciaDelValor(valor, posicion);
            if (controladorListasCircular.quitarNodoPorValor(valor, ocurrencia)) {
                exito("Se quitó la ocurrencia confirmada de num = " + valor + ".");
            } else {
                error("No se pudo quitar el nodo num = " + valor + ".");
            }
        });
    }

    @FXML
    private void insertarCircularPorValor() {
        Integer valorReferencia = leerEntero(valorReferenciaField, "el valor num de referencia");
        if (valorReferencia == null) {
            return;
        }
        Integer nuevoValor = leerEntero(nuevoValorCircularField, "el valor del nuevo nodo");
        if (nuevoValor == null) {
            return;
        }
        buscarCircularConConfirmacion(valorReferencia, posicion -> {
            int ocurrencia = ocurrenciaDelValor(valorReferencia, posicion);
            if (controladorListasCircular.insertarNodoDespuesDeValor(valorReferencia, ocurrencia, nuevoValor)) {
                exito("Se insertó num = " + nuevoValor + " después del nodo confirmado num = "
                        + valorReferencia + ".");
            } else {
                error("No se pudo insertar: no existe la referencia num = " + valorReferencia + ".");
            }
        });
    }

    private void buscarCircularConConfirmacion(int valorBuscado, java.util.function.IntConsumer alConfirmar) {
        if (busquedaEnCurso) {
            error("Ya hay una búsqueda secuencial en curso.");
            return;
        }
        int total = controladorListasCircular.tamano();
        if (total == 0) {
            error("La lista circular está vacía.");
            return;
        }
        busquedaEnCurso = true;
        listType.setDisable(true);
        circularBox.setDisable(true);
        circularOperationsBox.setDisable(true);
        avanzarBusquedaCircular(valorBuscado, 1, total, alConfirmar);
    }

    private void avanzarBusquedaCircular(int valorBuscado, int posicion, int total,
            java.util.function.IntConsumer alConfirmar) {
        if (posicion > total) {
            terminarBusqueda();
            actualizarVista();
            error("No se encontró un nodo con num = " + valorBuscado + ".");
            return;
        }
        Nodo candidato = controladorListasCircular.capturar(posicion);
        actualizarVista(posicion);
        statusLabel.setStyle(ESTILO_OK);
        statusLabel.setText("Búsqueda secuencial: revisando posición " + posicion + " de " + total
                + " (num = " + candidato.num + ").");

        PauseTransition pausa = new PauseTransition(PAUSA_BUSQUEDA);
        pausa.setOnFinished(event -> {
            if (candidato.num != valorBuscado) {
                avanzarBusquedaCircular(valorBuscado, posicion + 1, total, alConfirmar);
                return;
            }
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Búsqueda secuencial");
            confirmacion.setHeaderText("Coincidencia encontrada en el nodo " + posicion + " de " + total);
            confirmacion.setContentText("El nodo tiene num = " + candidato.num
                    + ". ¿Quieres seleccionar esta coincidencia o seguir buscando?");
            confirmacion.getButtonTypes().setAll(CONFIRMAR_NODO, CONTINUAR_BUSQUEDA);
            confirmacion.setOnHidden(dialogEvent -> {
                ButtonType respuesta = confirmacion.getResult();
                if (respuesta == CONFIRMAR_NODO) {
                    terminarBusqueda();
                    alConfirmar.accept(posicion);
                } else if (respuesta == CONTINUAR_BUSQUEDA) {
                    avanzarBusquedaCircular(valorBuscado, posicion + 1, total, alConfirmar);
                } else {
                    terminarBusqueda();
                    actualizarVista();
                }
            });
            confirmacion.show();
        });
        pausa.play();
    }

    private void terminarBusqueda() {
        busquedaEnCurso = false;
        listType.setDisable(false);
        circularBox.setDisable(false);
        circularOperationsBox.setDisable(false);
    }

    private int ocurrenciaDelValor(int valor, int posicion) {
        int ocurrencia = 0;
        for (int i = 1; i <= posicion; i++) {
            if (controladorListasCircular.capturar(i).num == valor) {
                ocurrencia++;
            }
        }
        return ocurrencia;
    }

    private int tamano() {
        return esDoble() ? controladorListasDoble.tamano()
            : esCircular() ? controladorListasCircular.tamano() : controladorListas.tamano();
    }

    private String nombreTipoLista() {
        return esDoble() ? "doble" : esCircular() ? "circular" : "simple";
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
        actualizarVista();
    }

    private void actualizarVista() {
        actualizarVista(0);
    }

    private void actualizarVista(int posicionActiva) {
        int total = tamano();
        listGraphic.getChildren().clear();
        if (total == 0) {
            Label vacia = new Label("La lista está vacía. Genera nodos para ver la estructura.");
            vacia.setStyle("-fx-text-fill: #60736d; -fx-font-size: 14px;");
            listGraphic.getChildren().add(vacia);
            return;
        }

        Label encabezado = new Label(esCircular()
                ? "p apunta al primer nodo  •  cab señala el último nodo"
                : "HEAD apunta al primer nodo");
        encabezado.setStyle("-fx-text-fill: #183b35; -fx-font-weight: bold; -fx-font-size: 13px;");

        HBox cadena = new HBox(8.0);
        cadena.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        Label inicio = new Label(esCircular() ? "p" : "HEAD");
        inicio.setStyle("-fx-background-color: #176b58; -fx-text-fill: white; -fx-font-weight: bold;"
                + "-fx-padding: 8px 12px; -fx-background-radius: 6px;");
        cadena.getChildren().add(inicio);
        agregarFlecha(cadena, "→");

        for (int posicion = 1; posicion <= total; posicion++) {
            Nodo nodoSimple = null;
            NodoDouble nodoDoble = null;
            if (esDoble()) {
                nodoDoble = controladorListasDoble.capturar(posicion);
            } else if (esCircular()) {
                nodoSimple = controladorListasCircular.capturar(posicion);
            } else {
                nodoSimple = controladorListas.capturar(posicion);
            }

            int numero = esDoble() ? nodoDoble.num : nodoSimple.num;
            String siguiente = esCircular()
                    ? (posicion == total ? "p" : String.valueOf(controladorListasCircular.capturar(posicion + 1).num))
                    : esDoble()
                        ? (nodoDoble.sig == null ? "NULL" : String.valueOf(nodoDoble.sig.num))
                        : (nodoSimple.sig == null ? "NULL" : String.valueOf(nodoSimple.sig.num));
            String anterior = esDoble()
                    ? (nodoDoble.ant == null ? "NULL" : String.valueOf(nodoDoble.ant.num))
                    : null;

            VBox tarjeta = new VBox(4.0);
            tarjeta.setAlignment(javafx.geometry.Pos.CENTER);
            tarjeta.setMinWidth(105.0);
            tarjeta.setStyle(posicion == posicionActiva
                    ? "-fx-background-color: #fff1bd; -fx-border-color: #e2a900; -fx-border-width: 2px;"
                        + "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 10px;"
                    : "-fx-background-color: #eaf3ef; -fx-border-color: #9bb9aa;"
                        + "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 10px;");
            Label indice = new Label((posicion == posicionActiva ? "▶ " : "") + "Nodo " + posicion
                    + (esCircular() && posicion == total ? " · cab" : ""));
            indice.setStyle("-fx-text-fill: #60736d; -fx-font-size: 11px; -fx-font-weight: bold;");
            Label valor = new Label("num = " + numero);
            valor.setStyle("-fx-text-fill: #183b35; -fx-font-size: 15px; -fx-font-weight: bold;");
            Label enlace = new Label("sig → " + siguiente);
            enlace.setStyle("-fx-text-fill: #176b58; -fx-font-size: 11px;");
            tarjeta.getChildren().addAll(indice, valor);
            if (nodoDoble != null) {
                Label enlaceAnterior = new Label("ant ← " + anterior);
                enlaceAnterior.setStyle("-fx-text-fill: #60736d; -fx-font-size: 11px;");
                tarjeta.getChildren().add(enlaceAnterior);
            }
            tarjeta.getChildren().add(enlace);
            cadena.getChildren().add(tarjeta);
            if (posicion < total || !esCircular()) {
                agregarFlecha(cadena, "→");
            }
        }
        if (!esCircular()) {
            Label fin = new Label("NULL");
            fin.setStyle("-fx-background-color: #f4dfd9; -fx-text-fill: #813d2d; -fx-font-weight: bold;"
                    + "-fx-padding: 8px 10px; -fx-background-radius: 6px;");
            cadena.getChildren().add(fin);
        }

        ArrayList<javafx.scene.Node> contenido = new ArrayList<>();
        contenido.add(encabezado);
        contenido.add(cadena);
        if (esCircular()) {
            Label retorno = new Label("↶  cab.sig regresa a p (la lista se cierra en círculo)");
            retorno.setStyle("-fx-text-fill: #176b58; -fx-font-weight: bold; -fx-padding: 4px 0 0 12px;");
            contenido.add(retorno);
        } else if (esDoble()) {
            Label extremos = new Label("NULL ← ant del primer nodo     •     sig del último nodo → NULL");
            extremos.setStyle("-fx-text-fill: #60736d; -fx-padding: 4px 0 0 12px;");
            contenido.add(extremos);
        }
        listGraphic.getChildren().setAll(contenido);
    }

    private void agregarFlecha(HBox cadena, String texto) {
        Label flecha = new Label(texto);
        flecha.setStyle("-fx-text-fill: #176b58; -fx-font-size: 20px; -fx-font-weight: bold;");
        cadena.getChildren().add(flecha);
    }

    private void error(String mensaje) {
        statusLabel.setStyle(ESTILO_ERROR);
        statusLabel.setText(mensaje);
    }
}
