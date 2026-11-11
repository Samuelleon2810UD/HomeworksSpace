package visas;

/** Estado de la evaluacion mas un mensaje que explica el motivo. */
public record ResultadoEvaluacion(EstadoEvaluacion estado, String mensaje) {

    public boolean fueAprobada() {
        return estado == EstadoEvaluacion.APROBADA;
    }
}
