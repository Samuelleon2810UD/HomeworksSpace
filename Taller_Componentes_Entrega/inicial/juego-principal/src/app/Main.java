package app;

import armas.EspadaExplorador;
import casco.CascoHierroSellado;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA INICIAL: COMPONENT QUEST ===");

        // Ronda 1: Dragon Ignis
        EspadaExplorador espada = new EspadaExplorador();
        System.out.println("Ataque con espada: " + espada.atacar() + " pts");

        // Ronda 4: Espectro Cambiante (ataca con FUEGO)
        CascoHierroSellado casco = new CascoHierroSellado();
        System.out.println("Defensa del casco: " + casco.resistirAtaque("FUEGO"));

        // Nota: Peto y Botas no se prueban aqui porque no se pueden exportar como .jar separados
    }
}
