package com.harry_potter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.harry_potter.Hechizos.ExpectoPatronum;
import com.harry_potter.Hechizos.Expelliarmus;
import com.harry_potter.Hechizos.Hechizo;
import com.harry_potter.Hechizos.OculusReparo;
import com.harry_potter.Hechizos.WingardiumLeviosa;
import com.harry_potter.Personaje.HarryPotter;
import com.harry_potter.Personaje.HermioneGranger;
import com.harry_potter.Personaje.Personaje;
import com.harry_potter.Personaje.RonWeasley;

/**
 * Menú interactivo por consola, estilo videojuego, para probar el sistema
 * de personajes y hechizos.
 *
 * Permite:
 *  - Elegir un personaje (Harry, Ron o Hermione).
 *  - Lanzar su hechizo actual sobre un objetivo escrito por el usuario.
 *  - Cambiar su hechizo hábil en tiempo de ejecución (aprenderHechizo).
 *  - Ver el estado (hechizo actual) de todos los personajes.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static List<Personaje> personajes;
    private static List<Hechizo> hechizosDisponibles;

    public static void main(String[] args) {
        inicializarDatos();
        mostrarBienvenida();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion(0, 3);

            switch (opcion) {
                case 1 -> lanzarHechizoMenu();
                case 2 -> cambiarHechizoMenu();
                case 3 -> mostrarEstadoPersonajes();
                case 0 -> {
                    System.out.println("\n¡Mischief managed! Gracias por jugar.");
                    salir = true;
                }
            }
        }
        sc.close();
    }

    // ---------------------------------------------------------------
    // Inicialización
    // ---------------------------------------------------------------

    private static void inicializarDatos() {
        personajes = new ArrayList<>();
        personajes.add(new HarryPotter());
        personajes.add(new RonWeasley());
        personajes.add(new HermioneGranger());

        hechizosDisponibles = new ArrayList<>();
        hechizosDisponibles.add(new WingardiumLeviosa());
        hechizosDisponibles.add(new OculusReparo());
        hechizosDisponibles.add(new Expelliarmus());
        hechizosDisponibles.add(new ExpectoPatronum());
    }

    private static void mostrarBienvenida() {
        System.out.println("=================================================");
        System.out.println("   HOGWARTS: DUELO DE MAGOS - Menú de pruebas");
        System.out.println("=================================================");
    }

    // ---------------------------------------------------------------
    // Menú principal
    // ---------------------------------------------------------------

    private static void mostrarMenuPrincipal() {
        System.out.println("\n----------------- MENÚ PRINCIPAL -----------------");
        System.out.println("1. Lanzar un hechizo con un personaje");
        System.out.println("2. Enseñar un nuevo hechizo a un personaje (cambio en tiempo de ejecución)");
        System.out.println("3. Ver estado actual de los personajes");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    // ---------------------------------------------------------------
    // Opción 1: lanzar hechizo
    // ---------------------------------------------------------------

    private static void lanzarHechizoMenu() {
        Personaje p = elegirPersonaje();
        if (p == null) return;

        System.out.print("¿Sobre qué objetivo lanza el hechizo? ");
        String objetivo = sc.nextLine();

        System.out.println();
        p.lanzarHechizo(objetivo);
    }

    // ---------------------------------------------------------------
    // Opción 2: cambiar hechizo (demuestra el cambio en tiempo de ejecución)
    // ---------------------------------------------------------------

    private static void cambiarHechizoMenu() {
        Personaje p = elegirPersonaje();
        if (p == null) return;

        System.out.println("\nHechizo actual de " + p.getNombre() + ": " + p.getHechizoActual().getNombre());
        Hechizo nuevo = elegirHechizo();
        if (nuevo == null) return;

        System.out.println();
        p.aprenderHechizo(nuevo);
    }

    // ---------------------------------------------------------------
    // Opción 3: ver estado
    // ---------------------------------------------------------------

    private static void mostrarEstadoPersonajes() {
        System.out.println("\n--------------- ESTADO ACTUAL ---------------");
        for (Personaje p : personajes) {
            System.out.println("- " + p.getNombre() + " -> hechizo hábil: " + p.getHechizoActual().getNombre());
        }
    }

    // ---------------------------------------------------------------
    // Submenús auxiliares
    // ---------------------------------------------------------------

    private static Personaje elegirPersonaje() {
        System.out.println("\nElige un personaje:");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + ". " + personajes.get(i).getNombre());
        }
        System.out.println("0. Cancelar");
        System.out.print("Opción: ");

        int opcion = leerOpcion(0, personajes.size());
        if (opcion == 0) return null;
        return personajes.get(opcion - 1);
    }

    private static Hechizo elegirHechizo() {
        System.out.println("\nElige el nuevo hechizo:");
        for (int i = 0; i < hechizosDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + hechizosDisponibles.get(i).getNombre());
        }
        System.out.println("0. Cancelar");
        System.out.print("Opción: ");

        int opcion = leerOpcion(0, hechizosDisponibles.size());
        if (opcion == 0) return null;
        return hechizosDisponibles.get(opcion - 1);
    }

    // ---------------------------------------------------------------
    // Utilidad de lectura robusta de opciones numéricas
    // ---------------------------------------------------------------

    private static int leerOpcion(int min, int max) {
        while (true) {
            String linea = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException ignored) {
                // se vuelve a pedir
            }
            System.out.print("Opción inválida, intenta de nuevo (" + min + "-" + max + "): ");
        }
    }
}