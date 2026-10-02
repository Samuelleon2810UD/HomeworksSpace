package com.memento_text;

import com.memento_text.caretaker.Historial;
import com.memento_text.originator.Editor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de edición de texto ===");

        // instanciamos el editor y lo asignamos al historial
        Editor editor = new Editor();
        Historial historial = new Historial(editor);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Escribir texto\n2. Deshacer\n3. Mostrar contenido\n4. Salir");
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String command = scanner.nextLine().trim();
            switch (command) {
                case "1":
                    System.out.print("Ingrese el nuevo contenido: ");
                    if (!scanner.hasNextLine()) {
                        return;
                    }
                    String text = scanner.nextLine();
                    historial.hitSave();
                    editor.setContent(text);
                    System.out.println("Texto actualizado.");
                    break;
                case "2":
                    if (historial.hitUndo()) {
                        System.out.println("Se restauró el contenido anterior.");
                    } else {
                        System.out.println("No hay cambios para deshacer.");
                    }
                    break;

                case "3":
                    System.out.println("Contenido actual: " + editor.getContent());
                    break;
                case "4":
                    System.out.println("Saliendo del editor.");
                    return;
                default:
                    System.out.println("Comando no reconocido.");
            }
        }
        scanner.close();
    }
}