/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.duckhuntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Behavior.Fly.FlyBehavior;
import Behavior.Fly.FlyWithSuperMegaJetPack;
import Behavior.Fly.FlyWithWings;
import Behavior.Fly.NoFly;
import Behavior.Sound.SoundBehavior;
import Ducks.DecoyDuck;
import Ducks.Duck;
import Ducks.MallardDuck;
import Ducks.RedDuck;
import Ducks.UleDuck;

/**
 *
 * @author Estudiantes
 */
public class DuckHuntU {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Duck> ducks = new ArrayList<>();

        boolean running = true;

        ducks.add(new MallardDuck());
        ducks.add(new DecoyDuck());

        while (running) {
            System.out.println("\n=================================");
            System.out.println("   🦆 MENÚ INTERACTIVO DUCKHUNT 🦆");
            System.out.println("=================================");
            System.out.println("1. Ver patos actuales y realizar sus acciones");
            System.out.println("2. Añadir un nuevo pato");
            System.out.println("3. Equipar / Desequipar Jetpack (Modificar vuelo)");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            int option = readInt(scanner);

            switch (option) {
                case 1:
                    showAndPerformDucks(ducks);
                    break;
                case 2:
                    addDuckMenu(scanner, ducks);
                    break;
                case 3:
                    toggleJetpackMenu(scanner, ducks);
                    break;
                case 4:
                    running = false;
                    System.out.println("\n¡Gracias por usar DuckHuntU! Hasta luego 👋");
                    break;
                default:
                    System.out.println("\n⚠️ Opción inválida. Intenta nuevamente.");
            }
        }

        scanner.close();
    }



    public static void ChangeFlyBehavior(Duck d , FlyBehavior fb){
            try {
                d.ChangeFlyBehavior(fb);
            } catch (Exception e) {
                System.out.print("OOPS, i have a problem switching the duck fly behavior");
            }
        }
    


    public void ChangeSoundBehavior(Duck d , SoundBehavior fb){
        try {
            d.ChangeSoundBehavior(fb);
        } catch (Exception e) {
            System.out.print("OOPS, i have a problem switching the duck sound behavior");
        }
    }
    
        private static void showAndPerformDucks(List<Duck> ducks) {
            if (ducks.isEmpty()) {
                System.out.println("\n❌ No hay patos creados en el sistema.");
                return;
            }
    
            System.out.println("\n--- LISTA DE PATOS Y SUS ACCIONES ---");
            for (int i = 0; i < ducks.size(); i++) {
                Duck d = ducks.get(i);
                System.out.println("\n---------------------------------");
                System.out.println("Pato #" + (i + 1) + ":");
                d.display();
                System.out.println("\n------------------");
                d.Swim();
                System.out.println("\n------------------");
                d.SoundPerform();
                System.out.println("\n------------------");
                d.flyPerform();
            }
        }

        private static void addDuckMenu(Scanner scanner, List<Duck> ducks) {
            System.out.println("\n--- AÑADIR NUEVO PATO ---");
            System.out.println("1. Mallard Duck (Pato Silvestre)");
            System.out.println("2. Red Duck (Pato Cabeza Roja)");
            System.out.println("3. Decoy Duck (Pato Señuelo)");
            System.out.println("4. Ule Duck (Pato de Hule)");
            System.out.print("Elige el tipo de pato: ");
    
            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    ducks.add(new MallardDuck());
                    System.out.println("✅ MallardDuck añadido con éxito.");
                    break;
                case 2:
                    ducks.add(new RedDuck());
                    System.out.println("✅ RedDuck añadido con éxito.");
                    break;
                case 3:
                    ducks.add(new DecoyDuck());
                    System.out.println("✅ DecoyDuck añadido con éxito.");
                    break;
                case 4:
                    ducks.add(new UleDuck());
                    System.out.println("✅ UleDuck añadido con éxito.");
                    break;
                default:
                    System.out.println("⚠️ Tipo de pato no válido.");
            }
        }
    
        private static void toggleJetpackMenu(Scanner scanner, List<Duck> ducks) {
            if (ducks.isEmpty()) {
                System.out.println("\n❌ No hay patos registrados para modificar.");
                return;
            }
    
            System.out.println("\n--- SELECCIONA EL PATO A MODIFICAR ---");
            for (int i = 0; i < ducks.size(); i++) {
                System.out.print((i + 1) + ". ");
                ducks.get(i).display();
            }
    
            System.out.print("Ingresa el número del pato: ");
            int index = readInt(scanner) - 1;
    
            if (index < 0 || index >= ducks.size()) {
                System.out.println("⚠️ Pato no encontrado o índice fuera de rango.");
                return;
            }
    
            Duck selectedDuck = ducks.get(index);
    
            System.out.println("\n--- CONFIGURACIÓN DE VUELO ---");
            System.out.println("1. Equipar Super Mega Jetpack 🚀");
            System.out.println("2. Restablecer vuelo con alas normal 🪶");
            System.out.println("3. Desequipar todo tipo de vuelo (No volar) 🛑");
            System.out.print("Selecciona una opción: ");
    
            int flyOption = readInt(scanner);
            switch (flyOption) {
                case 1:
                ChangeFlyBehavior(selectedDuck , new FlyWithSuperMegaJetPack());
                System.out.println("🚀 ¡Jetpack equipado correctamente!");
                break;
            case 2:
                ChangeFlyBehavior(selectedDuck ,new FlyWithWings());
                System.out.println("🪶 Comportamiento de vuelo restablecido a alas normales.");
                break;
            case 3:
                ChangeFlyBehavior(selectedDuck ,new NoFly());
                System.out.println("🛑 Se han retirado los accesorios de vuelo.");
                break;
            default:
                System.out.println("⚠️ Opción de vuelo no válida.");
        }
    }

    // Scanner
    private static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
