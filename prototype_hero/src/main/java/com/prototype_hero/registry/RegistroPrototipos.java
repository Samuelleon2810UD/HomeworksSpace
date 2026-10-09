package com.prototype_hero.registry;

import java.util.HashMap;
import java.util.Map;
import com.prototype_hero.prototype.Personaje;

public class RegistroPrototipos {

    private final Map<String, Personaje> prototipos = new HashMap<>();

    public void registrarPrototipo(String clave, Personaje personaje) {
        prototipos.put(clave, personaje);
    }

    // Devuelve una copia clonada del prototipo, nunca el original.
    // Si la clave no existe, no se intenta clonar y se retorna null.
    public Personaje obtenerCopia(String clave) {
        Personaje prototipo = prototipos.get(clave);
        if (prototipo == null) {
            System.out.println("No existe el prototipo '" + clave + "'.");
            return null;
        }
        return prototipo.clonar();
    }

    // Consulta el prototipo original (solo para mostrar su estado)
    public Personaje consultarPrototipo(String clave) {
        return prototipos.get(clave);
    }
}
