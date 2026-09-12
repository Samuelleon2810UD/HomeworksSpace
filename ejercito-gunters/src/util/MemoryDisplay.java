package util;

/**
 * Utilidad para medir y mostrar por consola la memoria utilizada por la JVM
 * en un momento dado. Se usa para comparar el consumo de memoria entre la
 * versión CON Flyweight y la versión SIN Flyweight.
 */
public class MemoryDisplay {

    /**
     * Fuerza una recolección de basura y muestra por consola la memoria
     * actualmente en uso (en MB), acompañada de una etiqueta descriptiva.
     *
     * @param etiqueta texto identificador del momento en que se mide (ej. "Antes de crear el ejercito")
     */
    public static void mostrarMemoria(String etiqueta) {
        long usadaMB = obtenerMemoriaUsadaMB();
        System.out.println("[MEMORIA] " + etiqueta + " -> " + usadaMB + " MB");
    }

    /**
     * Calcula la memoria actualmente en uso por la JVM, en megabytes.
     * Ejecuta el recolector de basura antes de medir para reducir el ruido
     * producido por objetos temporales que aún no fueron liberados.
     *
     * @return memoria usada en MB (memoria total asignada - memoria libre)
     */
    public static long obtenerMemoriaUsadaMB() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        try {
            Thread.sleep(100); // pequeña espera para dar tiempo al GC
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long memoriaTotal = runtime.totalMemory();
        long memoriaLibre = runtime.freeMemory();
        long memoriaUsada = memoriaTotal - memoriaLibre;
        return memoriaUsada / (1024 * 1024);
    }
}
