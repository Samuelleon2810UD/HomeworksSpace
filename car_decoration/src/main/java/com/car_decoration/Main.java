package com.car_decoration;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.car_decoration.Car.GTLineAT;
import com.car_decoration.Car.VibrantMT;
import com.car_decoration.Car.ZenithAT;
import com.car_decoration.Car.ZenithMT;
import com.car_decoration.Decoration.AlarmsControls;
import com.car_decoration.Decoration.ChargeMatrix;
import com.car_decoration.Decoration.DecorationCar;
import com.car_decoration.Decoration.DragWidget;
import com.car_decoration.Decoration.KiaRin;
import com.car_decoration.Decoration.ParkingSensor;
import com.car_decoration.Decoration.PicantoRug;
import com.car_decoration.Decoration.PortBikes;
import com.car_decoration.Decoration.SecurityPerns;
import com.car_decoration.Decoration.TurnOnKit;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== CONCESIONARIO KIA ===");
            while (true) {
                System.out.println("\n1. Adquirir un auto");
                System.out.println("0. Salir");
                int option = readInt(scanner, "Selecciona una opción: ", 0, 1);
                if (option == -1 || option == 0) {
                    System.out.println("Gracias por visitar el concesionario.");
                    return;
                }
                purchaseCar(scanner);
            }
        }
    }

    private static void purchaseCar(Scanner scanner) {
        List<DecorationCar> cars = List.of(
                new VibrantMT(),
                new ZenithMT(),
                new ZenithAT(),
                new GTLineAT());

        System.out.println("\n--- Selecciona tu auto ---");
        for (int i = 0; i < cars.size(); i++) {
            DecorationCar car = cars.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, car.getType(), money(car.cost()));
        }
        System.out.println("0. Volver al menú principal");
        int carOption = readInt(scanner, "Opción: ", 0, cars.size());
        if (carOption <= 0) {
            return;
        }

        DecorationCar car = cars.get(carOption - 1);
        while (true) {
            System.out.printf("%nAuto seleccionado: %s | Total actual: %s%n",
                    car.getType(), money(car.cost()));
            printDecorationMenu();
            int decorationOption = readInt(scanner, "Selecciona un accesorio (0 para finalizar): ", 0, 9);
            if (decorationOption == 0) {
                break;
            }

            int rimSize = 0;
            String rimColor = "";
            if (decorationOption == 1) {
                System.out.println("Tamaño de rin: 13 (350.000) o 14 (500.000)");
                rimSize = readChoice(scanner, "Selecciona el tamaño: ", 13, 14);
                if (rimSize == -1) {
                    return;
                }
                rimColor = readNonEmptyLine(scanner, "Color de los rines: ");
                if (rimColor == null) {
                    return;
                }
            }

            int quantity = readInt(scanner, "Cantidad: ", 1, Integer.MAX_VALUE);
            if (quantity == -1) {
                return;
            }
            float previousTotal = car.cost();
            car = addDecoration(car, decorationOption, quantity, rimSize, rimColor);
            System.out.printf("Accesorio agregado. Subtotal de accesorios: %s | Total: %s%n",
                    money(car.cost() - previousTotal), money(car.cost()));
        }

        System.out.println("\n--- Resumen de compra ---");
        System.out.println("Auto: " + car.getType());
        List<String> decorations = car.getDecorations();
        if (decorations.isEmpty()) {
            System.out.println("Accesorios: ninguno");
        } else {
            System.out.println("Accesorios:");
            for (String decoration : decorations) {
                System.out.println(" - " + decoration);
            }
        }
        System.out.println("Total a pagar: " + money(car.cost()));
    }

    private static void printDecorationMenu() {
        System.out.println("Accesorios disponibles:");
        System.out.println("1. Kia Rin (tamaño 13 o 14)");
        System.out.println("2. Sensor de parqueo");
        System.out.println("3. Tapete Picanto");
        System.out.println("4. Portabicicletas");
        System.out.println("5. Pernos de seguridad");
        System.out.println("6. Alarma con controles");
        System.out.println("7. Cargador Charge Matrix");
        System.out.println("8. Drag Widget");
        System.out.println("9. Turn On Kit");
    }

    private static DecorationCar addDecoration(DecorationCar car, int option, int quantity,
            int rimSize, String rimColor) {
        return switch (option) {
            case 1 -> new KiaRin(car, quantity, rimSize, rimColor);
            case 2 -> new ParkingSensor(car, quantity);
            case 3 -> new PicantoRug(car, quantity);
            case 4 -> new PortBikes(car, quantity);
            case 5 -> new SecurityPerns(car, quantity);
            case 6 -> new AlarmsControls(car, quantity);
            case 7 -> new ChargeMatrix(car, quantity);
            case 8 -> new DragWidget(car, quantity);
            case 9 -> new TurnOnKit(car, quantity);
            default -> throw new IllegalArgumentException("Accesorio no válido: " + option);
        };
    }

    private static int readChoice(Scanner scanner, String prompt, int first, int second) {
        while (true) {
            int choice = readInt(scanner, prompt, first, second);
            if (choice == -1 || choice == first || choice == second) {
                return choice;
            }
            System.out.printf("Selecciona %d o %d.%n", first, second);
        }
    }

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return -1;
            }
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Keep the menu active until a valid numeric option is entered.
            }
            System.out.printf("Ingresa un número entre %d y %d.%n", min, max);
        }
    }

    private static String readNonEmptyLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return null;
            }
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("El valor no puede estar vacío.");
        }
    }

    private static String money(float amount) {
        return String.format(Locale.US, "$%,.0f", amount);
    }
}