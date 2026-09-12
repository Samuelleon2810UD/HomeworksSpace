package sinflyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.MemoryDisplay;

/**
 * Version SIN el patron Flyweight, usada como control para comparar el
 * consumo de memoria contra la version que si lo aplica (paquete flyweight).
 * Crea la misma cantidad de Gunters (TOTAL_GUNTERS) que la version con
 * Flyweight, pero aqui cada uno es un objeto GunterCompleto independiente,
 * sin ningun tipo de reutilizacion.
 */
public class MainSinFlyweight {

    private static final int TOTAL_GUNTERS = 100_000;
    private static final String[] TIPOS = {"Normal", "Con sombrero"};
    private static final String SONIDO_GUNTER = "Wenk";

    public static void main(String[] args) {
        Random random = new Random();
        List<GunterCompleto> ejercito = new ArrayList<>(TOTAL_GUNTERS);

        MemoryDisplay.mostrarMemoria("ANTES de crear el ejercito (SIN Flyweight)");

        for (int i = 0; i < TOTAL_GUNTERS; i++) {
            int x = random.nextInt(1000);
            int y = random.nextInt(1000);
            String tipo = TIPOS[i % TIPOS.length];
            ejercito.add(new GunterCompleto(x, y, tipo, SONIDO_GUNTER));
        }

        MemoryDisplay.mostrarMemoria("DESPUES de crear el ejercito (SIN Flyweight)");
        System.out.println();

        for (GunterCompleto gunter : ejercito) {
            gunter.hacerSonido();
        }

        System.out.println();
        System.out.println("Total de pinguinos simulados: " + ejercito.size());
        System.out.println("Total de objetos GunterCompleto creados: " + ejercito.size());
    }
}
