package com.taller.interpreter;

import com.taller.interpreter.context.Context;
import com.taller.interpreter.expression.Destilar;
import com.taller.interpreter.expression.Expression;
import com.taller.interpreter.expression.Huir;
import com.taller.interpreter.expression.InvocarLlama;
import com.taller.interpreter.expression.Meditar;
import com.taller.interpreter.expression.Recolectar;
import com.taller.interpreter.expression.Repetir;
import com.taller.interpreter.expression.SellarGrieta;
import com.taller.interpreter.expression.Secuencia;
import com.taller.interpreter.expression.Condicional;
import com.taller.interpreter.expression.condicion.Condicion;
import com.taller.interpreter.expression.condicion.ConstructoActivo;
import com.taller.interpreter.expression.condicion.InestabilidadAlta;
import com.taller.interpreter.expression.condicion.IngredientesSuficientes;
import com.taller.interpreter.expression.condicion.ManaSuficiente;
import com.taller.interpreter.expression.condicion.No;
import com.taller.interpreter.expression.condicion.O;
import com.taller.interpreter.expression.condicion.Y;

import java.util.Scanner;

/**
 * Punto de entrada del taller "El Alquimista de la Torre Carmesí".
 * <p>
 * Ejecuta los 5 casos de prueba obligatorios, el desafío final (expresión
 * anidada de 3 niveles con combinadores lógicos Y/O/NO) y ofrece un menú
 * interactivo para experimentar con el lenguaje libremente.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println(" TALLER INTERPRETER - EL ALQUIMISTA DE LA TORRE CARMESI");
        System.out.println("========================================================\n");

        ejecutarCasosDePrueba();
        ejecutarDesafioFinal();
        menuInteractivo();
    }

    // ---------------------------------------------------------------
    // Parte 6: casos de prueba obligatorios
    // ---------------------------------------------------------------
    private static void ejecutarCasosDePrueba() {
        System.out.println(">>> CASOS DE PRUEBA\n");

        // Caso 1: RECOLECTAR
        System.out.println("Caso 1: RECOLECTAR");
        Context ctx1 = new Context(100, 50, 0, 20);
        new Recolectar().interpret(ctx1);
        System.out.println();

        // Caso 2: RECOLECTAR + DESTILAR
        System.out.println("Caso 2: RECOLECTAR + RECOLECTAR + DESTILAR");
        Context ctx2 = new Context(100, 50, 0, 20);
        new Secuencia(new Recolectar(), new Recolectar(), new Destilar()).interpret(ctx2);
        System.out.println();

        // Expresión reutilizable para los casos 3 y 4:
        // SI CONSTRUCTO_ACTIVO ENTONCES INVOCAR_LLAMA SINO RECOLECTAR
        Expression siConstructoInvocaSinoRecolecta = new Condicional(
                new ConstructoActivo(), new InvocarLlama(), new Recolectar());

        // Caso 3: constructo activo -> Invoca llama
        System.out.println("Caso 3: SI CONSTRUCTO_ACTIVO ENTONCES INVOCAR_LLAMA SINO RECOLECTAR  (constructo activo)");
        Context ctx3 = new Context(100, 50, 0, 20);
        siConstructoInvocaSinoRecolecta.interpret(ctx3);
        System.out.println();

        // Caso 4: constructo destruido -> Recolecta
        System.out.println("Caso 4: SI CONSTRUCTO_ACTIVO ENTONCES INVOCAR_LLAMA SINO RECOLECTAR  (constructo destruido)");
        Context ctx4 = new Context(0, 50, 0, 20);
        siConstructoInvocaSinoRecolecta.interpret(ctx4);
        System.out.println();

        // Caso 5: expresión compuesta que depende del cambio de estado.
        // INVOCAR_LLAMA sube la inestabilidad; el condicional que sigue evalúa
        // INESTABILIDAD_ALTA sobre el estado YA modificado, no el inicial.
        System.out.println("Caso 5: INVOCAR_LLAMA y luego SI INESTABILIDAD_ALTA ENTONCES SELLAR_GRIETA "
                + "SINO INVOCAR_LLAMA  (inestabilidad inicial = 50, por debajo del umbral de 70)");
        Context ctx5 = new Context(100, 50, 0, 50);
        Expression invocarYLuegoDecidir = new Secuencia(
                new InvocarLlama(), // inestabilidad: 50 -> 75 (supera el umbral de 70)
                new Condicional(new InestabilidadAlta(), new SellarGrieta(), new InvocarLlama())
        );
        invocarYLuegoDecidir.interpret(ctx5);
        System.out.println("Resultado: la condicion evaluo el NUEVO estado (inestabilidad tras el conjuro) "
                + "y por eso eligio SELLAR_GRIETA en vez de un segundo INVOCAR_LLAMA.\n");
    }

    // ---------------------------------------------------------------
    // Parte 4: desafío final - expresión con 3 niveles de anidación y
    // condiciones compuestas con combinadores lógicos Y / O / NO.
    //
    // SI (CONSTRUCTO_ACTIVO Y NO INESTABILIDAD_ALTA) ENTONCES
    //     REPETIR 2 VECES (INVOCAR_LLAMA)
    // SINO SI (CONSTRUCTO_ACTIVO Y INESTABILIDAD_ALTA) ENTONCES
    //     SELLAR_GRIETA + (SI MANA_SUFICIENTE ENTONCES INVOCAR_LLAMA SINO MEDITAR)
    // SINO
    //     RECOLECTAR + RECOLECTAR + DESTILAR
    // ---------------------------------------------------------------
    private static Expression construirDesafioFinal() {
        Condicion activoYEstable = new Y(new ConstructoActivo(), new No(new InestabilidadAlta()));
        Expression ramaAtaqueDoble = new Repetir(2, new InvocarLlama());

        Condicion activoYInestable = new Y(new ConstructoActivo(), new InestabilidadAlta());
        Expression decisionInterna = new Condicional(new ManaSuficiente(), new InvocarLlama(), new Meditar());
        Expression ramaSellarYDecidir = new Secuencia(new SellarGrieta(), decisionInterna);

        Expression ramaSinConstructo = new Secuencia(new Recolectar(), new Recolectar(), new Destilar());

        Expression siNoActivoYEstable = new Condicional(activoYInestable, ramaSellarYDecidir, ramaSinConstructo);

        return new Condicional(activoYEstable, ramaAtaqueDoble, siNoActivoYEstable);
    }

    private static void ejecutarDesafioFinal() {
        System.out.println(">>> DESAFIO FINAL: expresion anidada de 3 niveles con Y / O / NO (Parte 4)\n");
        Expression desafio = construirDesafioFinal();

        System.out.println("Ejecucion 1: constructo activo, inestabilidad BAJA (esperado: REPETIR 2 VECES INVOCAR_LLAMA)");
        Context contextoA = new Context(200, 50, 0, 20);
        desafio.interpret(contextoA);
        System.out.println();

        System.out.println("Ejecucion 2: constructo activo, inestabilidad ALTA, mana suficiente tras sellar "
                + "(esperado: SELLAR_GRIETA + INVOCAR_LLAMA)");
        Context contextoB = new Context(100, 30, 0, 80);
        desafio.interpret(contextoB);
        System.out.println();

        System.out.println("Ejecucion 3: constructo destruido (esperado: RECOLECTAR + RECOLECTAR + DESTILAR)");
        Context contextoC = new Context(0, 50, 0, 20);
        desafio.interpret(contextoC);
        System.out.println();
    }

    // ---------------------------------------------------------------
    // Menu interactivo para explorar el lenguaje libremente
    // ---------------------------------------------------------------
    private static void menuInteractivo() {
        Scanner scanner = new Scanner(System.in);
        Context context = new Context(100, 50, 1, 40);

        while (true) {
            System.out.println("========================================================");
            System.out.println(" MENU INTERACTIVO - Estado actual: " + context);
            System.out.println("========================================================");
            System.out.println("1. Ejecutar RECOLECTAR");
            System.out.println("2. Ejecutar DESTILAR");
            System.out.println("3. Ejecutar INVOCAR_LLAMA");
            System.out.println("4. Ejecutar SELLAR_GRIETA");
            System.out.println("5. Ejecutar MEDITAR");
            System.out.println("6. Ejecutar HUIR");
            System.out.println("7. Ejecutar REPETIR 3 VECES (INVOCAR_LLAMA)");
            System.out.println("8. Ejecutar SI (CONSTRUCTO_ACTIVO Y NO INESTABILIDAD_ALTA) ENTONCES INVOCAR_LLAMA SINO SELLAR_GRIETA");
            System.out.println("9. Ejecutar el desafio final completo (3 niveles de anidacion)");
            System.out.println("10. Reiniciar el contexto (elegir nuevos valores)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine().trim();
            System.out.println();

            switch (opcion) {
                case "1":
                    new Recolectar().interpret(context);
                    break;
                case "2":
                    new Destilar().interpret(context);
                    break;
                case "3":
                    new InvocarLlama().interpret(context);
                    break;
                case "4":
                    new SellarGrieta().interpret(context);
                    break;
                case "5":
                    new Meditar().interpret(context);
                    break;
                case "6":
                    new Huir().interpret(context);
                    break;
                case "7":
                    new Repetir(3, new InvocarLlama()).interpret(context);
                    break;
                case "8":
                    Condicion activoYEstable = new Y(new ConstructoActivo(), new No(new InestabilidadAlta()));
                    new Condicional(activoYEstable, new InvocarLlama(), new SellarGrieta()).interpret(context);
                    break;
                case "9":
                    construirDesafioFinal().interpret(context);
                    break;
                case "10":
                    context = pedirNuevoContexto(scanner);
                    break;
                case "0":
                    System.out.println("El alquimista abandona la Torre Carmesi. Hasta la proxima.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
            System.out.println();
        }
    }

    private static Context pedirNuevoContexto(Scanner scanner) {
        System.out.print("Vida del Constructo (0 = destruido, 1-300): ");
        int vidaConstructo = leerEntero(scanner, 0, 300);
        System.out.print("Mana actual (0-100): ");
        int mana = leerEntero(scanner, 0, 100);
        System.out.print("Ingredientes (0-20): ");
        int ingredientes = leerEntero(scanner, 0, 20);
        System.out.print("Inestabilidad de la torre (0-100): ");
        int inestabilidad = leerEntero(scanner, 0, 100);
        return new Context(vidaConstructo, mana, ingredientes, inestabilidad);
    }

    private static int leerEntero(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException ignored) {
                // vuelve a pedir
            }
            System.out.print("Valor invalido, intente de nuevo (" + min + "-" + max + "): ");
        }
    }
}
