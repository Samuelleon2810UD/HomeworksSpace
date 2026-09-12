package flyweight;

/**
 * Flyweight concreto.
 * Guarda únicamente el ESTADO INTRÍNSECO, es decir, el que es compartido
 * por todos los Gunters de un mismo tipo: el tipo de pingüino y su sonido.
 * Solo existen 2 instancias de esta clase en toda la aplicación
 * ("Normal" y "Con sombrero"), sin importar cuántos Gunters se simulen.
 */
public class Gunter {

    private final String tipo;   // Estado intrínseco: "Normal" o "Con sombrero"
    private final String sonido; // Estado intrínseco: "Wenk"

    public Gunter(String tipo, String sonido) {
        this.tipo = tipo;
        this.sonido = sonido;
    }

    /**
     * Emite el sonido del Gunter en una posición dada.
     * La posición (x, y) es ESTADO EXTRÍNSECO: no pertenece al Flyweight,
     * sino que es inyectado por el Contexto (PosicionGunter) en tiempo
     * de ejecución.
     */
    public void hacerSonido(int x, int y) {
        System.out.println(sonido + "! Gunter " + tipo + " en posición (" + x + ", " + y + ")");
    }

    public String getTipo() {
        return tipo;
    }
}
