/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.weatherindicator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Display.CurrentConditionDisplay;
import Display.Display;
import Display.ForeCastDisplay;
import Display.HeatIndexDisplay;
import Display.StatisticsDisplay;
import Display.ThirdPartyDisplay;
import Observer.Observer;
import Subject.WeatherData;

/**
 *
 * @author Estudiantes
 */
public class WeatherIndicator {

    private static final WeatherData weatherData = new WeatherData();
    // Catálogo de todas las vistas disponibles en el sistema (nombre -> instancia)
    private static final Map<String, Observer> vistasDisponibles = new LinkedHashMap<>();
    // Nombres de las vistas actualmente registradas (activas) en el WeatherData
    private static final List<String> vistasActivas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarVistas();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    listarEstadoVistas();
                    break;
                case 2:
                    agregarVista();
                    break;
                case 3:
                    quitarVista();
                    break;
                case 4:
                    ingresarMediciones();
                    break;
                case 5:
                    mostrarVistasActivas();
                    break;
                case 0:
                    System.out.println("Cerrando el sistema de monitoreo. ¡Hasta luego!");
                    break;
                default:
                    System.out.println(">> Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Crea las vistas base del sistema. Todavía NO quedan registradas en
     * WeatherData; el usuario decide desde el menú cuáles activar.
     */
    private static void inicializarVistas() {
        vistasDisponibles.put("Condiciones Actuales", new CurrentConditionDisplay());
        vistasDisponibles.put("Pronostico", new ForeCastDisplay());
        vistasDisponibles.put("Indice de Calor", new HeatIndexDisplay());
        vistasDisponibles.put("Estadisticas", new StatisticsDisplay());
        vistasDisponibles.put("Terceros", new ThirdPartyDisplay());
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENU - MONITOREO DE ESTACION METEOROLOGICA =====");
        System.out.println("1. Ver estado de todas las vistas (activas/inactivas)");
        System.out.println("2. Agregar (activar) una vista");
        System.out.println("3. Quitar (desactivar) una vista");
        System.out.println("4. Ingresar nuevas mediciones (temperatura, humedad, presion)");
        System.out.println("5. Mostrar el contenido de las vistas activas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listarEstadoVistas() {
        System.out.println("\n--- Estado de las vistas ---");
        int i = 1;
        for (String nombre : vistasDisponibles.keySet()) {
            String estado = vistasActivas.contains(nombre) ? "ACTIVA" : "inactiva";
            System.out.println(i + ". " + nombre + " [" + estado + "]");
            i++;
        }
    }

    private static void agregarVista() {
        listarEstadoVistas();
        System.out.print("Escriba el nombre exacto de la vista a agregar: ");
        String nombre = scanner.nextLine().trim();

        Observer obs = vistasDisponibles.get(nombre);
        if (obs == null) {
            System.out.println(">> Esa vista no existe en el catalogo.");
            return;
        }
        if (vistasActivas.contains(nombre)) {
            System.out.println(">> Esa vista ya se encuentra activa.");
            return;
        }

        weatherData.registerObserver(obs);
        vistasActivas.add(nombre);
        System.out.println(">> Vista '" + nombre + "' agregada y suscrita a las notificaciones.");
    }

    private static void quitarVista() {
        listarEstadoVistas();
        System.out.print("Escriba el nombre exacto de la vista a quitar: ");
        String nombre = scanner.nextLine().trim();

        Observer obs = vistasDisponibles.get(nombre);
        if (obs == null) {
            System.out.println(">> Esa vista no existe en el catalogo.");
            return;
        }
        if (!vistasActivas.contains(nombre)) {
            System.out.println(">> Esa vista no esta activa actualmente.");
            return;
        }

        weatherData.removeObserver(obs);
        vistasActivas.remove(nombre);
        System.out.println(">> Vista '" + nombre + "' removida correctamente.");
    }

    private static void ingresarMediciones() {
        try {
            System.out.print("Ingrese temperatura: ");
            int temperatura = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ingrese humedad: ");
            int humedad = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ingrese presion: ");
            int presion = Integer.parseInt(scanner.nextLine().trim());

            weatherData.changeMeasurements(temperatura, humedad, presion);
            System.out.println(">> Mediciones actualizadas y notificadas a las vistas activas.");
        } catch (NumberFormatException e) {
            System.out.println(">> Entrada invalida. Debe ingresar solo numeros enteros.");
        }
    }

    private static void mostrarVistasActivas() {
        if (vistasActivas.isEmpty()) {
            System.out.println(">> No hay vistas activas actualmente. Use la opcion 2 para agregar una.");
            return;
        }

        System.out.println("\n--- Contenido de las vistas activas ---");
        for (String nombre : vistasActivas) {
            Observer obs = vistasDisponibles.get(nombre);
            if (obs instanceof Display) {
                System.out.println("[" + nombre + "]");
                ((Display) obs).display();
            }
        }
    }
}