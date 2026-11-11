package reporte;

import java.util.List;
import util.Consola;

/** Unica responsabilidad: imprimir la tabla final de antipatrones capturados. */
public class TablaDeAntipatrones {

    private static final String FORMATO_FILA = "| %-22s | %-42s | %-42s |";
    private static final String BORDE = "+" + "-".repeat(24) + "+" + "-".repeat(44) + "+" + "-".repeat(44) + "+";

    private final List<AntipatronCapturado> filas;

    public TablaDeAntipatrones(List<AntipatronCapturado> filas) {
        this.filas = filas;
    }

    public static TablaDeAntipatrones delConsulado() {
        return new TablaDeAntipatrones(List.of(
                new AntipatronCapturado("God Object",
                        "Una clase valida, cobra, guarda y avisa",
                        "Una clase por responsabilidad (SRP)"),
                new AntipatronCapturado("Copy-Paste Programming",
                        "Formula de tarifa copiada 3 veces",
                        "Strategy + un unico punto de tarifa base"),
                new AntipatronCapturado("Magic Numbers",
                        "tipo == 3, 0.5 sin significado",
                        "Enum TipoVisa y constantes con nombre"),
                new AntipatronCapturado("Acoplamiento fuerte",
                        "La solicitud crea y llama a 3 servicios",
                        "Observer: la solicitud no conoce a nadie")));
    }

    public void imprimir() {
        Consola.linea(BORDE);
        Consola.linea(String.format(FORMATO_FILA, "Antipatron", "Sintoma", "Solucion"));
        Consola.linea(BORDE);
        for (AntipatronCapturado fila : filas) {
            Consola.linea(String.format(FORMATO_FILA, fila.nombre(), fila.sintoma(), fila.solucion()));
        }
        Consola.linea(BORDE);
    }
}
