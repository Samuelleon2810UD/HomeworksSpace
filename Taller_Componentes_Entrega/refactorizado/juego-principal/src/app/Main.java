package app;

import armas.EspadaExplorador;
import botas.BotasEnlace;
import casco.CascoHierroSellado;
import casco.CascoRunico;
import casco.ProteccionElemental;
import peto.PetoEnlace;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA FINAL: COMPONENT QUEST (componentes .jar v1.0) ===");

        // Ronda 1: Dragon Ignis -> ataque limpio
        System.out.println("\n--- Ronda 1: Dragon Ignis ---");
        EspadaExplorador espada = new EspadaExplorador();
        System.out.println("Ataque con espada: " + espada.atacar() + " pts");

        // Ronda 2: Golem Sismico -> el peto sobrevive sin botas
        System.out.println("\n--- Ronda 2: Golem Sismico ---");
        BotasEnlace botas = new BotasEnlace();
        PetoEnlace peto = new PetoEnlace(botas);
        System.out.println("Con botas -> escudo activo: " + peto.escudoActivo()
                + " | escudo reforzado: " + peto.escudoReforzado());
        peto.conectarFuente(null); // las botas se retiran en pleno combate
        System.out.println("Sin botas -> escudo activo: " + peto.escudoActivo()
                + " | escudo reforzado: " + peto.escudoReforzado());

        // Ronda 4: Espectro Cambiante (ataca con FUEGO)
        System.out.println("\n--- Ronda 4: Espectro Cambiante (FUEGO) ---");
        ProteccionElemental hierro = new CascoHierroSellado();
        ProteccionElemental runico = new CascoRunico();
        System.out.println("CascoHierroSellado vs FISICO: " + hierro.resistirAtaque("FISICO"));
        System.out.println("CascoHierroSellado vs FUEGO : " + hierro.resistirAtaque("FUEGO"));
        System.out.println("CascoRunico        vs FUEGO : " + runico.resistirAtaque("FUEGO"));
    }
}
