package visas;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Reemplaza los numeros magicos (tipo == 1, 2, 3 y los rangos de edad).
 * Cada constante tiene nombre y lleva sus propias reglas de edad.
 * Agregar un tipo nuevo de visa = agregar UNA linea aqui.
 */
public enum TipoVisa {

    TURISTA(1, 18, TipoVisa.SIN_LIMITE),
    ESTUDIANTE(2, 16, 30),
    TRABAJO(3, 18, 65);

    private static final int SIN_LIMITE = Integer.MAX_VALUE;

    private final int codigo;
    private final int edadMinima;
    private final int edadMaxima;

    TipoVisa(int codigo, int edadMinima, int edadMaxima) {
        this.codigo = codigo;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean admiteEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    /** Traduce un codigo numerico heredado a la constante; falla con mensaje claro. */
    public static TipoVisa desdeCodigo(int codigo) {
        for (TipoVisa tipo : values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new TipoVisaInvalidoException("Tipo de visa invalido: " + codigo
                + ". Tipos validos: " + descripcionDeCodigos());
    }

    private static String descripcionDeCodigos() {
        return Arrays.stream(values())
                .map(t -> t.codigo + "=" + t.name())
                .collect(Collectors.joining(", "));
    }
}
