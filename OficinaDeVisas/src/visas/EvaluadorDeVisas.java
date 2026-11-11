package visas;

/** Unica responsabilidad: decidir si una solicitud cumple la regla de edad de su tipo. */
public class EvaluadorDeVisas {

    public ResultadoEvaluacion evaluar(TipoVisa tipo, int edad) {
        if (tipo.admiteEdad(edad)) {
            return new ResultadoEvaluacion(EstadoEvaluacion.APROBADA,
                    "Visa " + tipo + " aprobada para edad " + edad);
        }
        return new ResultadoEvaluacion(EstadoEvaluacion.RECHAZADA,
                "Visa " + tipo + " rechazada: la edad " + edad + " no cumple la regla del tipo");
    }

    /** Compatibilidad con codigos numericos: un codigo desconocido se explica, no se traga. */
    public ResultadoEvaluacion evaluarPorCodigo(int codigo, int edad) {
        try {
            return evaluar(TipoVisa.desdeCodigo(codigo), edad);
        } catch (TipoVisaInvalidoException e) {
            return new ResultadoEvaluacion(EstadoEvaluacion.RECHAZADA, e.getMessage());
        }
    }
}
