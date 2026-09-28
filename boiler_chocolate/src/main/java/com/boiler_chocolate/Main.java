package com.boiler_chocolate;

import com.boiler_chocolate.Boiler.Boiler;

public class Main {
    public static void main(String[] args) {
        Boiler boiler = Boiler.getBoiler();

        System.out.println("=== Intentos con la caldera vacia ===");
        boiler.mix();
        boiler.FilledOut();
        boiler.stopMixing();

        System.out.println("\n=== Llenar y mezclar ===");
        boiler.fill(5);
        boiler.fill(2);
        boiler.mix();

        System.out.println("\n=== Operaciones bloqueadas mientras mezcla ===");
        boiler.fill(1);
        boiler.mix();
        boiler.FilledOut();

        System.out.println("\n=== Detener y vaciar ===");
        boiler.stopMixing();
        boiler.stopMixing();
        boiler.FilledOut();

        System.out.println("\n=== Intentos despues de vaciar ===");
        boiler.mix();
        boiler.FilledOut();
    }
}