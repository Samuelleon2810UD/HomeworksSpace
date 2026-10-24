package com.ui;

import com.ui.content.TarjetaElemento;
import com.ui.input.ValidadorForgiving;
import com.ui.navigation.BreadcrumbNavigator;
import com.ui.state.GestorSesionUI;

import java.io.Console;
import java.io.PrintStream;

/**
 * Ejecuta los 5 casos de prueba del taller en consola.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Console consola = System.console();
        if (consola != null) {
            System.setOut(new PrintStream(System.out, true, consola.charset()));
        }

        casoNavegacion();
        casoProgressiveDisclosure();
        casoForgivingFormat();
        casoCompletenessMeter();
        casoLazyRegistrationYToast();
        casoSalidasSinDarkPatterns();
    }

    // Caso 1
    private static void casoNavegacion() {
        System.out.println("--- 1. PATRÓN DE NAVEGACIÓN (BREADCRUMBS + TABS) ---");
        BreadcrumbNavigator nav = new BreadcrumbNavigator("Catálogo", "Reservas", "Mi cuenta");

        nav.seleccionarPestana("Catálogo");
        nav.entrarSubcategoria("Servidor Principal");
        System.out.println("Ruta actual: " + nav.getRuta());

        nav.seleccionarPestana("Reservas");
        nav.entrarSubcategoria("Laboratorio de Redes");
        System.out.println("Ruta actual: " + nav.getRuta());

        nav.seleccionarPestana("Catálogo");
        System.out.println("Ruta actual (al volver a la pestaña Catálogo): " + nav.getRuta());

        nav.subirNivel();
        System.out.println("Ruta actual (tras subir un nivel): " + nav.getRuta());
        System.out.println();
    }

    // Caso 2
    private static void casoProgressiveDisclosure() {
        System.out.println("--- 2. PATRÓN DE CONTENIDO Y ESTRUCTURA (PROGRESSIVE DISCLOSURE) ---");
        TarjetaElemento tarjeta =
                new TarjetaElemento("Servidor Principal", "Activo", "192.168.1.10", 8080, 4, 16);

        System.out.println("verAvanzado = " + tarjeta.isVerAvanzado());
        System.out.println(tarjeta.render());

        System.out.println("[Usuario solicita ver detalles avanzados...]");
        tarjeta.desplegar();
        System.out.println("verAvanzado = " + tarjeta.isVerAvanzado());
        System.out.println(tarjeta.render());
        System.out.println();
    }

    // Caso 3
    private static void casoForgivingFormat() {
        System.out.println("--- 3. PATRÓN DE ENTRADA DE DATOS (FORGIVING FORMAT) ---");
        ValidadorForgiving validador = new ValidadorForgiving();

        String[] telefonos = {"+57 (310) 999-8877", "+57 (300) 123-4567", "310 999 8877", "(310)999.8877"};
        for (String t : telefonos) {
            System.out.println("Entrada del usuario: \"" + t + "\"");
            System.out.println("Teléfono normalizado sin error: \"" + validador.normalizarTelefono(t) + "\"");
        }

        String[] fechas = {"05/11/2026", "5-11-26", "2026.11.05", "5 11 2026"};
        for (String f : fechas) {
            System.out.println("Fecha ingresada: \"" + f + "\" -> \"" + validador.normalizarFecha(f) + "\"");
        }
        System.out.println();
    }

    // Caso 4
    private static void casoCompletenessMeter() {
        System.out.println("--- 4. PATRÓN DE FEEDBACK Y ESTADO (COMPLETENESS METER) ---");
        GestorSesionUI gestor = new GestorSesionUI("Teléfono", "Fecha de reserva", "Correo de contacto");
        ValidadorForgiving v = gestor.getValidador();

        System.out.println(gestor.mostrarProgreso());

        gestor.completarCampo("Teléfono", v.normalizarTelefono("+57 (310) 999-8877"));
        System.out.println("Usuario ingresa teléfono: \"+57 (310) 999-8877\"");
        System.out.println(gestor.mostrarProgreso());

        gestor.completarCampo("Fecha de reserva", v.normalizarFecha("05/11/2026"));
        System.out.println("Usuario ingresa fecha: \"05/11/2026\"");
        System.out.println(gestor.mostrarProgreso());

        gestor.completarCampo("Correo de contacto", v.normalizarCorreo("estudiante@udistrital.edu.co"));
        System.out.println("Usuario ingresa correo: \"estudiante@udistrital.edu.co\"");
        System.out.println(gestor.mostrarProgreso());
        System.out.println();
    }

    // Caso 5
    private static void casoLazyRegistrationYToast() {
        System.out.println("--- 5. LAZY REGISTRATION + TOAST ---");
        GestorSesionUI gestor = new GestorSesionUI("Teléfono", "Fecha de reserva", "Correo de contacto");
        ValidadorForgiving v = gestor.getValidador();

        // El usuario arma la reserva SIN haber iniciado sesión.
        gestor.completarCampo("Teléfono", v.normalizarTelefono("+57 (310) 999-8877"));
        gestor.completarCampo("Fecha de reserva", v.normalizarFecha("05/11/2026"));
        gestor.completarCampo("Correo de contacto", v.normalizarCorreo("estudiante@udistrital.edu.co"));
        System.out.println("Reserva armada sin iniciar sesión. usuarioActivo = " + gestor.isSesionActiva());
        System.out.println(gestor.mostrarProgreso());

        System.out.println("Usuario intenta guardar sin haber iniciado sesión...");
        System.out.println(gestor.intentarGuardar());
        System.out.println("¿Borrador conservado? " + gestor.tieneBorrador()
                + " | ¿Reserva guardada? " + gestor.isReservaGuardada());

        System.out.println(gestor.registroRapido());
        System.out.println("usuarioActivo = " + gestor.isSesionActiva()
                + " | Progreso recuperado del borrador: " + gestor.calcularPorcentaje() + "%");

        System.out.println("Usuario intenta guardar con sesión activa...");
        System.out.println(gestor.intentarGuardar());
        System.out.println("¿Reserva guardada? " + gestor.isReservaGuardada());
        System.out.println();
    }

    // Restricción ética: salidas en un solo paso y con mensajes neutros.
    private static void casoSalidasSinDarkPatterns() {
        System.out.println("--- EXTRA. CERO DARK PATTERNS (SALIDA EN UN SOLO PASO) ---");
        GestorSesionUI gestor = new GestorSesionUI("Teléfono", "Fecha de reserva", "Correo de contacto");
        gestor.registroRapido();
        gestor.completarCampo("Teléfono", "3109998877");
        System.out.println("Usuario pulsa 'Cancelar reserva' (1 clic):");
        System.out.println(gestor.cancelarReserva());
        System.out.println("Usuario pulsa 'Cerrar sesión' (1 clic):");
        System.out.println(gestor.cerrarSesion());
        System.out.println("usuarioActivo = " + gestor.isSesionActiva());
    }
}
