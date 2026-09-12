package flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Fábrica Flyweight.
 * Mantiene un pool (HashMap) de los Flyweight ya creados, indexados por tipo.
 * Si el tipo solicitado ya existe, reutiliza el mismo objeto Gunter;
 * si no existe, lo crea una única vez y lo guarda en el pool.
 */
public class FabricaGunter {

    private final Map<String, Gunter> pool = new HashMap<>();
    private static final String SONIDO_GUNTER = "Wenk";

    /**
     * Devuelve el Flyweight Gunter correspondiente al tipo pedido,
     * creándolo solo si todavía no existe en el pool.
     */
    public Gunter obtenerGunter(String tipo) {
        Gunter gunter = pool.get(tipo);
        if (gunter == null) {
            gunter = new Gunter(tipo, SONIDO_GUNTER);
            pool.put(tipo, gunter);
        }
        return gunter;
    }

    /**
     * Cantidad real de objetos Flyweight (Gunter) creados hasta el momento.
     */
    public int getCantidadFlyweightsCreados() {
        return pool.size();
    }
}
