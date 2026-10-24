package com.ui.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Patrón de Entrada de Datos: Forgiving Format.
 * Acepta la entrada en casi cualquier formato y la normaliza internamente.
 * Nunca lanza excepciones ni culpa al usuario: siempre devuelve un valor utilizable.
 */
public class ValidadorForgiving {

    // Prefijo de país de Colombia (+57 / 0057 / 57).
    private static final Pattern NO_DIGITOS = Pattern.compile("\\D");
    private static final Pattern PREFIJO_PAIS = Pattern.compile("^(?:00)?57(?=\\d{10}$)");

    // dd/mm/aaaa, dd-mm-aa, dd.mm.aaaa, "dd mm aaaa"
    private static final Pattern FECHA_DMA =
            Pattern.compile("^(\\d{1,2})[\\s./-]+(\\d{1,2})[\\s./-]+(\\d{2}|\\d{4})$");
    // aaaa-mm-dd, aaaa/mm/dd, aaaa.mm.dd
    private static final Pattern FECHA_AMD =
            Pattern.compile("^(\\d{4})[\\s./-]+(\\d{1,2})[\\s./-]+(\\d{1,2})$");

    /**
     * Quita espacios, paréntesis, guiones, puntos y el prefijo +57.
     * Ejemplo: "+57 (310) 999-8877" -> "3109998877".
     */
    public String normalizarTelefono(String entrada) {
        if (entrada == null) {
            return "";
        }
        String soloDigitos = NO_DIGITOS.matcher(entrada).replaceAll("");
        return PREFIJO_PAIS.matcher(soloDigitos).replaceFirst("");
    }

    /**
     * Acepta la fecha en varios formatos y la entrega como aaaa-mm-dd.
     * Si no logra interpretarla, devuelve el texto limpio (sin error) para que el
     * usuario pueda corregirlo después sin perder lo que escribió.
     */
    public String normalizarFecha(String entrada) {
        if (entrada == null) {
            return "";
        }
        String texto = entrada.trim();

        Matcher dma = FECHA_DMA.matcher(texto);
        if (dma.matches()) {
            return formatear(dma.group(3), dma.group(2), dma.group(1));
        }
        Matcher amd = FECHA_AMD.matcher(texto);
        if (amd.matches()) {
            return formatear(amd.group(1), amd.group(2), amd.group(3));
        }
        return texto;
    }

    /** Correo: solo recorta espacios y pasa a minúsculas (no rechaza al usuario). */
    public String normalizarCorreo(String entrada) {
        return entrada == null ? "" : entrada.trim().toLowerCase();
    }

    private String formatear(String anio, String mes, String dia) {
        if (anio.length() == 2) {
            anio = "20" + anio;
        }
        return anio + "-" + String.format("%02d", Integer.parseInt(mes))
                + "-" + String.format("%02d", Integer.parseInt(dia));
    }
}
