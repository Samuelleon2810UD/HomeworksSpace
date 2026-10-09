package com.prototype_hero;

import com.prototype_hero.model.Inventario;
import com.prototype_hero.prototype.Personaje;
import com.prototype_hero.registry.RegistroPrototipos;

public class Main {

    public static void main(String[] args) {
        RegistroPrototipos registro = new RegistroPrototipos();

        // Registro de prototipos base
        Inventario invGuerrero = new Inventario();
        invGuerrero.agregarItem("Poción de vida");
        registro.registrarPrototipo("Guerrero", new Personaje("Guerrero", "Guerrero", invGuerrero));

        Inventario invMago = new Inventario();
        invMago.agregarItem("Poción de maná");
        invMago.agregarItem("Libro de hechizos");
        registro.registrarPrototipo("Mago", new Personaje("Mago", "Mago", invMago));

        System.out.println("=== Prototipos registrados ===");
        System.out.println(registro.consultarPrototipo("Guerrero"));
        System.out.println(registro.consultarPrototipo("Mago"));

        // Clonaciones
        Personaje guerreroCopia = registro.obtenerCopia("Guerrero");
        guerreroCopia.setNombre("Guerrero-Copia");

        Personaje magoCopia = registro.obtenerCopia("Mago");
        magoCopia.setNombre("Mago-Copia");

        // Modificación del inventario de una copia
        guerreroCopia.getInventario().agregarItem("Escudo");

        System.out.println("\n=== Después de modificar la copia ===");
        System.out.println(guerreroCopia);
        System.out.println(magoCopia);

        System.out.println("\n=== Prototipos originales (sin cambios) ===");
        System.out.println(registro.consultarPrototipo("Guerrero"));
        System.out.println(registro.consultarPrototipo("Mago"));

        // Verificación de independencia
        System.out.println("\n¿Inventarios son el mismo objeto? "
                + (guerreroCopia.getInventario() == registro.consultarPrototipo("Guerrero").getInventario()));

        // Prototipo inexistente
        System.out.println("\n=== Prototipo inexistente ===");
        Personaje inexistente = registro.obtenerCopia("Arquero");
        System.out.println("Resultado: " + inexistente);
    }
}
