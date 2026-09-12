package flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.MemoryDisplay;

/**
 * Cliente del patrón Flyweight.
 * Genera el ejército de Gunters (PosicionGunter) alternando entre los 2
 * tipos existentes, los activa a todos y reporta cuántos objetos Flyweight
 * (Gunter) fueron realmente creados, además de medir la memoria usada
 * antes y después de construir el ejército.
 */
public class MainFlyweight {

    private static final int TOTAL_GUNTERS = 100_000;
    private static final String[] TIPOS = {"Normal", "Con sombrero"};

    public static void main(String[] args) {
        Random random = new Random();
        FabricaGunter fabrica = new FabricaGunter();
        List<PosicionGunter> ejercito = new ArrayList<>(TOTAL_GUNTERS);

        MemoryDisplay.mostrarMemoria("ANTES de crear el ejercito (CON Flyweight)");

        for (int i = 0; i < TOTAL_GUNTERS; i++) {
            int x = random.nextInt(1000);
            int y = random.nextInt(1000);
            String tipo = TIPOS[i % TIPOS.length];
            Gunter gunter = fabrica.obtenerGunter(tipo);
            ejercito.add(new PosicionGunter(x, y, gunter));
        }

        MemoryDisplay.mostrarMemoria("DESPUES de crear el ejercito (CON Flyweight)");
        System.out.println();

        // Se activan TODOS los Gunters del ejercito (tal como exige el enunciado).
        // Con TOTAL_GUNTERS = 100000 la consola imprime una linea por Gunter;
        // "LIMITE_IMPRESION" solo controla cuantas se muestran a modo de muestra
        // en este comentario/README, el programa igualmente activa e imprime todas.
        for (PosicionGunter posicion : ejercito) {
            posicion.activar();
        }

        System.out.println();
        System.out.println("Total de pinguinos simulados: " + ejercito.size());
        System.out.println("Total de objetos Flyweight (Gunter) creados: " + fabrica.getCantidadFlyweightsCreados());
    }
}
