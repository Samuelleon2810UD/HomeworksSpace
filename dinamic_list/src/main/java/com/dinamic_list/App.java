package com.dinamic_list;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    // Ventanas emergentes abiertas, para no abrir la misma dos veces
    private static final Map<String, Stage> ventanas = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle("Listas dinámicas");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /** Abre el FXML indicado en una ventana emergente (si ya está abierta, la trae al frente). */
    public static void abrirVentana(String fxml, String titulo) throws IOException {
        Stage ventana = ventanas.get(fxml);
        if (ventana != null && ventana.isShowing()) {
            ventana.toFront();
            return;
        }
        ventana = new Stage();
        ventana.setTitle(titulo);
        ventana.setScene(new Scene(loadFXML(fxml)));
        ventana.show();
        ventanas.put(fxml, ventana);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
