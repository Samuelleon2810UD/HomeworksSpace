/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coffeecooperative;

import Extras.Lotes;
import Observer.Barist;
import Observer.ModalidadNotificacion;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Estudiantes
 */
public class CoffeeCooperative {

    private static final Scanner sc = new Scanner(System.in);
    private static final Subject.CoffeeCooperative cooperativa = new Subject.CoffeeCooperative();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    registrarBarista();
                    break;
                case 2:
                    eliminarBarista();
                    break;
                case 3:
                    agregarLote();
                    break;
                case 4:
                    listarBaristas();
                    break;
                case 5:
                    listarLotes();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("===== COOPERATIVA DE CAFÉ - MENÚ =====");
        System.out.println("1. Registrar barista (suscribirse a notificaciones)");
        System.out.println("2. Eliminar barista (desuscribirse)");
        System.out.println("3. Agregar nuevo lote de café (notifica a todos los baristas)");
        System.out.println("4. Listar baristas suscritos y sus lotes conocidos");
        System.out.println("5. Listar todos los lotes registrados");
        System.out.println("0. Salir");
    }

    private static void registrarBarista() {
        System.out.println("--- Registrar barista ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        int id = leerEntero("ID: ");

        System.out.println("Modalidad de notificación:");
        System.out.println("  1. Push completo (recibe la lista completa de lotes)");
        System.out.println("  2. Push incremental (recibe solo el lote nuevo)");
        System.out.println("  3. Pull (se le avisa y él consulta los datos)");
        int modOpcion = leerEntero("Seleccione modalidad: ");

        ModalidadNotificacion modalidad;
        switch (modOpcion) {
            case 1:
                modalidad = ModalidadNotificacion.PUSH_COMPLETO;
                break;
            case 2:
                modalidad = ModalidadNotificacion.PUSH_INCREMENTAL;
                break;
            case 3:
                modalidad = ModalidadNotificacion.PULL;
                break;
            default:
                System.out.println("Modalidad inválida, se asigna PUSH_COMPLETO por defecto.");
                modalidad = ModalidadNotificacion.PUSH_COMPLETO;
        }

        Barist barista = new Barist(nombre, id, modalidad);
        cooperativa.AddBarist(barista);
        System.out.println("Barista '" + nombre + "' registrado con modalidad " + modalidad + ".");
    }

    private static void eliminarBarista() {
        System.out.println("--- Eliminar barista ---");
        List<Barist> baristas = cooperativa.getBaristas();
        if (baristas.isEmpty()) {
            System.out.println("No hay baristas registrados.");
            return;
        }
        for (int i = 0; i < baristas.size(); i++) {
            System.out.println((i + 1) + ". " + baristas.get(i).getName()
                    + " (ID " + baristas.get(i).getIdNumber() + ")");
        }
        int index = leerEntero("Seleccione el número del barista a eliminar (0 para cancelar): ");
        if (index == 0) {
            return;
        }
        if (index < 1 || index > baristas.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        Barist eliminado = baristas.get(index - 1);
        cooperativa.RemoveBarist(eliminado);
        System.out.println("Barista '" + eliminado.getName() + "' eliminado.");
    }

    private static void agregarLote() {
        System.out.println("--- Agregar nuevo lote de café ---");
        System.out.print("Varietal: ");
        String varietal = sc.nextLine();
        System.out.print("Notas de cata: ");
        String notas = sc.nextLine();
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        int unidades = leerEntero("Unidades: ");
        int altitud = leerEntero("Altitud (msnm): ");
        System.out.print("Nombre del productor: ");
        String productor = sc.nextLine();

        Lotes lote = new Lotes(varietal, notas, origen, unidades, altitud, productor);
        cooperativa.AddLote(lote);
        System.out.println("Lote agregado. Notificando a los baristas suscritos...");
        cooperativa.InformBarist();
    }

    private static void listarBaristas() {
        System.out.println("--- Baristas suscritos ---");
        List<Barist> baristas = cooperativa.getBaristas();
        if (baristas.isEmpty()) {
            System.out.println("No hay baristas registrados.");
            return;
        }
        for (Barist b : baristas) {
            System.out.println("- " + b.getName() + " (ID " + b.getIdNumber() + ", modalidad: "
                    + b.getModalidad() + ")");
            List<Lotes> lotesConocidos = b.getActualLotes();
            if (lotesConocidos == null || lotesConocidos.isEmpty()) {
                System.out.println("    Aún no conoce ningún lote.");
            } else {
                for (Lotes lo : lotesConocidos) {
                    System.out.println("    " + lo);
                }
            }
        }
    }

    private static void listarLotes() {
        System.out.println("--- Lotes registrados en la cooperativa ---");
        List<Lotes> lotes = cooperativa.getLotes();
        if (lotes.isEmpty()) {
            System.out.println("No hay lotes registrados todavía.");
            return;
        }
        for (Lotes lo : lotes) {
            System.out.println("- " + lo);
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine();
            try {
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

}
