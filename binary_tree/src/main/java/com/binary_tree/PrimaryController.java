package com.binary_tree;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.binary_tree.Arbol.Arbol;
import com.binary_tree.Arbol.Arbol.ParSubarboles;
import com.binary_tree.Arbol.Arbol.TipoComparacion;
import com.binary_tree.Nodo.Nodo;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class PrimaryController {
    @FXML private Label listaLabel;
    @FXML private Label mensajeLabel;
    @FXML private Button construirButton;
    @FXML private Button vaciarButton;
    @FXML private Button analizarButton;
    @FXML private Button consultarGradoButton;
    @FXML private Pane arbolPane;
    @FXML private Pane primerSubarbolPane;
    @FXML private Pane segundoSubarbolPane;
    @FXML private Label ramasLabel;
    @FXML private Label hojasLabel;
    @FXML private Label gradoArbolLabel;
    @FXML private Label longitudLabel;
    @FXML private Label pesoArbolLabel;
    @FXML private Label preordenLabel;
    @FXML private Label inordenLabel;
    @FXML private Label postordenLabel;
    @FXML private Label hojaLejanaLabel;
    @FXML private Label caminoLabel;
    @FXML private Label gradoResultadoLabel;
    @FXML private Label cantidadSubarbolesLabel;
    @FXML private Label igualesCantidadLabel;
    @FXML private Label equivalentesCantidadLabel;
    @FXML private Label isomorfosCantidadLabel;
    @FXML private TextField numeroField;
    @FXML private ComboBox<TipoComparacion> comparacionCombo;
    @FXML private ListView<ParSubarboles> paresList;

    private final Arbol arbol = new Arbol();

    @FXML
    private void initialize() {
        construirButton.setDisable(true);
        vaciarButton.setDisable(true);
        analizarButton.setDisable(true);
        consultarGradoButton.setDisable(true);
        comparacionCombo.setConverter(new StringConverter<TipoComparacion>() {
            @Override
            public String toString(TipoComparacion tipo) {
                return nombreComparacion(tipo);
            }

            @Override
            public TipoComparacion fromString(String texto) {
                return null;
            }
        });
        comparacionCombo.getItems().setAll(TipoComparacion.values());
        comparacionCombo.getSelectionModel().select(TipoComparacion.IGUALES);
        comparacionCombo.valueProperty().addListener((observable, anterior, actual) -> actualizarPares());
        paresList.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> dibujarPar(seleccionado));
    }

    @FXML
    private void generarLista() {
        arbol.generarLista();
        listaLabel.setText(arbol.imprimirLista());
        mensajeLabel.setText("Se añadieron elementos a la lista. Construye el árbol para analizarlo.");
        limpiarVisualizaciones();
        configurarAnalisis(false);
        construirButton.setDisable(false);
        vaciarButton.setDisable(false);
    }

    @FXML
    private void vaciarLista() {
        arbol.vaciarLista();
        listaLabel.setText(arbol.imprimirLista());
        mensajeLabel.setText("La lista y el árbol se vaciaron.");
        limpiarVisualizaciones();
        configurarAnalisis(false);
        construirButton.setDisable(true);
        vaciarButton.setDisable(true);
    }

    @FXML
    private void construirArbol() {
        Nodo raiz = arbol.createTree();
        limpiarVisualizaciones();
        if (raiz == null) {
            mensajeLabel.setText("La lista está vacía; genera una lista antes de construir el árbol.");
            configurarAnalisis(false);
            return;
        }

        dibujarArbol(raiz, arbolPane, Color.LIGHTBLUE);
        configurarAnalisis(true);
        mensajeLabel.setText("Árbol construido. Ya puedes consultar sus características.");
    }

    @FXML
    private void analizarArbol() {
        if (arbol.getRaiz() == null) {
            return;
        }
        ramasLabel.setText(formatearValores(arbol.getNodosRama()));
        hojasLabel.setText(formatearValores(arbol.getNodosHoja()));
        gradoArbolLabel.setText(Integer.toString(arbol.gradoArbol()));
        longitudLabel.setText(arbol.longitudArbol() + " nodos en el camino más largo");
        pesoArbolLabel.setText(Integer.toString(arbol.pesoArbol()) + " nodos");
        preordenLabel.setText(formatearValores(arbol.getPreorden()));
        inordenLabel.setText(formatearValores(arbol.getInorden()));
        postordenLabel.setText(formatearValores(arbol.getPostorden()));
        List<Integer> caminoMasLejano = arbol.getCaminoHojaMasLejana();
        hojaLejanaLabel.setText(caminoMasLejano.isEmpty()
                ? "Ninguna" : Integer.toString(caminoMasLejano.get(caminoMasLejano.size() - 1)));
        caminoLabel.setText(formatearValores(caminoMasLejano));
        igualesCantidadLabel.setText(indicadorCantidad(TipoComparacion.IGUALES));
        equivalentesCantidadLabel.setText(indicadorCantidad(TipoComparacion.EQUIVALENTES));
        isomorfosCantidadLabel.setText(indicadorCantidad(TipoComparacion.ISOMORFOS));
        actualizarPares();
    }

    @FXML
    private void consultarGrado() {
        String texto = numeroField.getText().trim();
        if (texto.isEmpty()) {
            gradoResultadoLabel.setText("Escribe un valor para buscar.");
            return;
        }
        try {
            int numero = Integer.parseInt(texto);
            int grado = arbol.gradoNodo(numero);
            if (grado < 0) {
                gradoResultadoLabel.setText("El valor " + numero + " no está en el árbol.");
                return;
            }
            gradoResultadoLabel.setText(
                    "Camino: " + formatearValores(arbol.getCaminoNodo(numero))
                    + "\nGrado: " + grado
                    + "\nLongitud desde la raíz: " + arbol.longitudNodo(numero) + " nodos"
                    + "\nAltura (nivel desde la raíz, raíz = 0): " + arbol.alturaNodo(numero));
        } catch (NumberFormatException excepcion) {
            gradoResultadoLabel.setText("Ingresa un número entero válido.");
        }
    }

    private void configurarAnalisis(boolean habilitado) {
        analizarButton.setDisable(!habilitado);
        consultarGradoButton.setDisable(!habilitado);
        numeroField.setDisable(!habilitado);
        comparacionCombo.setDisable(!habilitado);
        paresList.setDisable(!habilitado);
    }

    private void limpiarVisualizaciones() {
        arbolPane.getChildren().clear();
        primerSubarbolPane.getChildren().clear();
        segundoSubarbolPane.getChildren().clear();
        ramasLabel.setText("—");
        hojasLabel.setText("—");
        gradoArbolLabel.setText("—");
        longitudLabel.setText("—");
        pesoArbolLabel.setText("—");
        preordenLabel.setText("—");
        inordenLabel.setText("—");
        postordenLabel.setText("—");
        hojaLejanaLabel.setText("—");
        caminoLabel.setText("—");
        gradoResultadoLabel.setText("");
        cantidadSubarbolesLabel.setText("Analiza el árbol para ver las parejas.");
        igualesCantidadLabel.setText("Sin analizar");
        equivalentesCantidadLabel.setText("Sin analizar");
        isomorfosCantidadLabel.setText("Sin analizar");
        paresList.getItems().clear();
    }

    private void actualizarPares() {
        TipoComparacion tipo = comparacionCombo.getValue();
        if (tipo == null || arbol.getRaiz() == null) {
            return;
        }
        List<ParSubarboles> pares = arbol.getSubarbolesComparables(tipo);
        paresList.getItems().setAll(pares);
        cantidadSubarbolesLabel.setText(pares.isEmpty()
                ? "No se encontraron parejas " + nombreComparacion(tipo).toLowerCase() + "."
                : "Parejas " + nombreComparacion(tipo).toLowerCase() + ": " + pares.size()
                        + ". Selecciona una para dibujarla.");
        if (!pares.isEmpty()) {
            paresList.getSelectionModel().selectFirst();
        } else {
            dibujarPar(null);
        }
    }

    private void dibujarPar(ParSubarboles par) {
        primerSubarbolPane.getChildren().clear();
        segundoSubarbolPane.getChildren().clear();
        if (par != null) {
            dibujarArbol(par.getPrimero(), primerSubarbolPane, Color.LIGHTGREEN);
            dibujarArbol(par.getSegundo(), segundoSubarbolPane, Color.LIGHTSALMON);
        }
    }

    private void dibujarArbol(Nodo raiz, Pane panel, Color colorNodo) {
        panel.getChildren().clear();
        int cantidad = contarNodos(raiz);
        int profundidad = profundidadMaxima(raiz);
        double ancho = Math.max(320, cantidad * 58 + 48);
        double alto = Math.max(180, (profundidad + 1) * 76 + 35);
        panel.setMinSize(0, 0);
        panel.setPrefSize(ancho, alto);

        Map<Nodo, Point2D> posiciones = new IdentityHashMap<>();
        asignarPosiciones(raiz, 0, new int[] {0}, (ancho - 24) / (cantidad + 1), posiciones);
        dibujarConexiones(raiz, posiciones, panel);
        dibujarNodos(raiz, posiciones, panel, colorNodo);
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
        double x = 12 + separacion * (++indice[0]);
        double y = 28 + profundidad * 76;
        posiciones.put(nodo, new Point2D(x, y));
        asignarPosiciones(nodo.right, profundidad + 1, indice, separacion, posiciones);
    }

    private void dibujarConexiones(Nodo nodo, Map<Nodo, Point2D> posiciones, Pane panel) {
        if (nodo == null) {
            return;
        }
        Point2D origen = posiciones.get(nodo);
        agregarConexion(nodo.left, origen, posiciones, panel);
        agregarConexion(nodo.right, origen, posiciones, panel);
        dibujarConexiones(nodo.left, posiciones, panel);
        dibujarConexiones(nodo.right, posiciones, panel);
    }

    private void agregarConexion(Nodo hijo, Point2D origen, Map<Nodo, Point2D> posiciones, Pane panel) {
        if (hijo != null) {
            Point2D destino = posiciones.get(hijo);
            panel.getChildren().add(new Line(origen.getX(), origen.getY(), destino.getX(), destino.getY()));
        }
    }

    private void dibujarNodos(
            Nodo nodo, Map<Nodo, Point2D> posiciones, Pane panel, Color colorNodo) {
        if (nodo == null) {
            return;
        }
        Point2D posicion = posiciones.get(nodo);
        Circle circulo = new Circle(posicion.getX(), posicion.getY(), 19, colorNodo);
        circulo.setStroke(Color.DARKSLATEBLUE);
        Text valor = new Text(Integer.toString(nodo.num));
        valor.setX(posicion.getX() - valor.getLayoutBounds().getWidth() / 2);
        valor.setY(posicion.getY() + valor.getLayoutBounds().getHeight() / 4);
        panel.getChildren().addAll(circulo, valor);
        dibujarNodos(nodo.left, posiciones, panel, colorNodo);
        dibujarNodos(nodo.right, posiciones, panel, colorNodo);
    }

    private String formatearValores(List<Integer> valores) {
        return valores.isEmpty() ? "Ninguno" : valores.toString();
    }

    private String indicadorCantidad(TipoComparacion tipo) {
        int cantidad = arbol.contarSubarbolesComparables(tipo);
        return cantidad == 0 ? "No: 0 parejas" : "Sí: " + cantidad + " parejas";
    }

    private String nombreComparacion(TipoComparacion tipo) {
        if (tipo == null) {
            return "";
        }
        switch (tipo) {
            case IGUALES:
                return "Iguales";
            case EQUIVALENTES:
                return "Equivalentes";
            case ISOMORFOS:
                return "Isomorfos";
            default:
                throw new IllegalArgumentException("Tipo de comparación no válido: " + tipo);
        }
    }
}
