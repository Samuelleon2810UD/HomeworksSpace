package util;

/**
 * Unica responsabilidad: imprimir en consola con un formato uniforme.
 * Cada linea indica QUIEN realiza la tarea (actor).
 */
public final class Consola {

    private static final String SEPARADOR = "=".repeat(78);

    private Consola() {
    }

    public static void titulo(String texto) {
        System.out.println();
        System.out.println(SEPARADOR);
        System.out.println(texto);
        System.out.println(SEPARADOR);
    }

    public static void subtitulo(String texto) {
        System.out.println();
        System.out.println("--- " + texto + " ---");
    }

    public static void registrar(String actor, String mensaje) {
        System.out.println("   [" + actor + "] " + mensaje);
    }

    public static void linea(String texto) {
        System.out.println(texto);
    }
}
