package flyweight;

/**
 * Contexto del patrón Flyweight.
 * Guarda el ESTADO EXTRÍNSECO (único por cada Gunter simulado: su posición
 * x, y) junto con una referencia al Flyweight (Gunter) compartido que le
 * corresponde según su tipo.
 * Existen tantas instancias de esta clase como Gunters se simulen
 * (por ejemplo, 100000), pero todas apuntan a solo 2 objetos Gunter.
 */
public class PosicionGunter {

    private final int x;
    private final int y;
    private final Gunter gunter; // referencia al Flyweight compartido

    public PosicionGunter(int x, int y, Gunter gunter) {
        this.x = x;
        this.y = y;
        this.gunter = gunter;
    }

    /**
     * Activa este Gunter: delega en el Flyweight, pasándole el estado
     * extrínseco (su propia posición) como parámetro.
     */
    public void activar() {
        gunter.hacerSonido(x, y);
    }
}
