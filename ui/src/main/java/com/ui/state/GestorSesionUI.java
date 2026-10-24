package com.ui.state;

import com.ui.input.ValidadorForgiving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Patrón de Feedback y Estado: Completeness Meter + Toast,
 * y patrón de Entrada: Lazy Registration (borrador temporal).
 *
 * Restricción ética: cancelar la reserva y cerrar sesión se hacen en un solo paso,
 * con mensajes neutros (sin Roach Motel ni Confirmshaming).
 */
public class GestorSesionUI {

    private final ValidadorForgiving validador = new ValidadorForgiving();

    // Campos del formulario, en orden. Valor vacío = pendiente.
    private final Map<String, String> campos = new LinkedHashMap<>();
    // Borrador temporal (Lazy Registration).
    private Map<String, String> borrador = null;

    private final List<String> toasts = new ArrayList<>();
    private boolean sesionActiva = false;
    private boolean reservaGuardada = false;

    public GestorSesionUI(String... nombresCampos) {
        for (String nombre : nombresCampos) {
            campos.put(nombre, "");
        }
    }

    // ---------------- Completeness Meter ----------------

    /** Guarda el valor de un campo (ya normalizado) y devuelve el nuevo porcentaje. */
    public int completarCampo(String campo, String valor) {
        if (campos.containsKey(campo) && valor != null && !valor.trim().isEmpty()) {
            campos.put(campo, valor.trim());
        }
        return calcularPorcentaje();
    }

    public int camposListos() {
        int listos = 0;
        for (String v : campos.values()) {
            if (!v.isEmpty()) {
                listos++;
            }
        }
        return listos;
    }

    /** Porcentaje de avance redondeado: con 3 campos da 0%, 33%, 67% y 100%. */
    public int calcularPorcentaje() {
        if (campos.isEmpty()) {
            return 0;
        }
        return Math.round(camposListos() * 100f / campos.size());
    }

    public List<String> camposFaltantes() {
        List<String> faltantes = new ArrayList<>();
        for (Map.Entry<String, String> e : campos.entrySet()) {
            if (e.getValue().isEmpty()) {
                faltantes.add(e.getKey());
            }
        }
        return faltantes;
    }

    public String mostrarProgreso() {
        int total = campos.size();
        int listos = camposListos();
        int pct = calcularPorcentaje();
        if (listos == total) {
            return "[Progreso: " + pct + "%] ¡Formulario completo!";
        }
        return "[Progreso: " + pct + "%] Campos listos: " + listos + " de " + total
                + " (Falta: " + String.join(", ", camposFaltantes()) + ")";
    }

    // ---------------- Lazy Registration ----------------

    /**
     * Intenta guardar la reserva.
     * - Sin sesión: conserva un borrador temporal y pide crear la cuenta (no pierde nada).
     * - Con sesión: guarda y emite el Toast de éxito.
     */
    public String intentarGuardar() {
        if (!sesionActiva) {
            borrador = new LinkedHashMap<>(campos);
            return "[Lazy Registration] Avance guardado en borrador temporal. "
                    + "Por favor cree su cuenta para finalizar.";
        }
        return confirmarGuardado();
    }

    /** Registro rápido: activa la sesión y recupera el borrador sin que el usuario reescriba nada. */
    public String registroRapido() {
        sesionActiva = true;
        if (borrador != null) {
            campos.putAll(borrador);
            borrador = null;
        }
        return "Usuario completa registro rápido -> Sesión activa = true";
    }

    private String confirmarGuardado() {
        reservaGuardada = true;
        return emitirToast("Reserva guardada exitosamente.");
    }

    /** Guarda directamente tras autenticarse (si había borrador, ya fue recuperado). */
    public String guardarTrasAutenticar() {
        return intentarGuardar();
    }

    // ---------------- Toast ----------------

    public String emitirToast(String mensaje) {
        toasts.add(mensaje);
        return "[Toast de Estado] " + mensaje;
    }

    public List<String> getToasts() {
        return Collections.unmodifiableList(toasts);
    }

    // ---------------- Salidas en un solo paso (cero Dark Patterns) ----------------

    /** Cancelar la reserva: un solo paso, sin preguntas manipuladoras ni pasos ocultos. */
    public String cancelarReserva() {
        reservaGuardada = false;
        borrador = null;
        for (String k : campos.keySet()) {
            campos.put(k, "");
        }
        return emitirToast("Reserva cancelada.");
    }

    /** Cerrar sesión: un solo paso y mensaje neutro. */
    public String cerrarSesion() {
        sesionActiva = false;
        return emitirToast("Sesión cerrada.");
    }

    // ---------------- Estado ----------------

    public boolean isSesionActiva() {
        return sesionActiva;
    }

    public boolean isReservaGuardada() {
        return reservaGuardada;
    }

    public boolean tieneBorrador() {
        return borrador != null;
    }

    public ValidadorForgiving getValidador() {
        return validador;
    }
}
