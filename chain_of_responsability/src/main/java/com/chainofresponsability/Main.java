package com.chainofresponsability;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import com.chainofresponsability.Handler.BaseHandler;
import com.chainofresponsability.Handler.CoordinadorHandler;
import com.chainofresponsability.Handler.DecanaturaHandler;
import com.chainofresponsability.Handler.Handler;
import com.chainofresponsability.Handler.MonitorHandler;
import com.chainofresponsability.Handler.PrincipalHandler;
import com.chainofresponsability.Handler.ProfesorHandler;
import com.chainofresponsability.Handler.SecretariaHandler;
import com.chainofresponsability.Handler.SubHandler;
import com.chainofresponsability.Problem.Problem;
import com.chainofresponsability.Problem.RegularProblem;

/**
 * Main
 * Punto de entrada por consola para demostrar de forma interactiva el
 * patrón Chain of Responsibility implementado en este proyecto.
 *
 * Cadena configurada (de menor a mayor autoridad):
 *   Monitor (nivel 1) -> Profesor (nivel 2) -> Coordinador (nivel 3)
 *   -> Secretaria (nivel 4) -> Decanatura (nivel 5, última instancia)
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static PrincipalHandler principal;

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        inicializarCadena();
        cargarProblemasDeEjemplo();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    agregarProblemaManual();
                    break;
                case "2":
                    verProblemasPendientes();
                    break;
                case "3":
                    procesarSiguientePendiente();
                    break;
                case "4":
                    principal.procesarTodos();
                    break;
                case "5":
                    verEstadoSubHandlers();
                    break;
                case "6":
                    cargarProblemasDeEjemplo();
                    System.out.println("Se recargaron los problemas de ejemplo.");
                    break;
                case "0":
                    salir = true;
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void inicializarCadena() {
        principal = new PrincipalHandler();

        SubHandler monitorPersona = new SubHandler("Juan (Monitor)", 1);
        SubHandler profesorPersona = new SubHandler("Prof. Ramírez", 2);
        SubHandler coordinadorPersona = new SubHandler("Coord. Torres", 3);
        SubHandler secretariaPersona = new SubHandler("Secretaria Gómez", 4);
        SubHandler decanaturaPersona = new SubHandler("Decano Pérez", 5);

        principal.agregarHandler(new MonitorHandler(monitorPersona));
        principal.agregarHandler(new ProfesorHandler(profesorPersona));
        principal.agregarHandler(new CoordinadorHandler(coordinadorPersona));
        principal.agregarHandler(new SecretariaHandler(secretariaPersona));
        principal.agregarHandler(new DecanaturaHandler(decanaturaPersona));
    }

    private static void cargarProblemasDeEjemplo() {
        principal.agregarProblema(new RegularProblem("Duda sobre un ejercicio de laboratorio", 1));
        principal.agregarProblema(new RegularProblem("Reclamo por nota de un parcial", 2));
        principal.agregarProblema(new RegularProblem("Cambio de grupo de clase", 3));
        principal.agregarProblema(new RegularProblem("Solicitud de certificado académico", 4));
        principal.agregarProblema(new RegularProblem("Apelación disciplinaria", 5));
    }

    private static void mostrarMenu() {
        System.out.println("\n===== CHAIN OF RESPONSIBILITY - Menu =====");
        System.out.println("1. Agregar un problema nuevo");
        System.out.println("2. Ver problemas pendientes/registrados");
        System.out.println("3. Procesar el siguiente problema no resuelto");
        System.out.println("4. Procesar TODOS los problemas");
        System.out.println("5. Ver estado de los SubHandlers (personas)");
        System.out.println("6. Recargar problemas de ejemplo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void agregarProblemaManual() {
        System.out.print("Descripción del problema: ");
        String descripcion = scanner.nextLine().trim();
        int nivel = leerEntero("Nivel requerido (1=Monitor, 2=Profesor, 3=Coordinador, 4=Secretaria, 5=Decanatura): ", 1, 5);
        principal.agregarProblema(new RegularProblem(descripcion, nivel));
        System.out.println("Problema agregado correctamente.");
    }

    private static int leerEntero(String mensaje, int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                valor = Integer.parseInt(entrada);
                if (valor < min || valor > max) {
                    System.out.println("Debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
        return valor;
    }

    private static void verProblemasPendientes() {
        List<Problem> problemas = principal.getProblemsList();
        if (problemas.isEmpty()) {
            System.out.println("No hay problemas registrados.");
            return;
        }
        System.out.println("\n-- Problemas registrados --");
        for (int i = 0; i < problemas.size(); i++) {
            System.out.println((i + 1) + ". " + problemas.get(i));
        }
    }

    private static void procesarSiguientePendiente() {
        Problem siguiente = principal.getProblemsList().stream()
                .filter(p -> !(p instanceof RegularProblem) || !((RegularProblem) p).isResolved())
                .findFirst()
                .orElse(null);

        if (siguiente == null) {
            System.out.println("Todos los problemas registrados ya fueron resueltos.");
            return;
        }
        principal.procesarProblema(siguiente);
    }

    private static void verEstadoSubHandlers() {
        System.out.println("\n-- Estado de los SubHandlers --");
        for (Handler h : principal.getHandlersList()) {
            if (h instanceof BaseHandler) {
                BaseHandler bh = (BaseHandler) h;
                System.out.println(bh.getNombreRol() + " (nivel " + bh.getNivel() + ") -> " + bh.getSubHandler());
            }
        }
    }
}
