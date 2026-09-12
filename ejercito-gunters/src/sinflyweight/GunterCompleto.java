package sinflyweight;

/**
 * Versión SIN Flyweight.
 * Cada instancia guarda TANTO el estado intrínseco (tipo, sonido) COMO el
 * extrínseco (x, y). No hay reutilización ni fábrica: se crea un objeto
 * completo por cada Gunter simulado.
 */
public class GunterCompleto {

    private final int x;
    private final int y;
    private final String tipo;
    private final String sonido;

    public GunterCompleto(int x, int y, String tipo, String sonido) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.sonido = sonido;
    }

    public void hacerSonido() {
        System.out.println(sonido + "! Gunter " + tipo + " en posición (" + x + ", " + y + ")");
    }
}
