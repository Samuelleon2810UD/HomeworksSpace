package com.ui.navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Patrón de Navegación: Breadcrumbs + Tabs.
 * Mantiene la pestaña activa y la ruta jerárquica (Inicio > Sección > Subsección)
 * para que el usuario siempre sepa dónde está.
 */
public class BreadcrumbNavigator {

    private static final String RAIZ = "Inicio";
    private static final String SEPARADOR = " > ";

    // Cada pestaña (Tab) conserva su propia ruta de subniveles.
    private final Map<String, List<String>> subniveles = new LinkedHashMap<>();
    private String pestanaActiva;

    public BreadcrumbNavigator(String... pestanas) {
        for (String p : pestanas) {
            subniveles.put(p, new ArrayList<>());
        }
        if (pestanas.length > 0) {
            pestanaActiva = pestanas[0];
        }
    }

    /** Cambia de pestaña; la ruta se reconstruye con los subniveles de esa pestaña. */
    public void seleccionarPestana(String pestana) {
        subniveles.putIfAbsent(pestana, new ArrayList<>());
        pestanaActiva = pestana;
    }

    /** Entra a una subcategoría dentro de la pestaña activa. */
    public void entrarSubcategoria(String subcategoria) {
        if (pestanaActiva == null) {
            return;
        }
        subniveles.get(pestanaActiva).add(subcategoria);
    }

    /** Sube un nivel (equivale a hacer clic en el penúltimo elemento de la ruta). */
    public void subirNivel() {
        if (pestanaActiva == null) {
            return;
        }
        List<String> niveles = subniveles.get(pestanaActiva);
        if (!niveles.isEmpty()) {
            niveles.remove(niveles.size() - 1);
        }
    }

    /** Vuelve directamente a Inicio (clic en el primer elemento de la ruta). */
    public void irAInicio() {
        pestanaActiva = null;
    }

    public List<String> getPestanas() {
        return Collections.unmodifiableList(new ArrayList<>(subniveles.keySet()));
    }

    public String getPestanaActiva() {
        return pestanaActiva;
    }

    /** Construye la cadena de ruta, por ejemplo: Inicio > Reservas > Laboratorio de Redes. */
    public String getRuta() {
        StringBuilder ruta = new StringBuilder(RAIZ);
        if (pestanaActiva != null) {
            ruta.append(SEPARADOR).append(pestanaActiva);
            for (String nivel : subniveles.get(pestanaActiva)) {
                ruta.append(SEPARADOR).append(nivel);
            }
        }
        return ruta.toString();
    }
}
